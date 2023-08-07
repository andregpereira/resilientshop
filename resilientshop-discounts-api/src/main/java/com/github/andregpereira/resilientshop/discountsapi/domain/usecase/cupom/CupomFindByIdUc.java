package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;

public interface CupomFindByIdUc {

    Cupom findById(Long id);

}
