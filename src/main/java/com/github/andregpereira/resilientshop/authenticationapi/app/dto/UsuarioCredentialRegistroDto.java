package com.github.andregpereira.resilientshop.authenticationapi.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCredentialRegistroDto(@NotBlank(message = "O nome é obrigatório") @Size(
        message = "O nome deve ter pelo menos 2 caracteres", min = 2) String nome,
        @NotBlank(message = "O e-mail é obrigatório")
        @Email
        String email,
        @NotBlank(message = "O e-mail é obrigatório") @Size(message = "A senha deve ter entre 6 e 255 caracteres",
                min = 6, max = 8) String senha) {

}
