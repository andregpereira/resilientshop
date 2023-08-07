package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomActivateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomCreateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomDeactivateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomUpdateUc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public non-sealed class CupomManutencaoServiceImpl implements CupomManutencaoService {

    private final CupomCreateUc cupomCreateUc;
    private final CupomUpdateUc cupomUpdateUc;
    private final CupomDeactivateUc cupomDeactivateUc;
    private final CupomActivateUc cupomActivateUc;
    private final CupomServiceMapper mapper;

    @Override
    public CupomDto criar(CupomRegistroDto dto) {
        return mapper.toCupomDto(cupomCreateUc.criar(mapper.toCupom(dto)));
    }

    @Override
    public CupomDto update(Long id, CupomUpdateDto dto) {
        return mapper.toCupomDto(cupomUpdateUc.update(id, mapper.toCupom(dto)));
    }

    @Override
    public String activate(Long id) {
        return cupomActivateUc.activate(id);
    }

    @Override
    public String deactivate(Long id) {
        return cupomDeactivateUc.deactivate(id);
    }

}
