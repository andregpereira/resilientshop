package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;

public interface DescontoFindByIdUc {

    Desconto findById(Long id);

}
