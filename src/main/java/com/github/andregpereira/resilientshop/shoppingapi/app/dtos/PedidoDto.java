package com.github.andregpereira.resilientshop.shoppingapi.app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.andregpereira.resilientshop.shoppingapi.domain.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto(Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu HH:mm") LocalDateTime dataCriacao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu HH:mm") LocalDateTime dataModificacao,
        Status status,
        BigDecimal total,
        List<DetalhesPedidoDto> detalhesPedido) {

}
