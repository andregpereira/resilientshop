package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FindByIdDescontoImpl implements FindByIdDesconto {

    private final DescontoGateway gateway;

    @Override
    public Desconto findById(Long id) {
        return gateway.findById(id);
    }

}
