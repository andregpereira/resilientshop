package com.github.andregpereira.resilientshop.discountsapi.app.service;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public sealed interface CupomConsultaService permits CupomConsultaServiceImpl {


    Page<CupomDto> consultarTodos(Pageable pageable);

}
