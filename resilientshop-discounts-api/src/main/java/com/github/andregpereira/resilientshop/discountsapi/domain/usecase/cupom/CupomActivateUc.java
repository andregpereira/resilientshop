package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.UseCase;

public interface CupomActivateUc extends UseCase {

    void activate(Long id);

}
