package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.DescontoServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoFindAllByTipoDescontoUc;
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
public class DescontoConsultaServiceImpl implements DescontoConsultaService {

    private final DescontoFindAllUc findAllUc;
    private final DescontoFindAllByTipoDescontoUc findAllByTipoDescontoUc;
    private final DescontoFindByIdUc findByIdUc;
    private final DescontoServiceMapper mapper;

    @Override
    public Page<DescontoDto> consultarTodos(Pageable pageable) {
        return findAllUc.findAll(pageable).map(mapper::toDescontoDto);
    }

    @Override
    public Page<DescontoDto> consultarPorTipoDesconto(String tipoDesconto, Pageable pageable) {
        return findAllByTipoDescontoUc.findAllByTipoDesconto(tipoDesconto, pageable).map(mapper::toDescontoDto);
    }

    @Override
    public DescontoDto consultarPorId(Long id) {
        return mapper.toDescontoDto(findByIdUc.findById(id));
    }

}
