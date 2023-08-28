package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence;

import com.github.andregpereira.resilientshop.commons.entities.Produto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
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
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DetalhePedidoEntity detalhePedido))
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy hibernateProxy
                ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy
                ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        return getId() != null && Objects.equals(getId(), detalhePedido.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy hibernateProxy
                ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}
