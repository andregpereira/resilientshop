package com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.endereco.EnderecoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.usuario.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "resilientshop-user-api")
public interface UsuariosFeignClient {

    @GetMapping("/usuarios/{id}")
    UsuarioDto consultarUsuarioPorId(@PathVariable Long id);

    @GetMapping("/enderecos/{id}/usuario/{idUsuario}")
    EnderecoDto consultarEnderecoPorId(@PathVariable Long id, @PathVariable Long idUsuario);

    @GetMapping("/enderecos/usuario/{idUsuario}/apelido")
    EnderecoDto consultarEnderecoPorApelido(@PathVariable Long idUsuario, @RequestParam String apelido);

}
