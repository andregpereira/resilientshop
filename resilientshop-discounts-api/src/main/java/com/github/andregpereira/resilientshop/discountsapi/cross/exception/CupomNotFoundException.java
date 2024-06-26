package com.github.andregpereira.resilientshop.discountsapi.cross.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.text.MessageFormat;

public class CupomNotFoundException extends ErrorResponseException {

    private static final HttpStatusCode status = HttpStatus.NOT_FOUND;

    public CupomNotFoundException(Long id) {
        super(status, asProblemDetail(MessageFormat.format("Opa! Não foi encontrado um cupom com id {0}", id)), null);
    }

    public CupomNotFoundException(Long id, boolean ativo) {
        super(status, asProblemDetail(MessageFormat.format("Opa! Não foi encontrado um cupom {1} com id {0}", id,
                ativo ? "ativo" : "inativo")), null);
    }

    public CupomNotFoundException(String codigo) {
        super(status, asProblemDetail(MessageFormat.format("Opa! Não foi encontrado um cupom com código {0}", codigo)),
                null);
    }

    private static ProblemDetail asProblemDetail(String msg) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, msg);
        pd.setTitle("Cupom não encontrado");
        pd.setType(URI.create("http://resilientshop.com/errors/not-found"));
        return pd;
    }

}
