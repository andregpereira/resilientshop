package com.github.andregpereira.resilientshop.discountsapi.app.rest;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.app.service.CupomManutencaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Slf4j
@Validated
@RestController
@RequestMapping("/cupons")
public class CupomController {

    private final CupomManutencaoService manutencaoService;

    @PostMapping
    public ResponseEntity<CupomDto> registrar(@RequestBody CupomRegistroDto dto) {
        log.info("Criando cupom...");
        CupomDto cupom = manutencaoService.registrar(dto);
        log.info("Cupom criado com sucesso");
        URI uri = UriComponentsBuilder.fromPath("/cupons/{id}").buildAndExpand(cupom.id()).toUri();
        return ResponseEntity.created(uri).body(cupom);
    }

}
