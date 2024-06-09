package com.github.andregpereira.resilientshop.discountsapi.domain.gateway;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DescontoGateway extends BaseGateway<Desconto> {

    Page<Desconto> findByTipoDesconto(String tipoDesconto, Pageable pageable);

}
