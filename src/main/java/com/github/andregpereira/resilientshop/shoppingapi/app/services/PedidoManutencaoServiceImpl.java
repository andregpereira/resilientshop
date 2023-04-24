package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.DetalhePedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.ProdutoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Pedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Produto;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.StatusPedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutoFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.UsuarioFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.DetalhePedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.DetalhePedidoEntity;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class PedidoManutencaoServiceImpl implements PedidoManutencaoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final DetalhePedidoRepository detalhePedidoRepository;
    private final DetalhePedidoMapper detalhePedidoMapper;
    private final UsuarioFeignClient usuarioFeignClient;
    private final ProdutoFeignClient produtoFeignClient;
    private final ProdutoMapper produtoMapper;

    @Override
    public PedidoDetalharDto criar(PedidoRegistrarDto dto) {
        PedidoEntity pedidoEntity = pedidoMapper.toPedidoEntity(dto);
        log.info("Calculando subtotal e setando produto(s)...");
        pedidoEntity.getDetalhePedido().parallelStream().forEach(dp -> {
            Produto produto = produtoMapper.toProduto(produtoFeignClient.consultarPorId(dp.getIdProduto()));
            dp.setSubtotal(produto.getValorUnitario().multiply(BigDecimal.valueOf(dp.getQuantidade())));
            dp.setProduto(produto);
            dp.setPedido(pedidoEntity);
        });
        log.info("Setando id(s) do(s) produto(s)... ");
        LocalDateTime agora = LocalDateTime.now();
        pedidoEntity.setDataCriacao(agora);
        pedidoEntity.setDataModificacao(agora);
        pedidoEntity.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO.getStatus());
        log.info("Calculando total...");
        pedidoEntity.setTotal(
                pedidoEntity.getDetalhePedido().parallelStream().map(DetalhePedidoEntity::getSubtotal).reduce(
                        BigDecimal.ZERO, BigDecimal::add));
        Pedido pedido = pedidoMapper.toPedido(pedidoRepository.save(pedidoEntity));
        pedido.setDetalhePedido(detalhePedidoMapper.toListaDetalhePedido(
                detalhePedidoRepository.saveAll(pedidoEntity.getDetalhePedido())));
        return pedidoMapper.toPedidoDetalharDto(pedido);
    }

    @Override
    public String cancelar(Long id) {
        pedidoRepository.findByIdAndStatusAguardandoPagamento(id).ifPresentOrElse(p -> {
            p.setStatus(StatusPedido.CANCELADO.getStatus());
            pedidoRepository.save(p);
        }, () -> {
            log.info("Pedido aguardando pagamento com id {} não encontrado", id);
            throw new PedidoNotFoundException("Poxa! Não foi encontrado um pedido aguardando pagamento com o id " + id);
        });
        log.info("Pedido com id {} cancelado com sucesso", id);
        return "Pedido cancelado";
    }

}
