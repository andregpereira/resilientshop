package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CupomFindAllAtivoUc {

    Page<Cupom> findAllAtivo(Pageable pageable);

}
