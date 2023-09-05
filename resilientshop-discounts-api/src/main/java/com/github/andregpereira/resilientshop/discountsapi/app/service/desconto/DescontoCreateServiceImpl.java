package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.DescontoServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.app.service.CreateService;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoCreateUc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DescontoCreateServiceImpl implements CreateService<DescontoDto, DescontoCreateDto> {

    private final DescontoCreateUc createUc;
    private final DescontoServiceMapper mapper;

    @Override
    public DescontoDto criar(DescontoCreateDto dto) {
        return mapper.toDescontoDto(createUc.criar(mapper.toDesconto(dto)));
    }

}
