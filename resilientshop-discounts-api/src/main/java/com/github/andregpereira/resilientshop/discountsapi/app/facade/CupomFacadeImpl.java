package com.github.andregpereira.resilientshop.discountsapi.app.facade;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.service.cupom.CupomConsultaService;
import com.github.andregpereira.resilientshop.discountsapi.app.service.cupom.CupomManutencaoService;
import com.github.andregpereira.resilientshop.discountsapi.app.service.factory.ServiceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.MessageFormat;

@RequiredArgsConstructor
@Slf4j
@Component
class CupomFacadeImpl implements CupomFacade {

    private final CupomManutencaoService manutencaoService;
    private final CupomConsultaService consultaService;
    private final ServiceFactory factory;

    private static URI getUri(CupomDto dto) {
        return UriComponentsBuilder.fromPath("/cupons/{id}").buildAndExpand(dto.id()).toUri();
    }

    @Override
    public ResponseEntity<CupomDto> criar(CupomCreateDto dto) {
        CupomDto cupom = manutencaoService.criar(dto);
        log.info("Cupom criado com sucesso");
        return ResponseEntity.created(getUri(cupom)).body(cupom);
    }

    @Override
    public ResponseEntity<CupomDto> update(Long id, CupomUpdateDto dto) {
        CupomDto cupom = manutencaoService.update(id, dto);
        log.info("Cupom atualizado com sucesso");
        return ResponseEntity.ok().location(getUri(cupom)).body(cupom);
    }

    @Override
    public ResponseEntity<String> activate(Long id) {
        manutencaoService.activate(id);
        log.info("Cupom ativado com sucesso");
        return ResponseEntity.ok(MessageFormat.format("Cupom com id {0} ativado com sucesso!", id));
    }

    @Override
    public ResponseEntity<String> deactivate(Long id) {
        manutencaoService.deactivate(id);
        log.info("Cupom desativado com sucesso");
        return ResponseEntity.ok(MessageFormat.format("Cupom com id {0} desativado com sucesso!", id));
    }

    @Override
    public ResponseEntity<CupomDto> findById(Long id) {
        CupomDto cupom = consultaService.consultarPorId(id);
        log.info("Cupom com id {} encontrado", id);
        return ResponseEntity.ok(cupom);
    }

    @Override
    public ResponseEntity<CupomDto> findByCodigo(String codigo) {
        CupomDto cupom = consultaService.consultarPorCodigo(codigo);
        log.info("Cupom com código {} encontrado", codigo);
        return ResponseEntity.ok(cupom);
    }

    @Override
    public ResponseEntity<Page<CupomDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(consultaService.consultarTodos(pageable));
    }

    @Override
    public ResponseEntity<Page<CupomDto>> findAllAtivo(Pageable pageable) {
        return ResponseEntity.ok(consultaService.consultarAtivo(pageable));
    }

    @Override
    public ResponseEntity<Page<CupomDto>> findAllInativo(Pageable pageable) {
        return ResponseEntity.ok(consultaService.consultarInativo(pageable));
    }

}
