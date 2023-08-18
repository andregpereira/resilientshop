package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DescontoFindByTipoDescontoUc {

    Page<Desconto> findByTipoDesconto(String tipoDesconto, Pageable pageable);

}
