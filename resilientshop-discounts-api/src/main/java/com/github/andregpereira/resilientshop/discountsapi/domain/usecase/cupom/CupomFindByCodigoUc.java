package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;

public interface CupomFindByCodigoUc {

    Cupom findByCodigo(String codigo);

}
