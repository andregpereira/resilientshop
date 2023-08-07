package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Component
public class DescontoDeactivateUcImpl implements DescontoDeactivateUc {

    private final DescontoGateway gateway;

    @Override
    public String deactivate(Long id) {
        Desconto desconto = gateway.findAtivoById(id);
        desconto.setAtivo(false);
        gateway.save(desconto);
        return MessageFormat.format("Desconto com id {0} desativado com sucesso!", id);
    }

}
