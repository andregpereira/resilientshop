package com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record DescontoUpdateDto(@NotNull(message = "O percentual é obrigatório") BigDecimal percentual,
        @NotNull(message = "O tipo de desconto é obrigatório") TipoDesconto tipoDesconto,
        @NotNull(message = "O id do objeto do desconto é obrigatório") Long idObjetoDoDesconto,
        @NotNull(message = "A data de expiração é obrigatória") @Future(
                message = "A data de expiração deve ser uma data futura") LocalDate dataExpiracao,
        boolean ativo) {

}
