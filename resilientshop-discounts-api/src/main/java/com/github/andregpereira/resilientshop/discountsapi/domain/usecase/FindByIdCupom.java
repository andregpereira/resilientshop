package com.github.andregpereira.resilientshop.discountsapi.domain.usecase;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;

public interface FindByIdCupom {

    Cupom findById(Long id);

}
