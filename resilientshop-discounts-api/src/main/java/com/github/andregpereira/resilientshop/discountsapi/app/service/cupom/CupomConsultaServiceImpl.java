package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.*;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.factory.UseCaseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CupomConsultaServiceImpl implements CupomConsultaService {

    private final UseCaseFactory factory;
    private final CupomServiceMapper mapper;

    @Override
    public CupomDto consultarPorId(Long id) {
        return mapper.toCupomDto(factory.getUseCase(CupomFindByIdUc.class).findById(id));
    }

    @Override
    public CupomDto consultarPorCodigo(String codigo) {
        return mapper.toCupomDto(factory.getUseCase(CupomFindByCodigoUc.class).findByCodigo(codigo));
    }

    @Override
    public Page<CupomDto> consultarTodos(Pageable pageable) {
        return factory.getUseCase(CupomFindAllUc.class).findAll(pageable).map(mapper::toCupomDto);
    }

    @Override
    public Page<CupomDto> consultarAtivo(Pageable pageable) {
        return factory.getUseCase(CupomFindAllAtivoUc.class).findAllAtivo(pageable).map(mapper::toCupomDto);
    }

    @Override
    public Page<CupomDto> consultarInativo(Pageable pageable) {
        return factory.getUseCase(CupomFindAllInativoUc.class).findAllInativo(pageable).map(mapper::toCupomDto);
    }

}
