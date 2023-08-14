package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CupomActivateUcImpl implements CupomActivateUc {

    private final CupomGateway gateway;

    @Override
    public void activate(Long id) {
        Cupom cupom = gateway.findInativoById(id);
        cupom.setAtivo(true);
        gateway.save(cupom);
    }

}
