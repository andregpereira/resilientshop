package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

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
    private BigDecimal subtotal;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;

    @Column(nullable = false)
    private Long idProduto;

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
                that.subtotal) && Objects.equals(idProduto, that.idProduto) && Objects.equals(pedido, that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subtotal, quantidade, idProduto, pedido);
    }

}
