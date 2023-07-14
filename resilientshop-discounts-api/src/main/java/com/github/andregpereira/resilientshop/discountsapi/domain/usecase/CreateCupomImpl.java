package com.github.andregpereira.resilientshop.discountsapi.domain.usecase;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateCupomImpl implements CreateCupom {

    private final CupomGateway gateway;

    @Override
    public Cupom criar(Cupom cupom) {
        cupom.setAtivo(true);
        return gateway.save(cupom);
    }

}
