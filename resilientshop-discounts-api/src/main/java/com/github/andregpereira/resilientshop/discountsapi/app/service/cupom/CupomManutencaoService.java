package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;

public interface CupomManutencaoService {

    CupomDto criar(CupomCreateDto dto);

    CupomDto update(Long id, CupomUpdateDto dto);

    void activate(Long id);

    void deactivate(Long id);

}
