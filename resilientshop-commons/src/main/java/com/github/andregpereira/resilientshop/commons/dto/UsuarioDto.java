package com.github.andregpereira.resilientshop.commons.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.time.LocalDate;

public record UsuarioDto(Long id,
        String nome,
        String nomeSocial,
        String cpf,
        @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/uuuu") LocalDate dataNascimento,
        String email,
        String celular,
        @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/uuuu") LocalDate dataCriacao,
        @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/uuuu") LocalDate dataModificacao,
        boolean ativo) {

}
