package com.github.andregpereira.resilientshop.shoppingapi.infra.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Produto produto = (Produto) o;
        return estoque == produto.estoque && Objects.equals(id, produto.id) && Objects.equals(sku,
                produto.sku) && Objects.equals(nome, produto.nome) && Objects.equals(descricao,
                produto.descricao) && Objects.equals(dataCriacao, produto.dataCriacao) && Objects.equals(valorUnitario,
                produto.valorUnitario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku, nome, descricao, dataCriacao, valorUnitario, estoque);
    }

}
