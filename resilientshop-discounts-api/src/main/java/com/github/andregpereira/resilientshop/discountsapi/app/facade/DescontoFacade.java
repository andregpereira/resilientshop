package com.github.andregpereira.resilientshop.discountsapi.app.facade;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public non-sealed interface DescontoFacade extends BaseFacade<DescontoDto, DescontoCreateDto, DescontoUpdateDto> {

    ResponseEntity<Page<DescontoDto>> findByTipoDesconto(TipoDesconto tipoDesconto, Pageable pageable);

}
