package com.github.andregpereira.resilientshop.shoppingapi.app.dtos.detalhepedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetalhePedidoRegistrarDto(@NotNull(message = "O produto é obrigatório") @Positive(
        message = "O valor mínimo para esse campo é 1") Long idProduto,
        @NotNull(message = "A quantidade do produto é obrigatória") @Positive(
                message = "Ops! Quantidade inválida, digite um número que não seja negativo ou zero") int quantidade) {

}
