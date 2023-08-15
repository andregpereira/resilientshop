package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DescontoDeactivateUcImpl implements DescontoDeactivateUc {

    private final DescontoGateway gateway;

    @Override
    public void deactivate(Long id) {
        Desconto desconto = gateway.findAtivoById(id);
        desconto.setAtivo(false);
        gateway.save(desconto);
    }

}
