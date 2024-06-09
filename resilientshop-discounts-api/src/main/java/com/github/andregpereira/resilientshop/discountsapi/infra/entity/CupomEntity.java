package com.github.andregpereira.resilientshop.discountsapi.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cupom")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_cupons", uniqueConstraints = {@UniqueConstraint(name = "uk_codigo", columnNames = "codigo")})
@SequenceGenerator(name = "cupom", sequenceName = "sq_cupons", allocationSize = 1)
public class CupomEntity {

    @Id
    @Column(name = "id_cupom")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cupom")
    Long id;

    @Column(nullable = false)
    String codigo;

    @Column(precision = 3, scale = 2, nullable = false)
    BigDecimal percentual;

    @Column(precision = 2)
    Integer qtdMinimaProdutos;

    @Column(precision = 3, scale = 2)
    BigDecimal valorMinimoPedido;

    @Column(precision = 3, scale = 2)
    BigDecimal descontoMaximo;

    @CreatedDate
    @Column(nullable = false)
    LocalDate dataCriacao;

    @Column(nullable = false)
    LocalDate dataExpiracao;

    @Column(nullable = false)
    boolean ativo;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CupomEntity cupom))
            return false;
        return ativo == cupom.ativo && Objects.equals(id, cupom.id) && Objects.equals(codigo,
                cupom.codigo) && Objects.equals(percentual, cupom.percentual) && Objects.equals(qtdMinimaProdutos,
                cupom.qtdMinimaProdutos) && Objects.equals(valorMinimoPedido,
                cupom.valorMinimoPedido) && Objects.equals(descontoMaximo, cupom.descontoMaximo) && Objects.equals(
                dataCriacao, cupom.dataCriacao) && Objects.equals(dataExpiracao, cupom.dataExpiracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, percentual, qtdMinimaProdutos, valorMinimoPedido, descontoMaximo, dataCriacao,
                dataExpiracao, ativo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CupomEntity.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "codigo='" + codigo + "'").add("percentual=" + percentual).add(
                "qtdMinimaProdutos=" + qtdMinimaProdutos).add("valorMinimoPedido=" + valorMinimoPedido).add(
                "descontoMaximo=" + descontoMaximo).add("dataCriacao=" + dataCriacao).add(
                "dataExpiracao=" + dataExpiracao).add("ativo=" + ativo).toString();
    }

}
