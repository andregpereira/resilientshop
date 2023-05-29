package com.github.andregpereira.resilientshop.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subcategoria {

    private Long id;
    private String nome;
    private String descricao;
    private Categoria categoria;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Subcategoria subcategoria))
            return false;
        return Objects.equals(id, subcategoria.id) && Objects.equals(nome, subcategoria.nome) && Objects.equals(
                descricao, subcategoria.descricao) && Objects.equals(categoria, subcategoria.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, categoria);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Subcategoria.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "nome='" + nome + "'").add("descricao='" + descricao + "'").add("categoria=" + categoria).toString();
    }

}
