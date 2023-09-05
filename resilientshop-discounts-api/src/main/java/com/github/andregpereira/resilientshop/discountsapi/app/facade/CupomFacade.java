package com.github.andregpereira.resilientshop.discountsapi.app.rest.facade;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import org.springframework.http.ResponseEntity;

public non-sealed interface CupomFacade extends BaseFacade<CupomDto, CupomCreateDto, CupomUpdateDto> {

    ResponseEntity<CupomDto> findByCodigo(String codigo);

}
