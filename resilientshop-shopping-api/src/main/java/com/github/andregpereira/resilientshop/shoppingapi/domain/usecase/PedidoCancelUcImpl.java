package com.github.andregpereira.resilientshop.shoppingapi.domain.usecase;

import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.PedidoGateway;
import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.feign.ProdutoFeignGateway;
import com.github.andregpereira.resilientshop.shoppingapi.domain.model.DetalhePedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
public class PedidoCancelUcImpl implements PedidoCancelUc {

    private final PedidoGateway gateway;
    private final ProdutoFeignGateway feignGateway;

    @Override
    public void cancelar(Long id) {
        gateway.findByIdAndStatusAguardandoPagamento(id, p -> {
            feignGateway.returnProdutos(
                    p.getDetalhesPedido().stream().map(DetalhePedido::getProduto).collect(Collectors.toSet()));
            p.setStatus(0);
            gateway.save(p);
        });
    }

}
