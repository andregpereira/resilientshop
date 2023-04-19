package com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.StatusPedido;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException {

    public PedidoNotFoundException(String msg) {
        super(msg);
    }

    public PedidoNotFoundException(Long id) {
        super("Opa! Não foi encontrado um pedido com o id " + id);
    }

    public PedidoNotFoundException(int status) {
        super("Opa! Não foram encontrados pedidos com o status " + status + " (" + StatusPedido.getStatusPorId(
                status) + ")");
    }

}
