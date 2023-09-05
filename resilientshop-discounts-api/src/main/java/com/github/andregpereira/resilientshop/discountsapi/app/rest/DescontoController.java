package com.github.andregpereira.resilientshop.discountsapi.app.rest;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.facade.DescontoFacade;
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
@RequestMapping("/descontos")
public class DescontoController {

    private final DescontoFacade facade;

    @PostMapping
    public ResponseEntity<DescontoDto> criar(@RequestBody @Valid DescontoCreateDto dto) {
        log.info("Criando desconto...");
        return facade.criar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescontoDto> update(@PathVariable Long id, @RequestBody @Valid DescontoUpdateDto dto) {
        log.info("Atualizando desconto...");
        return facade.update(id, dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> activate(@PathVariable Long id) {
        log.info("Ativando desconto...");
        return facade.activate(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        log.info("Desativando desconto...");
        return facade.deactivate(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescontoDto> findById(@PathVariable Long id) {
        log.info("Procurando desconto com id {}...", id);
        return facade.findById(id);
    }

    @GetMapping
    public ResponseEntity<Page<DescontoDto>> findAll(@PageableDefault(sort = "id") Pageable pageable) {
        log.info("Retornando todos os descontos");
        return facade.findAll(pageable);
    }

    @GetMapping("/tipo-desconto")
    public ResponseEntity<Page<DescontoDto>> findByTipoDesconto(
            @RequestParam("tipo-desconto") TipoDesconto tipoDesconto,
            @PageableDefault(sort = "tipoDesconto") Pageable pageable) {
        log.info("Retornando todos os descontos com tipo de desconto {}", tipoDesconto);
        return facade.findByTipoDesconto(tipoDesconto, pageable);
    }

}
