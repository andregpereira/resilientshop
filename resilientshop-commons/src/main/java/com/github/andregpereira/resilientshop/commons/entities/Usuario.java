package com.github.andregpereira.resilientshop.commons.entities;

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
    private String nomeSocial;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String celular;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private boolean ativo;
    private List<Endereco> enderecos;

}
