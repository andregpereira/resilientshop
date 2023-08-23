package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.UseCase;

public interface CupomFindByCodigoUc extends UseCase {

    Cupom findByCodigo(String codigo);

}
