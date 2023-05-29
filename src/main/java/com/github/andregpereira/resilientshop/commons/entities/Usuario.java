package com.github.andregpereira.resilientshop.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private boolean ativo;
    private List<Endereco> enderecos;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Usuario usuario))
            return false;
        return ativo == usuario.ativo && Objects.equals(id, usuario.id) && Objects.equals(nome,
                usuario.nome) && Objects.equals(sobrenome, usuario.sobrenome) && Objects.equals(cpf,
                usuario.cpf) && Objects.equals(telefone, usuario.telefone) && Objects.equals(dataCriacao,
                usuario.dataCriacao) && Objects.equals(dataModificacao, usuario.dataModificacao) && Objects.equals(
                enderecos, usuario.enderecos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, cpf, telefone, dataCriacao, dataModificacao, ativo, enderecos);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Usuario.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "nome='" + nome + "'").add("sobrenome='" + sobrenome + "'").add("cpf='" + cpf + "'").add(
                "telefone='" + telefone + "'").add("dataCriacao=" + dataCriacao).add(
                "dataModificacao=" + dataModificacao).add("ativo=" + ativo).add("enderecos=" + enderecos).toString();
    }

}
