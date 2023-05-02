package com.github.andregpereira.resilientshop.shoppingapi.app.controllers;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.services.PedidoConsultaService;
import com.github.andregpereira.resilientshop.shoppingapi.app.services.PedidoManutencaoService;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.enums.StatusPedido;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoManutencaoService manutencaoService;
    private final PedidoConsultaService consultaService;

    @PostMapping
    @CircuitBreaker(name = "cadastrar", fallbackMethod = "cadastrarFallbackMethod")
    public ResponseEntity<PedidoDetalharDto> criar(@RequestBody @Valid PedidoRegistrarDto dto,
            UriComponentsBuilder uriBuilder) {
        log.info("Criando pedido...");
        PedidoDetalharDto pedido = manutencaoService.criar(dto);
        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.id()).toUri();
        log.info("Pedido criado com sucesso");
        return ResponseEntity.created(uri).body(pedido);
    }

    public ResponseEntity<String> cadastrarFallbackMethod(Exception e) {
        log.error(MessageFormat.format("Erro ao tentar acessar a API de produtos: {0}", e));
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Ops! Não foi possível cadastrar o pedido");
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
    @CircuitBreaker(name = "consultarPorId", fallbackMethod = "consultarPorIdFallbackMethod")
    public ResponseEntity<PedidoDetalharDto> consultarPorId(@PathVariable Long id) {
        log.info("Pesquisando pedido com id {}...", id);
        return ResponseEntity.ok(consultaService.consultarPorId(id));
    }

    public ResponseEntity<String> consultarPorIdFallbackMethod(Long id, Exception e) {
        log.error(MessageFormat.format("Erro ao tentar acessar a API de produtos: id :{0} {1}", id, e));
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                (MessageFormat.format("Ops! Não foi possível consultar o pedido com id {0}", id)));
    }

    @GetMapping("/status")
    public ResponseEntity<Page<PedidoDto>> consultarPorStatus(
            @RequestParam @Min(message = "O status mínimo é 0", value = 0) @Max(message = "O status máximo é 5",
                    value = 5) int status,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        log.info("Pesquisando pedidos com status {} ({})...", StatusPedido.getStatusPorId(status), status);
        return ResponseEntity.ok(consultaService.consultarPorStatus(status, pageable));
    }

}
