package com.github.andregpereira.resilientshop.shoppingapi.infra.entities;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum StatusPedido {
    CANCELADO(0),
    AGUARDANDO_PAGAMENTO(1),
    PAGAMENTO_APROVADO(2),
    EM_SEPARACAO(3),
    ENVIADO(4),
    ENTREGUE(5);

    private final int status;

    StatusPedido(int status) {
        this.status = status;
    }

    public static StatusPedido getStatusPorId(Integer status) {
        return Stream.of(values()).filter(sp -> sp.status == status).findFirst().orElse(null);
    }
}
