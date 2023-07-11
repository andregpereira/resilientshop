package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence;

import com.github.andregpereira.resilientshop.commons.entities.Endereco;
import com.github.andregpereira.resilientshop.commons.entities.Usuario;
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

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private Long idEndereco;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetalhePedidoEntity> detalhePedido;

    @Transient
    private Usuario usuario;

    @Transient
    private Endereco endereco;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PedidoEntity pedido))
            return false;
        return status == pedido.status && Objects.equals(id, pedido.id) && Objects.equals(dataCriacao,
                pedido.dataCriacao) && Objects.equals(dataModificacao, pedido.dataModificacao) && Objects.equals(total,
                pedido.total) && Objects.equals(idUsuario, pedido.idUsuario) && Objects.equals(idEndereco,
                pedido.idEndereco) && Objects.equals(detalhePedido, pedido.detalhePedido) && Objects.equals(usuario,
                pedido.usuario) && Objects.equals(endereco, pedido.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataCriacao, dataModificacao, status, total, idUsuario, idEndereco, detalhePedido,
                usuario, endereco);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PedidoEntity.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "dataCriacao=" + dataCriacao).add("dataModificacao=" + dataModificacao).add("status=" + status).add(
                "total=" + total).add("idUsuario=" + idUsuario).add("idEndereco=" + idEndereco).add(
                "detalhePedido=" + detalhePedido).add("usuario=" + usuario).add("endereco=" + endereco).toString();
    }

}
