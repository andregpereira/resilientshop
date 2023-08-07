package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Component
public class DeactivateCupomImpl implements DeactivateCupom {

    private final CupomGateway gateway;

    @Override
    public String deactivate(Long id) {
        Cupom cupom = gateway.findAtivoById(id);
        cupom.setAtivo(false);
        gateway.save(cupom);
        return MessageFormat.format("Cupom com id {0} desativado com sucesso!", id);
    }

}
