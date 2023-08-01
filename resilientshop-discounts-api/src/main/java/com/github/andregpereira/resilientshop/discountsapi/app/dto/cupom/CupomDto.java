package com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CupomDto(Long id,
        String codigo,
        BigDecimal percentual,
        Integer qtdMinimaProdutos,
        BigDecimal valorMinimoPedido,
        BigDecimal descontoMaximo,
        @JsonFormat(pattern = "dd/MM/uuuu") LocalDate dataCriacao,
        @JsonFormat(pattern = "dd/MM/uuuu") LocalDate dataExpiracao,
        boolean ativo) {

}
