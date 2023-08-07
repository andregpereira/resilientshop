package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public sealed interface CupomConsultaService permits CupomConsultaServiceImpl {

    CupomDto consultarPorId(Long id);

    Page<CupomDto> consultarTodos(Pageable pageable);

    Page<CupomDto> consultarAtivo(Pageable pageable);

    Page<CupomDto> consultarInativo(Pageable pageable);

}
