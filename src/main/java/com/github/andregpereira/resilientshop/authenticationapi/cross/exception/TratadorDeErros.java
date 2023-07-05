package com.github.andregpereira.resilientshop.authenticationapi.cross.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Stream;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Object> erro403(DisabledException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                "Não foi possível realizar o login. O usuário está desativado");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> erro403(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("E-mail ou senha incorretos");
    }

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
        return ResponseEntity.badRequest().body(new DadoInvalido(e.getParameterName(),
                "O campo " + e.getParameterName().replace("cpf", "CPF") + " é obrigatório"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Stream<DadoInvalido>> erro422(MethodArgumentNotValidException e) {
        return ResponseEntity.unprocessableEntity().body(e.getFieldErrors().stream().map(DadoInvalido::new));
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
