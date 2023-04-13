package com.github.andregpereira.resilientshop.shoppingapi.domain.entities;

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
@SequenceGenerator(name = "detalhes_pedido", sequenceName = "sq_detalhes_pedidos", allocationSize = 1)
public class DetalhesPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalhes_pedido")
    @Column(name = "id_detalhes_pedido")
    private Long id;

    @Column(nullable = false, length = 45)
    private String nomeProduto;

    @Column(nullable = false)
    private BigDecimal valorUnitario;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DetalhesPedido that = (DetalhesPedido) o;
        return quantidade == that.quantidade && Objects.equals(id, that.id) && Objects.equals(nomeProduto,
                that.nomeProduto) && Objects.equals(valorUnitario, that.valorUnitario) && Objects.equals(pedido,
                that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeProduto, valorUnitario, quantidade, pedido);
    }

}
