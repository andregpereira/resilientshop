package com.github.andregpereira.resilientshop.discountsapi.domain.gateway;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CupomGateway {

    Cupom registrar(Cupom cupom);

    Page<Cupom> findAll(Pageable pageable);

}
