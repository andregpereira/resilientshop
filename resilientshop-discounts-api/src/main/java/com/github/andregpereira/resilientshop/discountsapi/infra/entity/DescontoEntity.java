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
@Entity(name = "desconto")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_descontos")
@SequenceGenerator(name = "desconto", sequenceName = "sq_descontos", allocationSize = 1)
public class DescontoEntity {

    @Id
    @Column(name = "id_desconto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "desconto")
    Long id;

    @Column(precision = 3, scale = 2, nullable = false)
    BigDecimal percentual;

    @Column(nullable = false)
    String tipoDesconto;

    @CreatedDate
    @Column(nullable = false)
    LocalDate dataCriacao;

    @Column(nullable = false)
    LocalDate dataExpiracao;

    @Column(nullable = false)
    boolean ativo;

    @Column(nullable = false)
    Long idObjetoDoDesconto;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DescontoEntity desconto))
            return false;
        return ativo == desconto.ativo && Objects.equals(id, desconto.id) && Objects.equals(percentual,
                desconto.percentual) && Objects.equals(tipoDesconto, desconto.tipoDesconto) && Objects.equals(
                dataCriacao, desconto.dataCriacao) && Objects.equals(dataExpiracao,
                desconto.dataExpiracao) && Objects.equals(idObjetoDoDesconto, desconto.idObjetoDoDesconto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, percentual, tipoDesconto, dataCriacao, dataExpiracao, ativo, idObjetoDoDesconto);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DescontoEntity.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "percentual=" + percentual).add("tipoDesconto=" + tipoDesconto).add("dataCriacao=" + dataCriacao).add(
                "dataExpiracao=" + dataExpiracao).add("ativo=" + ativo).add(
                "idObjetoDoDesconto=" + idObjetoDoDesconto).toString();
    }

}
