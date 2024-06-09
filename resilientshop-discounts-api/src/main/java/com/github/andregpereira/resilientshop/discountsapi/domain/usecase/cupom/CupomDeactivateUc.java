package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.UseCase;

public interface CupomDeactivateUc extends UseCase {

    void deactivate(Long id);

}
