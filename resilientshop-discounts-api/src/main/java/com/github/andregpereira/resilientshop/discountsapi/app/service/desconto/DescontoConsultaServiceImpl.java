package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.DescontoServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoFindAllUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoFindByIdUc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public non-sealed class DescontoConsultaServiceImpl implements DescontoConsultaService {

    private final DescontoFindAllUc descontoFindAllUc;
    private final DescontoFindByIdUc descontoFindByIdUc;
    private final DescontoServiceMapper mapper;

    @Override
    public Page<DescontoDto> consultarTodos(Pageable pageable) {
        return descontoFindAllUc.findAll(pageable).map(mapper::toDescontoDto);
    }

    @Override
    public DescontoDto consultarPorId(Long id) {
        return mapper.toDescontoDto(descontoFindByIdUc.findById(id));
    }

}
