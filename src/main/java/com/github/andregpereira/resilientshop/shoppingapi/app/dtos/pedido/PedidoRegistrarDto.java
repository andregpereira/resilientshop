package com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.detalhepedido.DetalhePedidoRegistrarDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record PedidoRegistrarDto(@NotNull(message = "O usuário é obrigatório") @Positive(
        message = "O valor mínimo para esse campo é 1") Long idUsuario,
        @NotBlank(message = "O apelido do endereço é obrigatório") String apelidoEndereco,
        @NotNull(message = "Os detalhes do pedido são obrigatórios") List<DetalhePedidoRegistrarDto> detalhePedido) {

}
