package com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients;

import com.github.andregpereira.resilientshop.commons.entities.Produto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.produto.ProdutoAtualizarEstoqueDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Set;

@Component
@FeignClient(name = "resilientshop-products-api")
public interface ProdutosFeignClient {

    @GetMapping("/produtos/{id}")
    Produto consultarPorId(@PathVariable Long id);

    @PutMapping("/produtos/estoque/subtrair")
    void subtrair(Set<ProdutoAtualizarEstoqueDto> dto);

    @PutMapping("/produtos/estoque/retornar")
    void retornarEstoque(Set<ProdutoAtualizarEstoqueDto> dto);

}
