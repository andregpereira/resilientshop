package com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.StatusPedido;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException {

    public PedidoNotFoundException(String msg) {
        super(msg);
    }

    public PedidoNotFoundException(Long id) {
        super(MessageFormat.format("Opa! Não foi encontrado um pedido com o id {0}", id));
    }

    public PedidoNotFoundException(int status) {
        super(MessageFormat.format("Opa! Não foram encontrados pedidos com o status {0} ({1})", status,
                StatusPedido.getStatusPorId(status)));
    }

}
