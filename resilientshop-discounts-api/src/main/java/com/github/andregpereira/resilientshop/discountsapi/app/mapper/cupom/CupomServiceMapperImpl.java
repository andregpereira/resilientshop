package com.github.andregpereira.resilientshop.discountsapi.app.mapper.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import org.springframework.stereotype.Component;

@Component
public non-sealed class CupomServiceMapperImpl implements CupomServiceMapper {

    public CupomDto toCupomDto(Cupom cupom) {
        return new CupomDto(cupom.getId(), cupom.getCodigo());
    }

    public Cupom toCupom(CupomRegistroDto dto) {
        return new Cupom(null, dto.codigo());
    }

}
