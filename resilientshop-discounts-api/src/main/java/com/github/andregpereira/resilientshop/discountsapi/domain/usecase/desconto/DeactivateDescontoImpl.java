package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Component
public class DeactivateDescontoImpl implements DeactivateDesconto {

    private final DescontoGateway gateway;

    @Override
    public String deactivate(Long id) {
        return gateway.findActivatedById(id).map(d -> {
            d.setAtivo(false);
            gateway.save(d);
            return MessageFormat.format("Desconto com id {0} desativado com sucesso!", id);
        });
    }

}
