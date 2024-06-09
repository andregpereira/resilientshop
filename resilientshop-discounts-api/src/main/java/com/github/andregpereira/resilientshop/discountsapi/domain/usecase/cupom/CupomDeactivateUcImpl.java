package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class CupomDeactivateUcImpl implements CupomDeactivateUc {

    private final CupomGateway gateway;

    @Override
    public void deactivate(Long id) {
        Cupom cupom = gateway.findAtivoById(id);
        cupom.setAtivo(false);
        gateway.save(cupom);
    }

}
