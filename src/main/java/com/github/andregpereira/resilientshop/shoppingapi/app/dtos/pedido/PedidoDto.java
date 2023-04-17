package com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoDto(Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu HH:mm") LocalDateTime dataCriacao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu HH:mm") LocalDateTime dataModificacao,
        int status,
        BigDecimal total) {

}
