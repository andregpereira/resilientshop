package com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> erro400(FeignException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<String> erro503(FeignException.ServiceUnavailable e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Serviço indisponível");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> erro400(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Informação inválida. Verifique os dados e tente novamente");
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<String> erro400(InvalidParameterException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
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

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<String> erro404(PedidoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Stream<DadoInvalido>> erro422(MethodArgumentNotValidException e) {
        Stream<FieldError> erros = e.getFieldErrors().stream();
        return ResponseEntity.unprocessableEntity().body(erros.map(DadoInvalido::new));
    }

    private record DadoInvalido(String campo,
            String mensagem) {

        public DadoInvalido(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }

    }

}
