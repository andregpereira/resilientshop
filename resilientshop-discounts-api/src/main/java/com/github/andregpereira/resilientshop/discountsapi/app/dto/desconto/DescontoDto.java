package com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DescontoDto(Long id,
        BigDecimal percentual,
        TipoDesconto tipoDesconto,
        @JsonFormat(pattern = "dd/MM/uuuu") LocalDate dataCriacao,
        @JsonFormat(pattern = "dd/MM/uuuu") LocalDate dataExpiracao,
        boolean ativo,
        Long idObjetoDoDesconto) {

}
