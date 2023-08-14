package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CupomConsultaServiceImpl implements CupomConsultaService {

    private final CupomFindAllUc findAllUc;
    private final CupomFindAllAtivoUc findAllAtivoUc;
    private final CupomFindAllInativoUc findAllInativoUc;
    private final CupomFindByIdUc findByIdUc;
    private final CupomFindByCodigoUc findByCodigoUc;
    private final CupomServiceMapper mapper;

    @Override
    public Page<CupomDto> consultarTodos(Pageable pageable) {
        return findAllUc.findAll(pageable).map(mapper::toCupomDto);
    }

    @Override
    public Page<CupomDto> consultarAtivo(Pageable pageable) {
        return findAllAtivoUc.findAllAtivo(pageable).map(mapper::toCupomDto);
    }

    @Override
    public Page<CupomDto> consultarInativo(Pageable pageable) {
        return findAllInativoUc.findAllInativo(pageable).map(mapper::toCupomDto);
    }

    @Override
    public CupomDto consultarPorId(Long id) {
        return mapper.toCupomDto(findByIdUc.findById(id));
    }

    @Override
    public CupomDto consultarPorCodigo(String codigo) {
        return mapper.toCupomDto(findByCodigoUc.findByCodigo(codigo));
    }

}
