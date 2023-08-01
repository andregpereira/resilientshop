package com.github.andregpereira.resilientshop.discountsapi.domain.model;

import com.github.andregpereira.resilientshop.discountsapi.domain.util.ModelHelper;
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
public class Desconto implements ModelHelper<Desconto> {

    Long id;
    BigDecimal percentual;
    String tipoDesconto;
    LocalDate dataCriacao;
    LocalDate dataExpiracao;
    boolean ativo;
    Long idObjetoDoDesconto;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Desconto desconto))
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
        return new StringJoiner(", ", Desconto.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "percentual=" + percentual).add("tipoDesconto='" + tipoDesconto + "'").add(
                "dataCriacao=" + dataCriacao).add("dataExpiracao=" + dataExpiracao).add("ativo=" + ativo).add(
                "idObjetoDoDesconto=" + idObjetoDoDesconto).toString();
    }

}
