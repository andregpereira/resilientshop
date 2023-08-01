package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoRegistroDto;

public sealed interface DescontoManutencaoService permits DescontoManutencaoServiceImpl {

    DescontoDto criar(DescontoRegistroDto dto);

    DescontoDto update(Long id, DescontoRegistroDto dto);

    String activate(Long id);

    String deactivate(Long id);

}
