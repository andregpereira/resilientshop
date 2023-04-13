package com.github.andregpereira.resilientshop.shoppingapi.service.consumers;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "usuario-service", url = "http://localhost:8763", path = "/usuarios")
public interface UsuarioConsumer {

    @GetMapping("/{id}")
    UsuarioDto consultarPorId(@PathVariable Long id);

}
