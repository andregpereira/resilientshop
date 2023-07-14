package com.github.andregpereira.resilientshop.discountsapi.domain.usecase;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllCupom {

    Page<Cupom> findAll(Pageable pageable);

}
