package com.github.andregpereira.resilientshop.discountsapi.infra.mapper;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import com.github.andregpereira.resilientshop.discountsapi.infra.entity.DescontoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DescontoDataProviderMapper {

    Desconto toDesconto(DescontoEntity desconto);

    DescontoEntity toDescontoEntity(Desconto desconto);

}
