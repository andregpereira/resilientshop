package com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.detalhepedido.DetalhePedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.usuario.UsuarioDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDetalharDto(Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu HH:mm") LocalDateTime dataCriacao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu HH:mm") LocalDateTime dataModificacao,
        int status,
        BigDecimal total,
        UsuarioDto usuario,
        List<DetalhePedidoDto> detalhePedido) {

}
