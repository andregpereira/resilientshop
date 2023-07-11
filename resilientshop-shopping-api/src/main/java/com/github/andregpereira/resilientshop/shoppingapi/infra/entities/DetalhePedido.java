package com.github.andregpereira.resilientshop.shoppingapi.infra.entities;

import com.github.andregpereira.resilientshop.commons.entities.Produto;
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
        if (!(o instanceof DetalhePedido detalhePedido))
            return false;
        return quantidade == detalhePedido.quantidade && Objects.equals(id, detalhePedido.id) && Objects.equals(
                subtotal, detalhePedido.subtotal) && Objects.equals(idProduto,
                detalhePedido.idProduto) && Objects.equals(produto, detalhePedido.produto);
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
