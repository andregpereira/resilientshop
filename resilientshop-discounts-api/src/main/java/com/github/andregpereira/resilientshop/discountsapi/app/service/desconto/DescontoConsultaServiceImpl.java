package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.DescontoServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoFindAllUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoFindByIdUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoFindByTipoDescontoUc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class DescontoConsultaServiceImpl implements DescontoConsultaService {

    private final DescontoFindByIdUc findByIdUc;
    private final DescontoFindAllUc findAllUc;
    private final DescontoFindByTipoDescontoUc findByTipoDescontoUc;
    private final DescontoServiceMapper mapper;

    @Override
    public DescontoDto consultarPorId(Long id) {
        return mapper.toDescontoDto(findByIdUc.findById(id));
    }

    @Override
    public Page<DescontoDto> consultarTodos(Pageable pageable) {
        return findAllUc.findAll(pageable).map(mapper::toDescontoDto);
    }

    @Override
    public Page<DescontoDto> consultarPorTipoDesconto(TipoDesconto tipoDesconto, Pageable pageable) {
        return findByTipoDescontoUc.findByTipoDesconto(tipoDesconto.toString(), pageable).map(mapper::toDescontoDto);
    }

}
