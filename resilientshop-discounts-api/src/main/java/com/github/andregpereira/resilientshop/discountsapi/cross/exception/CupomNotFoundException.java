package com.github.andregpereira.resilientshop.discountsapi.cross.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.text.MessageFormat;

public class CupomNotFoundException extends CupomException {

    private static final HttpStatusCode status = HttpStatus.NOT_FOUND;

    public CupomNotFoundException(Long id) {
        super(status, MessageFormat.format("Opa! Não foi encontrado um cupom com id {0}", id));
    }

    public CupomNotFoundException(Long id, boolean ativo) {
        super(status, MessageFormat.format("Opa! Não foi encontrado um cupom {1} com id {0}", id,
                ativo ? "ativo" : "inativo"));
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return status;
    }

}
