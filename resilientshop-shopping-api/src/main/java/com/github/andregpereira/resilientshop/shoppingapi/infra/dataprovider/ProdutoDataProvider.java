package com.github.andregpereira.resilientshop.shoppingapi.infra.dataprovider;

import com.github.andregpereira.resilientshop.commons.entities.Produto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.produto.ProdutoAtualizarEstoqueDto;
import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.feign.ProdutoFeignGateway;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclient.ProdutosFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProdutoDataProvider implements ProdutoFeignGateway {

    /**
     * Injeção da dependência {@link ProdutosFeignClient} para realizar
     * requisições na API de Produtos.
     */
    private final ProdutosFeignClient produtosFeignClient;

    @Override
    public Produto findProdutoById(Long id) {
        return produtosFeignClient.findProdutoById(id);
    }

    @Override
    public void findProdutoById(Long id, Consumer<Produto> op) {
        op.accept(produtosFeignClient.findProdutoById(id));
    }

    @Override
    public void subtract(Set<Produto> produtos) {
        produtosFeignClient.subtrair(
                produtos.stream().map(p -> new ProdutoAtualizarEstoqueDto(p.getId(), p.getEstoque())).collect(
                        Collectors.toSet()));
    }

    @Override
    public void returnProdutos(Set<Produto> produtos) {
        produtosFeignClient.retornarEstoque(
                produtos.stream().map(p -> new ProdutoAtualizarEstoqueDto(p.getId(), p.getEstoque())).collect(
                        Collectors.toSet()));
    }

}
