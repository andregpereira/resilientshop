package com.github.andregpereira.resilientshop.discountsapi.app.mapper.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;

public sealed interface CupomServiceMapper permits CupomServiceMapperImpl {

    CupomDto toCupomDto(Cupom cupom);

    Cupom toCupom(CupomRegistroDto dto);

}
