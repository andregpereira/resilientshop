package com.github.andregpereira.resilientshop.discountsapi.app.rest;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
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
import java.text.MessageFormat;

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
    public ResponseEntity<CupomDto> criar(@RequestBody @Valid CupomCreateDto dto) {
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
        manutencaoService.activate(id);
        log.info("Cupom ativado com sucesso");
        return ResponseEntity.ok(MessageFormat.format("Cupom com id {0} ativado com sucesso!", id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        log.info("Desativando cupom...");
        manutencaoService.deactivate(id);
        log.info("Cupom desativado com sucesso");
        return ResponseEntity.ok(MessageFormat.format("Cupom com id {0} desativado com sucesso!", id));
    }

    @GetMapping
    public ResponseEntity<Page<CupomDto>> findAll(@PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(consultaService.consultarTodos(pageable));
    }

    @GetMapping("/ativo")
    public ResponseEntity<Page<CupomDto>> findAllAtivo(@PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(consultaService.consultarAtivo(pageable));
    }

    @GetMapping("/inativo")
    public ResponseEntity<Page<CupomDto>> findAllInativo(@PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(consultaService.consultarInativo(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CupomDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.consultarPorId(id));
    }

    @GetMapping("/codigo")
    public ResponseEntity<CupomDto> findByCodigo(@RequestParam String codigo) {
        return ResponseEntity.ok(consultaService.consultarPorCodigo(codigo));
    }

}
