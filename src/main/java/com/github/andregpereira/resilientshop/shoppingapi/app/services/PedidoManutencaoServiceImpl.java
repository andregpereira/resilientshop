package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.produto.ProdutoAtualizarEstoqueDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
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

    @Override
    public PedidoDetalharDto criar(PedidoRegistrarDto dto) {
        PedidoEntity pedido = pedidoMapper.toPedidoEntity(dto);
        log.info("Procurando usuário...");
        pedido.setUsuario(usuariosFeignClient.consultarUsuarioPorId(dto.idUsuario()));
        log.info("Usuário OK");
        log.info("Procurando endereço...");
        pedido.setEndereco(usuariosFeignClient.consultarEnderecoPorApelido(dto.idUsuario(), dto.enderecoApelido()));
        log.info("Endereço OK");
        pedido.setIdEndereco(pedido.getEndereco().getId());
        log.info("Deduzindo produtos do estoque...");
        produtosFeignClient.subtrair(pedido.getDetalhePedido().stream().map(
                dp -> new ProdutoAtualizarEstoqueDto(dp.getIdProduto(), dp.getQuantidade())).toList());
        log.info("Calculando subtotal e setando produto(s)...");
        pedido.getDetalhePedido().parallelStream().forEach(
                dp -> Optional.of(produtosFeignClient.consultarPorId(dp.getIdProduto())).ifPresent(p -> {
                    dp.setSubtotal(p.getValorUnitario().multiply(BigDecimal.valueOf(dp.getQuantidade())));
                    dp.setProduto(p);
                    dp.setPedido(pedido);
                }));
        LocalDateTime agora = LocalDateTime.now();
        pedido.setDataCriacao(agora);
        pedido.setDataModificacao(agora);
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO.getStatus());
        log.info("Calculando total...");
        pedido.setTotal(
                pedido.getDetalhePedido().parallelStream().map(DetalhePedidoEntity::getSubtotal).reduce(BigDecimal.ZERO,
                        BigDecimal::add));
        log.info("Salvando pedido...");
        return pedidoMapper.toPedidoDetalharDto(pedidoRepository.save(pedido));
    }

    @Override
    public String cancelar(Long id) {
        return pedidoRepository.findByIdAndStatusAguardandoPagamento(id).map(p -> {
            log.info("Retornando produtos ao estoque...");
            produtosFeignClient.retornarEstoque(p.getDetalhePedido().stream().map(
                    dp -> new ProdutoAtualizarEstoqueDto(dp.getIdProduto(), dp.getQuantidade())).toList());
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
