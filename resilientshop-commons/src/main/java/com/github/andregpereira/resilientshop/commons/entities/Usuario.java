package com.github.andregpereira.resilientshop.commons.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;

    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/uuuu")
    private LocalDate dataCriacao;

    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/uuuu")
    private LocalDate dataModificacao;

    private boolean ativo;
    private List<Endereco> enderecos;

}
