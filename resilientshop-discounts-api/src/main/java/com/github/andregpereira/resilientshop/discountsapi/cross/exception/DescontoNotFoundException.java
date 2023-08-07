package com.github.andregpereira.resilientshop.discountsapi.cross.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.text.MessageFormat;

public class DescontoNotFoundException extends ErrorResponseException {

    private static final HttpStatusCode status = HttpStatus.NOT_FOUND;

    public DescontoNotFoundException(Long id) {
        super(status, asProblemDetail(MessageFormat.format("Opa! Não foi encontrado um desconto com id {0}", id)),
                null);
    }

    public DescontoNotFoundException(Long id, boolean ativo) {
        super(status, asProblemDetail(MessageFormat.format("Opa! Não foi encontrado um desconto {1} com id {0}", id,
                ativo ? "ativo" : "inativo")), null);
    }

    private static ProblemDetail asProblemDetail(String msg) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, msg);
        pd.setTitle("Cupom não encontrado");
        pd.setType(URI.create("http://resilientshop.com/errors/not-found"));
        return pd;
    }

}
