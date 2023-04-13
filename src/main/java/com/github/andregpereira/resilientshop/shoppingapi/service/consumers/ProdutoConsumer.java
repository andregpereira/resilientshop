package com.github.andregpereira.resilientshop.shoppingapi.service.consumers;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "produto-service", url = "http://localhost:8765", path = "/produtos")
public interface ProdutoConsumer {

//    @GetMapping
//    ProdutoDto listar(@PathVariable Pageable pageable);

    @GetMapping("/{id}")
    ProdutoDto consultarPorId(@PathVariable Long id);

}
