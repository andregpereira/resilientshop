package com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DescontoRegistroDto(@NotNull(message = "O percentual é obrigatório") BigDecimal percentual,
        @NotNull(message = "O tipo de desconto é obrigatório") TipoDesconto tipoDesconto,
        @NotNull(message = "A data de expiração é obrigatória") LocalDate dataExpiracao,
        @NotNull(message = "O objeto do desconto é obrigatório") Long idObjetoDoDesconto) {

}
