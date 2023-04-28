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
public class DetalhePedido {

    private Long id;
    private int quantidade;
    private BigDecimal subtotal;
    private Long idProduto;
    private Produto produto;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DetalhePedido that = (DetalhePedido) o;
        return quantidade == that.quantidade && Objects.equals(id, that.id) && Objects.equals(subtotal,
                that.subtotal) && Objects.equals(idProduto, that.idProduto) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantidade, subtotal, idProduto, produto);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DetalhePedido.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "quantidade=" + quantidade).add("subtotal=" + subtotal).add("idProduto=" + idProduto).add(
                "produto=" + produto).toString();
    }

}
