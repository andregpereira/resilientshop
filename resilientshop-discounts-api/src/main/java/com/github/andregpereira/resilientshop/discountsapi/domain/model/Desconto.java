package com.github.andregpereira.resilientshop.discountsapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Desconto {

    Long id;
    BigDecimal percentual;
    String tipoDesconto;
    Long idObjetoDoDesconto;
    LocalDate dataCriacao;
    LocalDate dataExpiracao;
    boolean ativo;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Desconto desconto))
            return false;
        return ativo == desconto.ativo && Objects.equals(id, desconto.id) && Objects.equals(percentual,
                desconto.percentual) && Objects.equals(tipoDesconto, desconto.tipoDesconto) && Objects.equals(
                idObjetoDoDesconto, desconto.idObjetoDoDesconto) && Objects.equals(dataCriacao,
                desconto.dataCriacao) && Objects.equals(dataExpiracao, desconto.dataExpiracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, percentual, tipoDesconto, idObjetoDoDesconto, dataCriacao, dataExpiracao, ativo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Desconto.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "percentual=" + percentual).add("tipoDesconto='" + tipoDesconto + "'").add(
                "idObjetoDoDesconto=" + idObjetoDoDesconto).add("dataCriacao=" + dataCriacao).add(
                "dataExpiracao=" + dataExpiracao).add("ativo=" + ativo).toString();
    }

}
