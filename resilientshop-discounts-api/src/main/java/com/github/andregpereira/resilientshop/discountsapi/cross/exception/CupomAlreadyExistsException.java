package com.github.andregpereira.resilientshop.discountsapi.cross.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.text.MessageFormat;

public class CupomAlreadyExistsException extends ErrorResponseException {

    private static final HttpStatusCode status = HttpStatus.CONFLICT;

    public CupomAlreadyExistsException(String codigo) {
        super(status,
                asProblemDetail(MessageFormat.format("Opa! Já existe um cupom cadastrado com o código {0}", codigo)),
                null);
    }

    private static ProblemDetail asProblemDetail(String msg) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, msg);
        pd.setTitle("Cupom não encontrado");
        pd.setType(URI.create("http://resilientshop.com/errors/conflict"));
        return pd;
    }

}
