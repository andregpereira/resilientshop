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
public class Cupom {

    Long id;
    String codigo;
    BigDecimal percentual;
    Integer qtdMinimaProdutos;
    BigDecimal valorMinimoPedido;
    BigDecimal descontoMaximo;
    LocalDate dataCriacao;
    LocalDate dataExpiracao;
    boolean ativo;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cupom cupom))
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
        return new StringJoiner(", ", Cupom.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "codigo='" + codigo + "'").add("percentual=" + percentual).add(
                "qtdMinimaProdutos=" + qtdMinimaProdutos).add("valorMinimoPedido=" + valorMinimoPedido).add(
                "descontoMaximo=" + descontoMaximo).add("dataCriacao=" + dataCriacao).add(
                "dataExpiracao=" + dataExpiracao).add("ativo=" + ativo).toString();
    }

}
