package com.github.andregpereira.resilientshop.discountsapi.domain.gateway;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CupomGateway {

    Cupom save(Cupom cupom);

    Page<Cupom> findAll(Pageable pageable);

    Cupom findById(Long id);

    Cupom findActivatedById(Long id);

    Cupom findDeactivatedById(Long id);

}
