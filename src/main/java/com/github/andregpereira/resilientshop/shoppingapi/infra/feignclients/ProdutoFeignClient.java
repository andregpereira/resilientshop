package com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "resilientshop-products-api", path = "/produtos")
public interface ProdutoFeignClient {

    @GetMapping("/{id}")
    ProdutoDto consultarPorId(@PathVariable Long id);

}
