package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.DescontoServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.ActivateDesconto;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.CreateDesconto;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DeactivateDesconto;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.UpdateDesconto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public non-sealed class DescontoManutencaoServiceImpl implements DescontoManutencaoService {

    private final CreateDesconto createDesconto;
    private final UpdateDesconto updateDesconto;
    private final ActivateDesconto activateDesconto;
    private final DeactivateDesconto deactivateDesconto;
    private final DescontoServiceMapper mapper;

    @Override
    public DescontoDto criar(DescontoRegistroDto dto) {
        return mapper.toDescontoDto(createDesconto.criar(mapper.toDesconto(dto)));
    }

    @Override
    public DescontoDto update(Long id, DescontoRegistroDto dto) {
        return mapper.toDescontoDto(updateDesconto.update(id, mapper.toDesconto(dto)));
    }

    @Override
    public String activate(Long id) {
        return activateDesconto.activate(id);
    }

    @Override
    public String deactivate(Long id) {
        return deactivateDesconto.deactivate(id);
    }

}
