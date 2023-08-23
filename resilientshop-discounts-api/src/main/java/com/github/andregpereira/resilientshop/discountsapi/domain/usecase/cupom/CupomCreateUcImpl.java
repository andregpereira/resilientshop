package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.cross.exception.CupomAlreadyExistsException;
import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class CupomCreateUcImpl implements CupomCreateUc {

    private final CupomGateway gateway;

    @Override
    public Cupom criar(Cupom cupom) {
        if (gateway.existsByCodigo(cupom.getCodigo()))
            throw new CupomAlreadyExistsException(cupom.getCodigo());
        return gateway.save(cupom);
    }

}
