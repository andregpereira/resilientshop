package com.github.andregpereira.resilientshop.discountsapi.app.rest;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.rest.facade.CupomFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@Validated
@RestController
@RequestMapping("/cupons")
public class CupomController {

    private final CupomFacade facade;

    @PostMapping
    public ResponseEntity<CupomDto> criar(@RequestBody @Valid CupomCreateDto dto) {
        log.info("Criando cupom...");
        return facade.criar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CupomDto> update(@PathVariable Long id, @RequestBody @Valid CupomUpdateDto dto) {
        log.info("Atualizando cupom...");
        return facade.update(id, dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> activate(@PathVariable Long id) {
        log.info("Ativando cupom...");
        return facade.activate(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        log.info("Desativando cupom...");
        return facade.deactivate(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CupomDto> findById(@PathVariable Long id) {
        log.info("Procurando cupom com id {}...", id);
        return facade.findById(id);
    }

    @GetMapping("/codigo")
    public ResponseEntity<CupomDto> findByCodigo(@RequestParam String codigo) {
        log.info("Procurando cupom com código {}...", codigo);
        return facade.findByCodigo(codigo);
    }

    @GetMapping
    public ResponseEntity<Page<CupomDto>> findAll(@PageableDefault(sort = "id") Pageable pageable) {
        log.info("Retornando todos os cupons");
        return facade.findAll(pageable);
    }

    @GetMapping("/ativo")
    public ResponseEntity<Page<CupomDto>> findAllAtivo(@PageableDefault(sort = "id") Pageable pageable) {
        log.info("Retornando todos os cupons ativos");
        return facade.findAllAtivo(pageable);
    }

    @GetMapping("/inativo")
    public ResponseEntity<Page<CupomDto>> findAllInativo(@PageableDefault(sort = "id") Pageable pageable) {
        log.info("Retornando todos os cupons inativos");
        return facade.findAllAtivo(pageable);
    }

}
