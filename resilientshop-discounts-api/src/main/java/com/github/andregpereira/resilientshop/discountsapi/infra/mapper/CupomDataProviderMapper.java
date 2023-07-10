package com.github.andregpereira.resilientshop.discountsapi.infra.mapper;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.infra.entity.CupomEntity;
import org.springframework.stereotype.Component;

@Component
public sealed interface CupomDataProviderMapper permits CupomDataProviderMapperImpl {

    CupomEntity toCupomEntity(Cupom cupom);

    Cupom toCupom(CupomEntity cupomEntity);

}
