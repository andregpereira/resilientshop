package com.github.andregpereira.resilientshop.discountsapi.domain.usecase;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Component
public class DeactivateCupomImpl implements DeactivateCupom {

    private final CupomGateway gateway;

    @Override
    public String deactivate(Long id) {
        return gateway.findActivatedById(id).map(c -> {
            c.setAtivo(false);
            gateway.save(c);
            return MessageFormat.format("Cupom com id {0} desativado com sucesso!", id);
        });
    }

}
