package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.DescontoServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.app.service.UpdateService;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoUpdateUc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DescontoUpdateServiceImpl implements UpdateService<DescontoDto, DescontoUpdateDto> {

    private final DescontoUpdateUc updateUc;
    private final DescontoServiceMapper mapper;

    @Override
    public DescontoDto update(Long id, DescontoUpdateDto dto) {
        return mapper.toDescontoDto(updateUc.update(id, mapper.toDesconto(dto)));
    }

}
