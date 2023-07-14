package com.github.andregpereira.resilientshop.discountsapi.cross.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CupomNotFoundException extends RuntimeException {

    public CupomNotFoundException() {
        super("Ops! Não há cupons cadastrados");
    }

    public CupomNotFoundException(Long id) {
        super(MessageFormat.format("Opa! Não foi encontrado um cupom com id {0}", id));
    }

    public CupomNotFoundException(Long id, boolean ativo) {
        super(MessageFormat.format("Opa! Não foi encontrado um cupom {1} com id {0}", id, ativo ? "ativo" : "inativo"));
    }

}
