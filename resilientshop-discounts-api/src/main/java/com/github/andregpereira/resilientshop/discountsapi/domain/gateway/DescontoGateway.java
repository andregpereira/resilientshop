package com.github.andregpereira.resilientshop.discountsapi.domain.gateway;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DescontoGateway {

    Desconto save(Desconto desconto);

    Page<Desconto> findAll(Pageable pageable);

    Desconto findById(Long id);

    Desconto findActivatedById(Long id);

    Desconto findDeactivatedById(Long id);

}
