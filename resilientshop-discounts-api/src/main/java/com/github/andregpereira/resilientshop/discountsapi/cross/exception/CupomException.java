package com.github.andregpereira.resilientshop.discountsapi.cross.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

@Getter
@Setter
public abstract class CupomException extends RuntimeException implements ErrorResponse {

    private final transient ProblemDetail body;

    protected CupomException(HttpStatusCode status, String msg) {
        super(msg);
        ErrorResponse.criar(this, HttpStatus.NOT_FOUND,"");
        this.body = ProblemDetail.forStatusAndDetail(status, msg);
    }

}
