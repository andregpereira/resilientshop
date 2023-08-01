package com.github.andregpereira.resilientshop.discountsapi.app.rest;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.service.cupom.CupomConsultaService;
import com.github.andregpereira.resilientshop.discountsapi.app.service.cupom.CupomManutencaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Slf4j
@Validated
@RestController
@RequestMapping("/cupons")
public class CupomController {

    private static final String PATH = "cupons/{id}";
    private final CupomManutencaoService manutencaoService;
    private final CupomConsultaService consultaService;

    @PostMapping
    public ResponseEntity<CupomDto> criar(@RequestBody @Valid CupomRegistroDto dto) {
        log.info("Criando cupom...");
        CupomDto cupom = manutencaoService.criar(dto);
        log.info("Cupom criado com sucesso");
        URI uri = UriComponentsBuilder.fromPath(String.format("/%s", PATH)).buildAndExpand(cupom.id()).toUri();
        return ResponseEntity.created(uri).body(cupom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CupomDto> update(@PathVariable Long id, @RequestBody @Valid CupomUpdateDto dto) {
        log.info("Atualizando cupom...");
        CupomDto cupom = manutencaoService.update(id, dto);
        log.info("Cupom atualizado com sucesso");
        URI uri = UriComponentsBuilder.fromPath(String.format("/%s", PATH)).buildAndExpand(id).toUri();
        return ResponseEntity.ok().location(uri).body(cupom);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> activate(@PathVariable Long id) {
        log.info("Ativando cupom...");
        String resposta = manutencaoService.activate(id);
        log.info("Cupom ativado com sucesso");
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        log.info("Desativando cupom...");
        String resposta = manutencaoService.deactivate(id);
        log.info("Cupom desativado com sucesso");
        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<Page<CupomDto>> findAll(@PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(consultaService.consultarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CupomDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.consultarPorId(id));
    }

}
