package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.UseCase;

public interface CupomFindByIdUc extends UseCase {

    Cupom findById(Long id);

}
