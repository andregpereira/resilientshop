package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoAtualizarEstoqueDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.EnderecoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.ProdutoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.UsuarioMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Pedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.enums.StatusPedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.UsuariosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.DetalhePedidoEntity;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class PedidoManutencaoServiceImpl implements PedidoManutencaoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final UsuariosFeignClient usuariosFeignClient;
    private final ProdutosFeignClient produtosFeignClient;
    private final ProdutoMapper produtoMapper;
    private final UsuarioMapper usuarioMapper;
    private final EnderecoMapper enderecoMapper;

    @Override
    public PedidoDetalharDto criar(PedidoRegistrarDto dto) {
        PedidoEntity pedidoEntity = pedidoMapper.toPedidoEntity(dto);
        log.info("Procurando usuário...");
        pedidoEntity.setUsuario(usuarioMapper.toUsuario(usuariosFeignClient.consultarUsuarioPorId(dto.idUsuario())));
        log.info("Usuário OK");
        log.info("Procurando endereço...");
        pedidoEntity.getUsuario().setEndereco(enderecoMapper.toEndereco(
                usuariosFeignClient.consultarEnderecoPorApelido(dto.idUsuario(), dto.enderecoApelido())));
        log.info("Endereço OK");
        pedidoEntity.setIdEndereco(pedidoEntity.getUsuario().getEndereco().getId());
        log.info("Deduzindo produtos do estoque...");
        produtosFeignClient.subtrair(pedidoEntity.getDetalhePedido().stream().map(
                dp -> new ProdutoAtualizarEstoqueDto(dp.getIdProduto(), dp.getQuantidade())).toList());
        log.info("Calculando subtotal e setando produto(s)...");
        pedidoEntity.getDetalhePedido().parallelStream().forEach(dp -> Optional.of(
                produtoMapper.toProduto(produtosFeignClient.consultarPorId(dp.getIdProduto()))).ifPresent(p -> {
            dp.setSubtotal(p.getValorUnitario().multiply(BigDecimal.valueOf(dp.getQuantidade())));
            dp.setProduto(p);
            dp.setPedido(pedidoEntity);
        }));
        LocalDateTime agora = LocalDateTime.now();
        pedidoEntity.setDataCriacao(agora);
        pedidoEntity.setDataModificacao(agora);
        pedidoEntity.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO.getStatus());
        log.info("Calculando total...");
        pedidoEntity.setTotal(
                pedidoEntity.getDetalhePedido().parallelStream().map(DetalhePedidoEntity::getSubtotal).reduce(
                        BigDecimal.ZERO, BigDecimal::add));
        log.info("Salvando pedido...");
        Pedido pedido = pedidoMapper.toPedido(pedidoRepository.save(pedidoEntity));
        return pedidoMapper.toPedidoDetalharDto(pedido);
    }

    @Override
    public String cancelar(Long id) {
        return pedidoRepository.findByIdAndStatusAguardandoPagamento(id).map(p -> {
            p.setStatus(StatusPedido.CANCELADO.getStatus());
            pedidoRepository.save(p);
            log.info("Pedido com id {} cancelado com sucesso", id);
            return MessageFormat.format("Pedido com id {0} cancelado", id);
        }).orElseThrow(() -> {
            log.info("Pedido aguardando pagamento com id {} não encontrado", id);
            return new PedidoNotFoundException(
                    MessageFormat.format("Poxa! Não foi encontrado um pedido aguardando pagamento com o id {0}", id));
        });
    }

}
