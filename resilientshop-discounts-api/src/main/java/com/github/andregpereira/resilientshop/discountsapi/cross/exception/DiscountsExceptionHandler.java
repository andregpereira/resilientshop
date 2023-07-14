package com.github.andregpereira.resilientshop.discountsapi.cross.exception;

import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DiscountsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CupomNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(CupomNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getFieldErrors().stream().map(DadoInvalido::new), headers, status,
                request);
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
