package com.github.andregpereira.resilientshop.shoppingapi.infra.consumers;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "resilientshop-products-api", url = "http://localhost:8765", path = "/produtos")
public interface ProdutoConsumer {

    @GetMapping("/{id}")
    ProdutoDto consultarPorId(@PathVariable Long id);

}
