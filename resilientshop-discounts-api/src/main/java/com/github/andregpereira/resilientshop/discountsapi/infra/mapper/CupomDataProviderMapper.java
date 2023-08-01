package com.github.andregpereira.resilientshop.discountsapi.infra.mapper;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.infra.entity.CupomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CupomDataProviderMapper {

    CupomEntity toCupomEntity(Cupom cupom);

    Cupom toCupom(CupomEntity cupom);

}
