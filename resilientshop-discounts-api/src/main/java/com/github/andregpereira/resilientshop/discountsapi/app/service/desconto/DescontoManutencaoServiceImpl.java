package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.DescontoServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoActivateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoCreateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoDeactivateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoUpdateUc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public non-sealed class DescontoManutencaoServiceImpl implements DescontoManutencaoService {

    private final DescontoCreateUc descontoCreateUc;
    private final DescontoUpdateUc descontoUpdateUc;
    private final DescontoActivateUc descontoActivateUc;
    private final DescontoDeactivateUc descontoDeactivateUc;
    private final DescontoServiceMapper mapper;

    @Override
    public DescontoDto criar(DescontoRegistroDto dto) {
        return mapper.toDescontoDto(descontoCreateUc.criar(mapper.toDesconto(dto)));
    }

    @Override
    public DescontoDto update(Long id, DescontoRegistroDto dto) {
        return mapper.toDescontoDto(descontoUpdateUc.update(id, mapper.toDesconto(dto)));
    }

    @Override
    public String activate(Long id) {
        return descontoActivateUc.activate(id);
    }

    @Override
    public String deactivate(Long id) {
        return descontoDeactivateUc.deactivate(id);
    }

}
