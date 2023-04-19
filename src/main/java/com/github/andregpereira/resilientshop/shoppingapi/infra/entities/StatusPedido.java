package com.github.andregpereira.resilientshop.shoppingapi.infra.entities;

import lombok.Getter;

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
        for (StatusPedido sp : values()) {
            if (sp.status == status)
                return sp;
        }
        return null;
    }
}
