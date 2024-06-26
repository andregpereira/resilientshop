package com.github.andregpereira.resilientshop.discountsapi.app.mapper;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DescontoServiceMapper {

    Desconto toDesconto(DescontoCreateDto dto);

    Desconto toDesconto(DescontoUpdateDto dto);

    DescontoDto toDescontoDto(Desconto desconto);

}
