package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.DescontoServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoActivateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoCreateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoDeactivateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoUpdateUc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class DescontoManutencaoServiceImpl implements DescontoManutencaoService {

    private final DescontoCreateUc createUc;
    private final DescontoUpdateUc updateUc;
    private final DescontoActivateUc activateUc;
    private final DescontoDeactivateUc deactivateUc;
    private final DescontoServiceMapper mapper;

    @Override
    public DescontoDto criar(DescontoCreateDto dto) {
        return mapper.toDescontoDto(createUc.criar(mapper.toDesconto(dto)));
    }

    @Override
    public DescontoDto update(Long id, DescontoCreateDto dto) {
        return mapper.toDescontoDto(updateUc.update(id, mapper.toDesconto(dto)));
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
