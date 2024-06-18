package com.github.andregpereira.resilientshop.commons.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCredentialRegistroDto(@NotBlank(message = "O e-mail é obrigatório") @Email String email,
        @NotBlank(message = "A senha é obrigatória") @Size(message = "A senha deve ter entre 8 e 255 caracteres",
                min = 8, max = 255) String senha) {

}
