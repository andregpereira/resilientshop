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

/**
 * Controller de pedidos da API de Pedidos
 *
 * @author André Garcia
 */
@RequiredArgsConstructor
@Slf4j
@Validated
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    /**
     * Injeção da dependência {@link PedidoManutencaoService} para serviços de manutenção.
     */
    private final PedidoManutencaoService manutencaoService;

    /**
     * Injeção da dependência {@link PedidoConsultaService} para serviços de consulta.
     */
    private final PedidoConsultaService consultaService;

    /**
     * Cadastra um pedido. Retorna um {@link PedidoDetalharDto}.
     *
     * @param dto o pedido a ser cadastrado.
     *
     * @return Um {@link PedidoDetalharDto} com o pedido criado.
     */
    @PostMapping
    @CircuitBreaker(name = "cadastrar", fallbackMethod = "cadastrarFallbackMethod")
    public ResponseEntity<PedidoDetalharDto> criar(@RequestBody @Valid PedidoRegistrarDto dto) {
        log.info("Criando pedido...");
        PedidoDetalharDto pedido = manutencaoService.criar(dto);
        log.info("Pedido criado com sucesso");
        URI uri = UriComponentsBuilder.fromPath("/pedidos/{id}").buildAndExpand(pedido.id()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }

    /**
     * Método fallback caso uma API esteja indisponível.
     *
     * @param e a exceção a ser capturada pelo Resilience4J.
     *
     * @return Uma {@link String} com uma mensagem de erro.
     */
    public ResponseEntity<String> cadastrarFallbackMethod(FeignException.ServiceUnavailable e) {
        String api = e.getMessage().matches(".*\\buser\\b.*") ? "usuários" : "produtos";
        log.error(MessageFormat.format("Erro ao tentar acessar a API de {0}: {1}", api, e));
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Ops! Não foi possível cadastrar o pedido");
    }

    /**
     * Cancela um pedido por {@code id}. Retorna uma {@link String}.
     *
     * @param id o {@code id} do pedido a ser cancelado.
     *
     * @return Uma {@link String} com uma mensagem de confirmação de cancelamento.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        log.info("Cancelando pedido...");
        return ResponseEntity.ok(manutencaoService.cancelar(id));
    }

    /**
     * Lista todos os pedidos cadastrados. Retorna uma {@link Page} de {@link PedidoDto}.
     *
     * @param pageable o {@code pageable} padrão.
     *
     * @return Uma {@link Page} de {@link PedidoDto} com todos os pedidos cadastrados.
     */
    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listar(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        log.info("Procurando pedidos...");
        return ResponseEntity.ok(consultaService.listar(pageable));
    }

    /**
     * Lista todos os pedidos de um usuário. Retorna uma {@link Page} de {@link PedidoDto}.
     *
     * @param id       o {@code id} do usuário.
     * @param pageable o {@code pageable} padrão.
     *
     * @return Uma {@link Page} de {@link PedidoDto} com todos os pedidos do usuário.
     */
    @GetMapping("/usuario/{id}")
    @CircuitBreaker(name = "listarPorUsuario", fallbackMethod = "listarPorUsuarioFallbackMethod")
    public ResponseEntity<Page<PedidoDto>> listarPorUsuario(@PathVariable Long id,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        log.info("Procurando pedidos do usuário com id {}...", id);
        return ResponseEntity.ok(consultaService.listarPorUsuario(id, pageable));
    }

    /**
     * Método fallback caso uma API esteja indisponível.
     *
     * @param e a exceção a ser capturada pelo Resilience4J.
     *
     * @return Uma {@link String} com uma mensagem de erro.
     */
    public ResponseEntity<String> listarPorUsuarioFallbackMethod(Long id, Pageable pageable,
            FeignException.ServiceUnavailable e) {
        log.error(
                MessageFormat.format("Erro ao tentar acessar a API de usuários: id: {0} - {1} - {2}", id, pageable, e));
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                MessageFormat.format("Ops! Não foi possível consultar os pedidos do usuário com id {0}", id));
    }

    /**
     * Pesquisa um produto por {@code id}. Retorna um {@link PedidoDetalharDto}.
     *
     * @param id o {@code id} do pedido a ser consultado.
     *
     * @return Um {@link PedidoDetalharDto} com o pedido encontrado pelo {@code id}.
     */
    @GetMapping("/{id}")
    @CircuitBreaker(name = "consultarPorId", fallbackMethod = "consultarPorIdFallbackMethod")
    public ResponseEntity<PedidoDetalharDto> consultarPorId(@PathVariable Long id) {
        log.info("Pesquisando pedido com id {}...", id);
        return ResponseEntity.ok(consultaService.consultarPorId(id));
    }

    /**
     * Método fallback caso uma API esteja indisponível.
     *
     * @param e a exceção a ser capturada pelo Resilience4J.
     *
     * @return Uma {@link String} com uma mensagem de erro.
     */
    public ResponseEntity<String> consultarPorIdFallbackMethod(Long id, FeignException.ServiceUnavailable e) {
        log.error(MessageFormat.format("Erro ao tentar acessar a API de {0}: id: {1}: {2}",
                e.getMessage().matches(".*\\buser\\b.*") ? "usuários" : "produtos", id, e));
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                (MessageFormat.format("Ops! Não foi possível consultar o pedido com id {0}. Tente novamente", id)));
    }

    /**
     * Pesquisa produtos por {@code status}. Retorna uma {@link Page} de {@link PedidoDto}.
     *
     * @param status   o {@code status} dos pedidos a serem consultados.
     * @param pageable o {@code pageable} padrão.
     *
     * @return Uma {@link Page} de {@link PedidoDto} com todos os pedidos encontrados pelo {@code status} informado.
     */
    @GetMapping("/status")
    public ResponseEntity<Page<PedidoDto>> consultarPorStatus(
            @RequestParam @Min(message = "O status mínimo é 0", value = 0) @Max(message = "O status máximo é 5",
                    value = 5) int status,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        log.info("Pesquisando pedidos com status {} ({})...", StatusPedido.getStatusPorId(status).toString()
                .toLowerCase().replace("_", "").replace("separacao", "separação"), status);
        return ResponseEntity.ok(consultaService.consultarPorStatus(status, pageable));
    }

}
