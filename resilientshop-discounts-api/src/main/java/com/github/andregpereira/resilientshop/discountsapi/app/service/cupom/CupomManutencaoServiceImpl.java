package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.ActivateCupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CreateCupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.DeactivateCupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.UpdateCupom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public non-sealed class CupomManutencaoServiceImpl implements CupomManutencaoService {

    private final CreateCupom createCupom;
    private final UpdateCupom updateCupom;
    private final DeactivateCupom deactivateCupom;
    private final ActivateCupom activateCupom;
    private final CupomServiceMapper mapper;

    @Override
    public CupomDto criar(CupomRegistroDto dto) {
        return mapper.toCupomDto(createCupom.criar(mapper.toCupom(dto)));
    }

    @Override
    public CupomDto update(Long id, CupomUpdateDto dto) {
        return mapper.toCupomDto(updateCupom.update(id, mapper.toCupom(dto)));
    }

    @Override
    public String activate(Long id) {
        return activateCupom.activate(id);
    }

    @Override
    public String deactivate(Long id) {
        return deactivateCupom.deactivate(id);
    }

}
