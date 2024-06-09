package com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.feign;

import com.github.andregpereira.resilientshop.commons.entities.Produto;

import java.util.Set;
import java.util.function.Consumer;

public interface ProdutoFeignGateway {

    Produto findProdutoById(Long id);

    void findProdutoById(Long id, Consumer<Produto> op);

    void subtract(Set<Produto> produtos);

    void returnProdutos(Set<Produto> produtos);

}
