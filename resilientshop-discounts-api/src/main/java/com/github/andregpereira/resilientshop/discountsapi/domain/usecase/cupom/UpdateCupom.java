package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;

public interface UpdateCupom {

    Cupom update(Long id, Cupom cupomAtualizado);

}
