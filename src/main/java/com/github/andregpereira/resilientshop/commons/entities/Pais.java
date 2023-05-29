package com.github.andregpereira.resilientshop.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pais {

    private Long id;
    private String nome;
    private String codigo;
    private List<Endereco> enderecos;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Pais pais))
            return false;
        return Objects.equals(id, pais.id) && Objects.equals(nome, pais.nome) && Objects.equals(codigo, pais.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, codigo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pais.class.getSimpleName() + "[", "]").add("id=" + id).add("nome='" + nome + "'")
                .add("codigo='" + codigo + "'").toString();
    }

}
