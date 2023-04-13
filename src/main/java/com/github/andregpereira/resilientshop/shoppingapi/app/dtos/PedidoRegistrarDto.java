package com.github.andregpereira.resilientshop.shoppingapi.app.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record PedidoRegistrarDto(@NotNull(message = "O usuário é obrigatório") @Positive(
        message = "O valor mínimo para esse campo é 1") Long idUsuario,
        @NotNull(message = "Os detalhes do pedido são obrigatórios") List<DetalhesPedidoRegistrarDto> detalhesPedido) {

}
