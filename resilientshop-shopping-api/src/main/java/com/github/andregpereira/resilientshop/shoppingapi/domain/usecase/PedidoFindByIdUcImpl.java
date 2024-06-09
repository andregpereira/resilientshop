package com.github.andregpereira.resilientshop.shoppingapi.domain.usecase;

import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.PedidoGateway;
import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.feign.ProdutoFeignGateway;
import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.feign.UsuarioFeignGateway;
import com.github.andregpereira.resilientshop.shoppingapi.domain.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class PedidoFindByIdUcImpl implements PedidoFindByIdUc {

    private final PedidoGateway gateway;
    private final UsuarioFeignGateway usuarioFeignGateway;
    private final ProdutoFeignGateway produtoFeignGateway;

    @Override
    public Pedido findById(Long id) {
        return gateway.findById(id, p -> {
            p.setUsuario(usuarioFeignGateway.findUsuarioById(p.getIdUsuario()));
            p.setEndereco(p.getUsuario().getEnderecos().stream().filter(
                    e -> p.getId().equals(e.getId())).findAny().orElseThrow());
            p.getDetalhesPedido().parallelStream().forEach(
                    dp -> dp.setProduto(produtoFeignGateway.findProdutoById(dp.getIdProduto())));
            return p;
        });
    }

}
