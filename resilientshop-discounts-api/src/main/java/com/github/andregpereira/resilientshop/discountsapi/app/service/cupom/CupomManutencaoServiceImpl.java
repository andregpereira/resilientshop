package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
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
public class CupomManutencaoServiceImpl implements CupomManutencaoService {

    private final CupomCreateUc createUc;
    private final CupomUpdateUc updateUc;
    private final CupomDeactivateUc deactivateUc;
    private final CupomActivateUc activateUc;
    private final CupomServiceMapper mapper;

    @Override
    public CupomDto criar(CupomCreateDto dto) {
        return mapper.toCupomDto(createUc.criar(mapper.toCupom(dto)));
    }

    @Override
    public CupomDto update(Long id, CupomUpdateDto dto) {
        return mapper.toCupomDto(updateUc.update(id, mapper.toCupom(dto)));
    }

    @Override
    public void activate(Long id) {
        activateUc.activate(id);
    }

    @Override
    public void deactivate(Long id) {
        deactivateUc.deactivate(id);
    }

}
