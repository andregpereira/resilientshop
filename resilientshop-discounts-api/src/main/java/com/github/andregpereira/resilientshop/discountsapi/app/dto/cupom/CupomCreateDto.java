package com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record CupomCreateDto(@NotBlank(message = "O código é obrigatório") String codigo,
        @NotNull(message = "O percentual é obrigatório") BigDecimal percentual,
        Integer qtdMinimaProdutos,
        BigDecimal valorMinimoPedido,
        BigDecimal descontoMaximo,
        @NotNull(message = "A data de expiração é obrigatória") @Future(
                message = "A data de expiração deve ser uma data futura") LocalDate dataExpiracao) {

}
