package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CupomFindAllInativoUc extends UseCase {

    Page<Cupom> findAllInativo(Pageable pageable);

}
