package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.app.service.CreateService;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomCreateUc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class CupomCreateServiceImpl implements CreateService<CupomDto, CupomCreateDto> {

    private final CupomCreateUc createUc;
    private final CupomServiceMapper mapper;

    @Override
    public CupomDto criar(CupomCreateDto dto) {
        return mapper.toCupomDto(createUc.criar(mapper.toCupom(dto)));
    }

}
