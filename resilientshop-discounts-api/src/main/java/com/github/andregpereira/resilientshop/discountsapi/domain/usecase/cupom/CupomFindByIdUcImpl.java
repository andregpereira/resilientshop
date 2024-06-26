package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class CupomFindByIdUcImpl implements CupomFindByIdUc {

    private final CupomGateway gateway;

    @Override
    public Cupom findById(Long id) {
        return gateway.findById(id);
    }

}
