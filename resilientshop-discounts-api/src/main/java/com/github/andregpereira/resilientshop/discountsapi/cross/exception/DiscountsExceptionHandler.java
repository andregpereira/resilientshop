package com.github.andregpereira.resilientshop.discountsapi.cross.exception;

import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class DiscountsExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String LOG_TRATANDO_EXCECAO = "Tratando exceção: '{}'";

    @Override
    protected ResponseEntity<Object> handleErrorResponseException(ErrorResponseException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        log.warn(LOG_TRATANDO_EXCECAO, ex.toString());
        return super.handleErrorResponseException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.warn(LOG_TRATANDO_EXCECAO, ex.toString());
        ProblemDetail body = ex.getBody();
        body.setTitle("Campos inválidos");
        body.setDetail("Um ou mais campos não foram preenchidos corretamente");
        body.setProperty("errors", ex.getFieldErrors().stream().map(DadoInvalido::new));
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    private record DadoInvalido(String campo,
            String mensagem) {

        public DadoInvalido(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }

        public DadoInvalido(ConstraintViolation<?> erro, String path) {
            this(new StringBuffer(path).replace(0, path.indexOf(".") + 1, "").toString(), erro.getMessageTemplate());
        }

    }

}
