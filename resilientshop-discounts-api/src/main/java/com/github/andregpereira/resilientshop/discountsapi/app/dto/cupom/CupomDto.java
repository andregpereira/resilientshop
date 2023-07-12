package com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CupomDto(Long id,
        String codigo,
        Float percentual,
        Integer qtdMinimaProdutos,
        BigDecimal valorMinimoPedido,
        BigDecimal descontoMaximo,
        LocalDate dataCriacao,
        LocalDate dataExpiracao,
        boolean ativo) {

}
