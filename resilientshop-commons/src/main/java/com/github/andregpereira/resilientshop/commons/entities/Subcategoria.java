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
public class Subcategoria {

    private Long id;
    private String nome;
    private String descricao;
    private Categoria categoria;
    private Set<Produto> produtos = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Subcategoria that = (Subcategoria) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(descricao,
                that.descricao) && Objects.equals(categoria, that.categoria) && Objects.equals(produtos, that.produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, categoria, produtos);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Subcategoria.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "nome='" + nome + "'").add("descricao='" + descricao + "'").add("categoria=" + categoria).add(
                "produtos=" + produtos).toString();
    }

}
