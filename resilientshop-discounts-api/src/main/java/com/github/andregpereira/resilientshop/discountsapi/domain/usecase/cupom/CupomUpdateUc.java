package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.UseCase;

public interface CupomUpdateUc extends UseCase {

    Cupom update(Long id, Cupom cupomAtualizado);

}
