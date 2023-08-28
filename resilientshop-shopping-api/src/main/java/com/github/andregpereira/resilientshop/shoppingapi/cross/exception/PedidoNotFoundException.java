package com.github.andregpereira.resilientshop.shoppingapi.cross.exception;

import com.github.andregpereira.resilientshop.shoppingapi.app.constant.StatusPedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.text.MessageFormat;

public class PedidoNotFoundException extends RuntimeException {

    private static final HttpStatusCode status = HttpStatus.NOT_FOUND;

    public PedidoNotFoundException(String msg) {
        super(msg);
    }

    public PedidoNotFoundException(Long id) {
        super(MessageFormat.format("Opa! Não foi encontrado um pedido com o id {0}", id));
    }

    public PedidoNotFoundException(int status) {
        super(MessageFormat.format("Opa! Não foram encontrados pedidos com o status {0}",
                StatusPedido.getStatusPorId(status) != null ? StatusPedido.getStatusPorId(
                        status).toString().toLowerCase().replace("_", " ").replace("separacao", "separação")
                        : "informado"));
    }

}
