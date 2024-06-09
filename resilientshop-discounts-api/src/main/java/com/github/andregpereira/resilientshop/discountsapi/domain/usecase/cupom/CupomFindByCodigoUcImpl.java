package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class CupomFindByCodigoUcImpl implements CupomFindByCodigoUc {

    private final CupomGateway gateway;

    @Override
    public Cupom findByCodigo(String codigo) {
        return gateway.findByCodigo(codigo);
    }

}
