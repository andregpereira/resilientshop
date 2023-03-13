package com.github.andregpereira.resilientshop.userapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioRegistroDto(
		@NotBlank(message = "Insira o nome") @Size(message = "O nome deve ter no máximo 35 caracteres", max = 35) String nome,
		@NotBlank(message = "Insira o sobrenome") @Size(message = "O sobrenome deve ter no máximo 60 caracteres", max = 60) String sobrenome,
		@NotBlank(message = "Insira o telefone") @Size(message = "O telefone deve ter entre 8 e 20 dígitos", min = 8, max = 20) @Pattern(message = "Insira um telefone válido", regexp = "^[1-9]{2}[2-9][0-9]{5,17}$") String telefone,
		@NotBlank(message = "Insira o CPF") @Size(message = "O CPF deve ter 11 dígitos", min = 11, max = 11) String cpf) {
}
