package com.github.andregpereira.resilientshop.userapi.app.controllers;

import com.github.andregpereira.resilientshop.userapi.app.dto.usuario.UsuarioAtualizacaoDto;
import com.github.andregpereira.resilientshop.userapi.app.dto.usuario.UsuarioDetalhesDto;
import com.github.andregpereira.resilientshop.userapi.app.dto.usuario.UsuarioDto;
import com.github.andregpereira.resilientshop.userapi.app.dto.usuario.UsuarioRegistroDto;
import com.github.andregpereira.resilientshop.userapi.app.services.usuario.UsuarioConsultaService;
import com.github.andregpereira.resilientshop.userapi.app.services.usuario.UsuarioManutencaoService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Controller de usuários da API de Usuários.
 *
 * @author André Garcia
 */
@RequiredArgsConstructor
@Slf4j
@Validated
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    /**
     * Injeção da dependência {@link UsuarioManutencaoService} para serviços de manutenção.
     */
    private final UsuarioManutencaoService manutencaoService;

    /**
     * Injeção da dependência {@link UsuarioConsultaService} para serviços de consulta.
     */
    private final UsuarioConsultaService usuarioConsultaService;

    /**
     * Cadastra um {@linkplain UsuarioRegistroDto usuário}.
     * Retorna um {@linkplain UsuarioDetalhesDto usuário detalhado}.
     *
     * @param dto o usuário a ser cadastrado.
     *
     * @return o usuário criado.
     */
    @PostMapping
    public ResponseEntity<UsuarioDetalhesDto> registrar(@RequestBody @Valid UsuarioRegistroDto dto) {
        log.info("Criando usuário...");
        UsuarioDetalhesDto usuario = manutencaoService.registrar(dto);
        log.info("Usuário criado com sucesso");
        URI uri = UriComponentsBuilder.fromPath("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    /**
     * Atualiza um {@linkplain UsuarioAtualizacaoDto usuário} por {@code id}.
     * Retorna um {@linkplain UsuarioDetalhesDto usuário detalhado}.
     *
     * @param id  o id do usuário a ser atualizado.
     * @param dto os dados do usuário a serem atualizados.
     *
     * @return o usuário atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDetalhesDto> atualizar(@PathVariable Long id,
            @RequestBody @Valid UsuarioAtualizacaoDto dto) {
        log.info("Atualizando usuário...");
        UsuarioDetalhesDto usuario = manutencaoService.atualizar(id, dto);
        log.info("Usuário com id {} atualizado com sucesso", id);
        URI uri = UriComponentsBuilder.fromPath("/usuarios/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.ok().location(uri).body(usuario);
    }

    /**
     * Remoção lógica de um usuário por {@code id}.
     * Retorna uma mensagem de confirmação de desativação.
     *
     * @param id o id do usuário a ser desativado.
     *
     * @return uma mensagem de confirmação de desativação.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> desativar(@PathVariable Long id) {
        log.info("Desativando usuário...");
        return ResponseEntity.ok(manutencaoService.desativar(id));
    }

    /**
     * Reativa um usuário por {@code id}.
     * Retorna uma mensagem de confirmação de reativação.
     *
     * @param id o id do usuário a ser reaativado.
     *
     * @return uma mensagem de confirmação de reativação.
     */
    @PatchMapping("/reativar/{id}")
    public ResponseEntity<String> reativar(@PathVariable Long id) {
        log.info("Reativando usuário...");
        return ResponseEntity.ok(manutencaoService.reativar(id));
    }

    /**
     * Lista todos os usuários cadastrados.
     * Retorna uma {@linkplain Page sublista} de {@linkplain UsuarioDto usuários}.
     *
     * @param pageable o pageable padrão.
     *
     * @return uma sublista de uma lista com todos os usuários cadastrados.
     */
    @GetMapping
    public ResponseEntity<Page<UsuarioDto>> listar(
            @Parameter(hidden = true) @PageableDefault(sort = "id", direction = Direction.ASC)
            Pageable pageable) {
        log.info("Listando usuários...");
        return ResponseEntity.ok(usuarioConsultaService.listar(pageable));
    }

    /**
     * Pesquisa um usuário por {@code id}.
     * Retorna um {@linkplain UsuarioDetalhesDto usuário detalhado}.
     *
     * @param id o id do usuário a ser consultado.
     *
     * @return um usuário encontrado pelo {@code id}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDetalhesDto> consultarPorId(@PathVariable Long id) {
        log.info("Consultando usuário por id {}...", id);
        return ResponseEntity.ok(usuarioConsultaService.consultarPorId(id));
    }

    /**
     * Pesquisa um usuário por {@code cpf}.
     * Retorna um {@linkplain UsuarioDetalhesDto usuário detalhado}.
     *
     * @param cpf o CPF do usuário a ser consultado.
     *
     * @return um usuário encontrado pelo {@code cpf}.
     */
    @GetMapping("/cpf")
    public ResponseEntity<UsuarioDetalhesDto> consultarPorCpf(@RequestParam
    @Pattern(message = "CPF inválido. Formatos aceitos: xxx.xxx.xxx-xx, xxxxxxxxx-xx ou xxxxxxxxxxx",
            regexp = "^(\\d{3}[.]\\d{3}[.]\\d{3}-\\d{2})|(\\d{9}-\\d{2})|(\\d{11})$") String cpf) {
        log.info("Consultando usuário por CPF...");
        return ResponseEntity.ok(usuarioConsultaService.consultarPorCpf(cpf.replaceAll("[.-]", "")));
    }

    /**
     * Pesquisa usuários por {@code nome}.
     * Retorna uma {@linkplain Page sublista} de {@linkplain UsuarioDto usuários}.
     *
     * @param nome     o nome dos usuários a serem consultados.
     * @param pageable o pageable padrão.
     *
     * @return uma sublista de uma lista com todos os usuários encontrados pelo {@code nome}.
     */
    @GetMapping("/nome")
    public ResponseEntity<Page<UsuarioDto>> consultarPorNome(
            @RequestParam @Size(message = "O nome deve ter pelo menos 2 caracteres", min = 2) String nome,
            @PageableDefault(sort = "nome", direction = Direction.ASC) Pageable pageable) {
        log.info("Consultando usuário por nome...");
        return ResponseEntity.ok(usuarioConsultaService.consultarPorNome(nome.trim(), pageable));
    }

}
