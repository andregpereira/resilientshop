package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.cross.factory.UseCaseFactory;
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

    private final UseCaseFactory factory;
    private final CupomServiceMapper mapper;

    @Override
    public CupomDto criar(CupomCreateDto dto) {
        return mapper.toCupomDto(factory.getUseCase(CupomCreateUc.class).criar(mapper.toCupom(dto)));
    }

    @Override
    public CupomDto update(Long id, CupomUpdateDto dto) {
        return mapper.toCupomDto(factory.getUseCase(CupomUpdateUc.class).update(id, mapper.toCupom(dto)));
    }

    @Override
    public void activate(Long id) {
        factory.getUseCase(CupomActivateUc.class).activate(id);
    }

    @Override
    public void deactivate(Long id) {
        factory.getUseCase(CupomDeactivateUc.class).deactivate(id);
    }

}
