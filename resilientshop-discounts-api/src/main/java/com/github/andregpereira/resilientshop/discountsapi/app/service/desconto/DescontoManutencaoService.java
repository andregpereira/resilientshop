package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;

public sealed interface DescontoManutencaoService permits DescontoManutencaoServiceImpl {

    DescontoDto criar(DescontoCreateDto dto);

    DescontoDto update(Long id, DescontoCreateDto dto);

    String activate(Long id);

    String deactivate(Long id);

}
