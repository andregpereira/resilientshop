package com.github.andregpereira.resilientshop.discountsapi.domain.gateway;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;

public interface CupomGateway extends Gateway<Cupom> {

    boolean existsByCodigo(String codigo);

}
