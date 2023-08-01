package com.github.andregpereira.resilientshop.discountsapi.app.mapper;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoRegistroDto;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DescontoServiceMapper {

    DescontoDto toDescontoDto(Desconto desconto);

    Desconto toDesconto(DescontoRegistroDto dto);

}
