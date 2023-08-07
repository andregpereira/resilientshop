package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomFindAllUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomFindByIdUc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public non-sealed class CupomConsultaServiceImpl implements CupomConsultaService {

    private final CupomFindAllUc cupomFindAllUc;
    private final CupomFindByIdUc cupomFindByIdUc;
    private final CupomServiceMapper mapper;

    @Override
    public Page<CupomDto> consultarTodos(Pageable pageable) {
        return cupomFindAllUc.findAll(pageable).map(mapper::toCupomDto);
    }

    @Override
    public CupomDto consultarPorId(Long id) {
        return mapper.toCupomDto(cupomFindByIdUc.findById(id));
    }

}
