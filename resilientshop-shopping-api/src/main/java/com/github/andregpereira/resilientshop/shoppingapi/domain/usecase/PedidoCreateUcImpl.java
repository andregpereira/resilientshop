package com.github.andregpereira.resilientshop.shoppingapi.domain.usecase;

import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.PedidoGateway;
import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.feign.ProdutoFeignGateway;
import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.feign.UsuarioFeignGateway;
import com.github.andregpereira.resilientshop.shoppingapi.domain.model.DetalhePedido;
import com.github.andregpereira.resilientshop.shoppingapi.domain.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
public class PedidoCreateUcImpl implements PedidoCreateUc {

    private final PedidoGateway gateway;
    private final UsuarioFeignGateway usuarioFeignGateway;
    private final ProdutoFeignGateway produtoFeignGateway;

    @Override
    public Pedido criar(Pedido pedido) {
        pedido.setUsuario(usuarioFeignGateway.findUsuarioById(pedido.getIdUsuario()));
        pedido.setEndereco(pedido.getUsuario().getEnderecos().stream().filter(
                e -> pedido.getId().equals(e.getId())).findAny().orElseThrow());
        pedido.setDetalhesPedido(new HashSet<>(pedido.getDetalhesPedido().stream().collect(
                Collectors.toMap(DetalhePedido::getIdProduto, Function.identity(),
                        (oldVal, newVal) -> oldVal.getQuantidade() < newVal.getQuantidade() ? newVal
                                : oldVal)).values()));
        produtoFeignGateway.subtract(
                pedido.getDetalhesPedido().stream().map(DetalhePedido::getProduto).collect(Collectors.toSet()));
        pedido.getDetalhesPedido().parallelStream().forEach(
                dp -> produtoFeignGateway.findProdutoById(dp.getIdProduto(), p -> {
                    dp.setSubtotal(p.getValorUnitario().multiply(BigDecimal.valueOf(dp.getQuantidade())));
                    dp.setProduto(p);
                }));
        pedido.setTotal(
                pedido.getDetalhesPedido().parallelStream().map(DetalhePedido::getSubtotal).reduce(BigDecimal.ZERO,
                        BigDecimal::add));
        pedido.setStatus(1);
        return gateway.save(pedido);
    }

}
