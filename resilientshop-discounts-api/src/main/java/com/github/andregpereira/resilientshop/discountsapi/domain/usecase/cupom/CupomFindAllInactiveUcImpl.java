package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class CupomFindAllInativoUcImpl implements CupomFindAllInativoUc {

    private final CupomGateway gateway;

    @Override
    public Page<Cupom> findAllInativo(Pageable pageable) {
        return gateway.findAllInativo(pageable);
    }

}
