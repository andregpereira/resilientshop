package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.app.service.UpdateService;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomUpdateUc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class CupomUpdateServiceImpl implements UpdateService<CupomDto, CupomUpdateDto> {

    private final CupomUpdateUc updateUc;
    private final CupomServiceMapper mapper;

    @Override
    public CupomDto update(Long id, CupomUpdateDto dto) {
        return mapper.toCupomDto(updateUc.update(id, mapper.toCupom(dto)));
    }

}
