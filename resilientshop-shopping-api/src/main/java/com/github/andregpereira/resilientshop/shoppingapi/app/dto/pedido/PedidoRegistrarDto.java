package com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.detalhepedido.DetalhePedidoRegistrarDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record PedidoRegistrarDto(@NotNull(message = "O usuário é obrigatório") @Positive(
        message = "O valor mínimo para esse campo é 1") Long idUsuario,
        @NotBlank(message = "O apelido do endereço é obrigatório") String enderecoApelido,
        @NotNull(message = "Os detalhes do pedido são obrigatórios") Set<DetalhePedidoRegistrarDto> detalhesPedido) {

}
