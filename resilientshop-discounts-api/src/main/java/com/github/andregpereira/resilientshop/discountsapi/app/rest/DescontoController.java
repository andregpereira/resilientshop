package com.github.andregpereira.resilientshop.discountsapi.app.rest;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.service.desconto.DescontoConsultaService;
import com.github.andregpereira.resilientshop.discountsapi.app.service.desconto.DescontoManutencaoService;
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
@RequestMapping("/descontos")
public class DescontoController {

    private static final String PATH = "descontos/{id}";
    private final DescontoManutencaoService manutencaoService;
    private final DescontoConsultaService consultaService;

    @PostMapping
    public ResponseEntity<DescontoDto> criar(@RequestBody @Valid DescontoCreateDto dto) {
        log.info("Criando desconto...");
        DescontoDto desconto = manutencaoService.criar(dto);
        log.info("Desconto criado com sucesso");
        URI uri = UriComponentsBuilder.fromPath(String.format("/%s", PATH)).buildAndExpand(desconto.id()).toUri();
        return ResponseEntity.created(uri).body(desconto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescontoDto> update(@PathVariable Long id, @RequestBody @Valid DescontoCreateDto dto) {
        log.info("Atualizando desconto...");
        DescontoDto desconto = manutencaoService.update(id, dto);
        log.info("Desconto atualizado com sucesso");
        URI uri = UriComponentsBuilder.fromPath(String.format("/%s", PATH)).buildAndExpand(id).toUri();
        return ResponseEntity.ok().location(uri).body(desconto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> activate(@PathVariable Long id) {
        log.info("Ativando desconto...");
        manutencaoService.activate(id);
        log.info("Desconto ativado com sucesso");
        return ResponseEntity.ok(MessageFormat.format("Desconto com id {0} ativado com sucesso!", id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        log.info("Desativando desconto...");
        manutencaoService.deactivate(id);
        log.info("Desconto desativado com sucesso");
        return ResponseEntity.ok(MessageFormat.format("Desconto com id {0} desativado com sucesso!", id));
    }

    @GetMapping
    public ResponseEntity<Page<DescontoDto>> findAll(@PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(consultaService.consultarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescontoDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.consultarPorId(id));
    }

    @GetMapping("/tipo-desconto")
    public ResponseEntity<Page<DescontoDto>> findByTipoDesconto(
            @RequestParam("tipo-desconto") TipoDesconto tipoDesconto,
            @PageableDefault(sort = "tipoDesconto") Pageable pageable) {
        return ResponseEntity.ok(consultaService.consultarPorTipoDesconto(tipoDesconto.toString(), pageable));
    }

}
