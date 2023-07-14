package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Component
public class ActivateDescontoImpl implements ActivateDesconto {

    private final DescontoGateway gateway;

    @Override
    public String activate(Long id) {
        return gateway.findDeactivatedById(id).map(d -> {
            d.setAtivo(false);
            gateway.save(d);
            return MessageFormat.format("Desconto com id {0} ativado com sucesso!", id);
        });
    }

}
