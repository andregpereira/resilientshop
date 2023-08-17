package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DescontoFindAllByTipoDescontoUcImpl implements DescontoFindAllByTipoDescontoUc {

    private final DescontoGateway gateway;

    @Override
    public Page<Desconto> findAllByTipoDesconto(String tipoDesconto, Pageable pageable) {
        return gateway.findAllByTipoDesconto(tipoDesconto, pageable);
    }

}
