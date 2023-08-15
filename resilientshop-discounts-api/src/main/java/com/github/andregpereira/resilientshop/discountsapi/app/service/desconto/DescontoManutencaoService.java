package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;

public interface DescontoManutencaoService {

    DescontoDto criar(DescontoCreateDto dto);

    DescontoDto update(Long id, DescontoCreateDto dto);

    void activate(Long id);

    void deactivate(Long id);

}
