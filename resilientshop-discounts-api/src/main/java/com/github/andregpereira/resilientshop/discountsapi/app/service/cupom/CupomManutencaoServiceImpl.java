package com.github.andregpereira.resilientshop.discountsapi.app.service;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.ActivateCupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.CreateCupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.DeactivateCupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.UpdateCupom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public non-sealed class CupomManutencaoServiceImpl implements CupomManutencaoService {

    private final CreateCupom createCupom;
    private final UpdateCupom updateCupom;
    private final DeactivateCupom deactivateCupom;
    private final ActivateCupom activateCupom;
    private final CupomServiceMapper mapper;

    @Override
    public CupomDto registrar(CupomRegistroDto dto) {
        return mapper.toCupomDto(createCupom.criar(mapper.toCupom(dto)));
    }

    @Override
    public String deactivate(Long id) {
        return deactivateCupom.deactivate(id);
    }

    @Override
    public String activate(Long id) {
        return activateCupom.activate(id);
    }

    @Override
    public CupomDto update(Long id, CupomRegistroDto dto) {
        return mapper.toCupomDto(updateCupom.update(id, mapper.toCupom(dto)));
    }

}
