package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;

public interface CupomUpdateUc {

    Cupom update(Long id, Cupom cupomAtualizado);

}
