package com.github.andregpereira.resilientshop.discountsapi.app.rest.facade;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.service.desconto.DescontoConsultaService;
import com.github.andregpereira.resilientshop.discountsapi.app.service.desconto.DescontoManutencaoService;
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
class DescontoFacadeImpl implements DescontoFacade {

    private final DescontoManutencaoService manutencaoService;
    private final DescontoConsultaService consultaService;
    private final ServiceFactory factory;

    private static URI getUri(DescontoDto dto) {
        return UriComponentsBuilder.fromPath("/cupons/{id}").buildAndExpand(dto.id()).toUri();
    }

    @Override
    public ResponseEntity<DescontoDto> criar(DescontoCreateDto dto) {
        DescontoDto desconto = manutencaoService.criar(dto);
        log.info("Desconto criado com sucesso");
        return ResponseEntity.created(getUri(desconto)).body(desconto);
    }

    @Override
    public ResponseEntity<DescontoDto> update(Long id, DescontoUpdateDto dto) {
        DescontoDto desconto = manutencaoService.update(id, dto);
        log.info("Desconto atualizado com sucesso");
        return ResponseEntity.ok().location(getUri(desconto)).body(desconto);
    }

    @Override
    public ResponseEntity<String> activate(Long id) {
        manutencaoService.activate(id);
        log.info("Desconto ativado com sucesso");
        return ResponseEntity.ok(MessageFormat.format("Desconto com id {0} ativado com sucesso!", id));
    }

    @Override
    public ResponseEntity<String> deactivate(Long id) {
        manutencaoService.deactivate(id);
        log.info("Desconto desativado com sucesso");
        return ResponseEntity.ok(MessageFormat.format("Desconto com id {0} desativado com sucesso!", id));
    }

    @Override
    public ResponseEntity<DescontoDto> findById(Long id) {
        DescontoDto desconto = consultaService.consultarPorId(id);
        log.info("Desconto com id {} encontrado", id);
        return ResponseEntity.ok(desconto);
    }

    @Override
    public ResponseEntity<Page<DescontoDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(consultaService.consultarTodos(pageable));
    }

    @Override
    public ResponseEntity<Page<DescontoDto>> findAllAtivo(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<Page<DescontoDto>> findAllInativo(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<Page<DescontoDto>> findByTipoDesconto(TipoDesconto tipoDesconto, Pageable pageable) {
        return ResponseEntity.ok(consultaService.consultarPorTipoDesconto(tipoDesconto, pageable));
    }

}
