package com.github.andregpereira.resilientshop.apigateway.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class GatewayController {

    @RequestMapping("/fallback/{api}")
    public Mono<ResponseEntity<String>> fallback(@PathVariable String api) {
        log.error("Erro ao acessar a API de {}. API indisponível", api);
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                "URL indisponivel no momento. Tente novamente"));
    }

}
