package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DescontoActivateUcImpl implements DescontoActivateUc {

    private final DescontoGateway gateway;

    @Override
    public void activate(Long id) {
        Desconto desconto = gateway.findInativoById(id);
        desconto.setAtivo(false);
        gateway.save(desconto);
    }

}
