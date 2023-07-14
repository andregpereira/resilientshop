package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;

public sealed interface CupomManutencaoService permits CupomManutencaoServiceImpl {

    CupomDto criar(CupomRegistroDto dto);

    CupomDto update(Long id, CupomUpdateDto dto);

    String activate(Long id);

    String deactivate(Long id);

}
