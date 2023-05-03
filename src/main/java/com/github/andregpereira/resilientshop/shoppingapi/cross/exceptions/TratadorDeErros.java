package com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions;

import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Stream;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Stream<DadoInvalido>> erro400(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(
                e.getConstraintViolations().stream().map(cv -> new DadoInvalido(cv, cv.getPropertyPath().toString())));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> erro400(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Informação inválida. Verifique os dados e tente novamente");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> erro400(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest().body("Parâmetro inválido. Verifique e tente novamente");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<DadoInvalido> erro400(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body(
                new DadoInvalido(e.getParameterName(), "O campo " + e.getParameterName() + " é obrigatório"));
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<String> erro404(FeignException.NotFound e) {
        String mensagem = e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                mensagem.substring(mensagem.lastIndexOf("[") + 1, mensagem.lastIndexOf("]")));
    }

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<String> erro404(PedidoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Stream<DadoInvalido>> erro422(MethodArgumentNotValidException e) {
        return ResponseEntity.unprocessableEntity().body(e.getFieldErrors().stream().map(DadoInvalido::new));
    }

    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<String> erro503(FeignException.ServiceUnavailable e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("URL indisponível");
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
