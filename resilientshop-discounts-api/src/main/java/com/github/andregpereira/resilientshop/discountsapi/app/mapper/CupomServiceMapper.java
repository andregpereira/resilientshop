package com.github.andregpereira.resilientshop.discountsapi.app.mapper;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CupomServiceMapper {

    Cupom toCupom(CupomCreateDto dto);

    Cupom toCupom(CupomUpdateDto dto);

    CupomDto toCupomDto(Cupom c);

}
