package com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoAtualizarEstoqueDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Component
@FeignClient(name = "resilientshop-products-api")
public interface ProdutosFeignClient {

    @GetMapping("/produtos/{id}")
    ProdutoDto consultarPorId(@PathVariable Long id);

    @PutMapping("/produtos/estoque/subtrair")
    void subtrair(List<ProdutoAtualizarEstoqueDto> dto);

    @PutMapping("/produtos/estoque/retornar")
    void retornarEstoque(List<ProdutoAtualizarEstoqueDto> dto);

}
