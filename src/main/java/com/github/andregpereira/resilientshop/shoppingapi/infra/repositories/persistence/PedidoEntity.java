package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence;

import jakarta.persistence.*;
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
@Entity
@Table(name = "tb_pedidos")
@SequenceGenerator(name = "pedido", sequenceName = "sq_pedidos", allocationSize = 1)
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido")
    @Column(name = "id_pedido")
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataModificacao;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<DetalhePedidoEntity> detalhePedido;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PedidoEntity pedido = (PedidoEntity) o;
        return Objects.equals(id, pedido.id) && Objects.equals(dataCriacao, pedido.dataCriacao) && Objects.equals(
                dataModificacao, pedido.dataModificacao) && status == pedido.status && Objects.equals(total,
                pedido.total) && Objects.equals(detalhePedido, pedido.detalhePedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCriacao, dataModificacao, status, total, detalhePedido);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PedidoEntity.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "dataCriacao=" + dataCriacao).add("dataModificacao=" + dataModificacao).add("status=" + status).add(
                "total=" + total).add("detalhePedido=" + detalhePedido).toString();
    }

}
