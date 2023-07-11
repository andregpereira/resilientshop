package com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients;

import com.github.andregpereira.resilientshop.commons.entities.Endereco;
import com.github.andregpereira.resilientshop.commons.entities.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "resilientshop-user-api")
public interface UsuariosFeignClient {

    @GetMapping(value = "/usuarios/{id}")
    Usuario consultarUsuarioPorId(@PathVariable Long id);

    @GetMapping("/enderecos/{id}")
    Endereco consultarEnderecoPorId(@PathVariable Long id);

    @GetMapping("/enderecos/usuario/{idUsuario}/apelido")
    Endereco consultarEnderecoPorApelido(@PathVariable Long idUsuario, @RequestParam String apelido);

}
