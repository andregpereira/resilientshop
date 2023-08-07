package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DescontoFindAllByTipoDescontoUc {

    Page<Desconto> findAllByTipoDesconto(String tipoDesconto, Pageable pageable);

}
