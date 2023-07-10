package com.github.andregpereira.resilientshop.discountsapi.app.service;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomRegistroDto;

public sealed interface CupomManutencaoService permits CupomManutencaoServiceImpl {

    CupomDto registrar(CupomRegistroDto dto);

}
