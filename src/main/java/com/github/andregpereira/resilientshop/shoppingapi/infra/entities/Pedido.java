package com.github.andregpereira.resilientshop.shoppingapi.infra.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private int status;
    private BigDecimal total;
    private List<DetalhePedido> detalhePedido;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pedido pedido = (Pedido) o;
        return status == pedido.status && Objects.equals(id, pedido.id) && Objects.equals(dataCriacao,
                pedido.dataCriacao) && Objects.equals(dataModificacao, pedido.dataModificacao) && Objects.equals(total,
                pedido.total) && Objects.equals(detalhePedido, pedido.detalhePedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCriacao, dataModificacao, status, total, detalhePedido);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pedido.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "dataCriacao=" + dataCriacao).add("dataModificacao=" + dataModificacao).add("status=" + status).add(
                "total=" + total).add("detalhePedido=" + detalhePedido).toString();
    }

}
