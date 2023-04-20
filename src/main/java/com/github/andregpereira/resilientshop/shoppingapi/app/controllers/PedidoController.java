package com.github.andregpereira.resilientshop.shoppingapi.app.controllers;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.services.PedidoConsultaService;
import com.github.andregpereira.resilientshop.shoppingapi.app.services.PedidoManutencaoService;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.StatusPedido;
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
    public ResponseEntity<PedidoDetalharDto> criar(@RequestBody @Valid PedidoRegistrarDto dto,
            UriComponentsBuilder uriBuilder) {
        log.info("Criando pedido...");
        PedidoDetalharDto pedido = manutencaoService.criar(dto);
        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.id()).toUri();
        log.info("Pedido criado com sucesso");
        return ResponseEntity.created(uri).body(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        log.info("Cancelando pedido...");
        return ResponseEntity.ok(manutencaoService.cancelar(id));
    }

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listar(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        log.info("Procurando pedidos...");
        return ResponseEntity.ok(consultaService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalharDto> consultarPorId(@PathVariable Long id) {
        log.info("Pesquisando pedido com id {}...", id);
        return ResponseEntity.ok(consultaService.consultarPorId(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Page<PedidoDto>> consultarPorStatus(@PathVariable int status,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        log.info("Pesquisando pedidos com status {} ({})...", StatusPedido.getStatusPorId(status), status);
        return ResponseEntity.ok(consultaService.consultarPorStatus(status, pageable));
    }

}
