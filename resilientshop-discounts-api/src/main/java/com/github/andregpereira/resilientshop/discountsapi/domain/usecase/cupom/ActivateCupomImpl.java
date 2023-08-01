package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Component
public class ActivateCupomImpl implements ActivateCupom {

    private final CupomGateway gateway;

    @Override
    public String activate(Long id) {
        return gateway.findDeactivatedById(id).map(c -> {
            c.setAtivo(true);
            gateway.save(c);
            return MessageFormat.format("Cupom com id {0} ativado com sucesso!", id);
        });
    }

}
