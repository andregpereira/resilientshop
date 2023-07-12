package com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DescontoRegistroDto(BigDecimal percentual,
        TipoDesconto tipoDesconto,
        LocalDate dataExpiracao,
        Long idObjetoDoDesconto) {

}
