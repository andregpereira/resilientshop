package com.github.andregpereira.resilientshop.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private Long id;
    private Long sku;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    private BigDecimal valorUnitario;
    private int estoque;
    private Subcategoria subcategoria;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Produto produto))
            return false;
        return estoque == produto.estoque && Objects.equals(id, produto.id) && Objects.equals(sku,
                produto.sku) && Objects.equals(nome, produto.nome) && Objects.equals(descricao,
                produto.descricao) && Objects.equals(dataCriacao, produto.dataCriacao) && Objects.equals(valorUnitario,
                produto.valorUnitario) && Objects.equals(subcategoria, produto.subcategoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku, nome, descricao, dataCriacao, valorUnitario, estoque, subcategoria);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Produto.class.getSimpleName() + "[", "]").add("id=" + id).add("sku=" + sku).add(
                        "nome='" + nome + "'").add("descricao='" + descricao + "'").add("dataCriacao=" + dataCriacao).add(
                        "valorUnitario=" + valorUnitario).add("estoque=" + estoque).add("subcategoria=" + subcategoria)
                .toString();
    }

}
