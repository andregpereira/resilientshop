package com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.usuario.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "resilientshop-user-api", path = "/usuarios")
public interface UsuarioFeignClient {

    @GetMapping("/{id}")
    UsuarioDto consultarPorId(@PathVariable Long id);

}
