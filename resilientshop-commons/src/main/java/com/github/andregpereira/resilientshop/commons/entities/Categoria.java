package com.github.andregpereira.resilientshop.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    private Long id;
    private String nome;
    private Set<Subcategoria> subcategorias = new LinkedHashSet<>();
    private Set<Produto> produtos = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id) && Objects.equals(nome, categoria.nome) && Objects.equals(subcategorias,
                categoria.subcategorias) && Objects.equals(produtos, categoria.produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, subcategorias, produtos);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Categoria.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "nome='" + nome + "'").add("subcategorias=" + subcategorias).add("produtos=" + produtos).toString();
    }

}
