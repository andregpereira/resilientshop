package com.github.andregpereira.resilientshop.shoppingapi.infra.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private Long id;
    private String nome;
    private BigDecimal valorUnitario;
    private int estoque;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Produto produto = (Produto) o;
        return estoque == produto.estoque && Objects.equals(id, produto.id) && Objects.equals(nome,
                produto.nome) && Objects.equals(valorUnitario, produto.valorUnitario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valorUnitario, estoque);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Produto.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "nome='" + nome + "'").add("valorUnitario=" + valorUnitario).add("estoque=" + estoque).toString();
    }

}
