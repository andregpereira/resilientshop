package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Produto;
import jakarta.persistence.*;
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
@Entity
@Table(name = "tb_detalhes_pedidos")
@SequenceGenerator(name = "detalhe_pedido", sequenceName = "sq_detalhes_pedidos", allocationSize = 1)
public class DetalhePedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalhe_pedido")
    @Column(name = "id_detalhe_pedido")
    private Long id;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private Long idProduto;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;

    @Transient
    private Produto produto;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DetalhePedidoEntity that = (DetalhePedidoEntity) o;
        return quantidade == that.quantidade && Objects.equals(id, that.id) && Objects.equals(subtotal,
                that.subtotal) && Objects.equals(idProduto, that.idProduto) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantidade, subtotal, idProduto, produto);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DetalhePedidoEntity.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "quantidade=" + quantidade).add("subtotal=" + subtotal).add("idProduto=" + idProduto).add(
                "produto=" + produto).toString();
    }

}
