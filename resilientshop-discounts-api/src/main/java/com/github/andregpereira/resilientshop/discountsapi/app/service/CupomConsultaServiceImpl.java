package com.github.andregpereira.resilientshop.discountsapi.app.service;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.FindAllCupom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public non-sealed class CupomConsultaServiceImpl implements CupomConsultaService {

    private final FindAllCupom findAllCupom;
    private final CupomServiceMapper mapper;

    @Override
    public Page<CupomDto> consultarTodos(Pageable pageable) {
        return findAllCupom.findAll(pageable).map(mapper::toCupomDto);
    }

}
