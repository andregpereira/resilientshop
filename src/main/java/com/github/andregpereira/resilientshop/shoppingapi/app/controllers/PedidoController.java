package com.github.andregpereira.resilientshop.shoppingapi.app.controllers;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.services.PedidoConsultaService;
import com.github.andregpereira.resilientshop.shoppingapi.app.services.PedidoManutencaoService;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.enums.StatusPedido;
import feign.FeignException;
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

    private static final String REGEX_USUARIO = ".*\\buser\\b.*";
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

    public ResponseEntity<String> cadastrarFallbackMethod(FeignException.ServiceUnavailable e) {
        log.error(MessageFormat.format("Erro ao tentar acessar a API de {0}: {1}",
                e.getMessage().matches(REGEX_USUARIO) ? "usuários" : "produtos", e));
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Ops! Não foi possível cadastrar o pedido");
    }

    public ResponseEntity<String> cadastrarFallbackMethod(FeignException.NotFound e) {
        log.error(MessageFormat.format("{0} nao encontrado: {1}",
                e.getMessage().matches(REGEX_USUARIO) ? "Usuário" : "Produto", e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Ops! Não foi possível cadastrar o pedido. O usuário não foi encontrado");
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

    @GetMapping("/usuarios/{id}")
    @CircuitBreaker(name = "listarPorUsuario", fallbackMethod = "listarPorUsuarioFallbackMethod")
    public ResponseEntity<Page<PedidoDto>> listarPorUsuario(@PathVariable Long id,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        log.info("Procurando pedidos...");
        return ResponseEntity.ok(consultaService.listarPorUsuario(id, pageable));
    }

    public ResponseEntity<String> listarPorUsuarioFallbackMethod(Long id, Pageable pageable,
            FeignException.ServiceUnavailable e) {
        log.error(
                MessageFormat.format("Erro ao tentar acessar a API de usuários: id: {0} - {1} - {2}", id, pageable, e));
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                MessageFormat.format("Ops! Não foi possível consultar os pedidos do usuário com id {0}", id));
    }

    public ResponseEntity<String> listarPorUsuarioFallbackMethod(Long id, Pageable pageable,
            FeignException.NotFound e) {
        log.error(MessageFormat.format("Usuário com id {0} não encontrado: {1} - {2}", id, pageable, e));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((MessageFormat.format(
                "Ops! Não foi possível consultar os pedidos do usuário com id {0}. O usuário não existe", id)));
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "consultarPorId", fallbackMethod = "consultarPorIdFallbackMethod")
    public ResponseEntity<PedidoDetalharDto> consultarPorId(@PathVariable Long id) {
        log.info("Pesquisando pedido com id {}...", id);
        return ResponseEntity.ok(consultaService.consultarPorId(id));
    }

    public ResponseEntity<String> consultarPorIdFallbackMethod(Long id, FeignException.ServiceUnavailable e) {
        log.error(MessageFormat.format("Erro ao tentar acessar a API de {0}: id: {1}: {2}",
                e.getMessage().matches(REGEX_USUARIO) ? "usuários" : "produtos", id, e));
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
