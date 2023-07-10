package com.github.andregpereira.resilientshop.discountsapi.app.service;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.cupom.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.CreateCupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public non-sealed class CupomManutencaoServiceImpl implements CupomManutencaoService {

    private final CreateCupom createCupom;
    private final CupomServiceMapper mapper;

    @Override
    public CupomDto registrar(CupomRegistroDto dto) {
        return mapper.toCupomDto(createCupom.criar(mapper.toCupom(dto)));
    }

}
