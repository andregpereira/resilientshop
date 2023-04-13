package com.github.andregpereira.resilientshop.shoppingapi.app.controllers;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.service.PedidoConsultaService;
import com.github.andregpereira.resilientshop.shoppingapi.service.PedidoManutencaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoManutencaoService manutencaoService;
    private final PedidoConsultaService consultaService;

    @PostMapping
    public ResponseEntity<PedidoDto> criar(@RequestBody @Valid PedidoRegistrarDto dto,
            UriComponentsBuilder uriBuilder) {
        log.info("Criando pedido...");
        PedidoDto pedido = manutencaoService.criar(dto);
        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.id()).toUri();
        log.info("Pedido criado com sucesso");
        return ResponseEntity.created(uri).body(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(manutencaoService.cancelar(id));
    }

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listar(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(consultaService.listar(pageable));
    }

}
