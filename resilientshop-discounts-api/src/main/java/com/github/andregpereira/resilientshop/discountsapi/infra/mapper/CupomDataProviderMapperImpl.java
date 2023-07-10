package com.github.andregpereira.resilientshop.discountsapi.infra.mapper;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.infra.entity.CupomEntity;
import org.springframework.stereotype.Component;

@Component
public non-sealed class CupomDataProviderMapperImpl implements CupomDataProviderMapper {

    public CupomEntity toCupomEntity(Cupom cupom) {
        return new CupomEntity(cupom.getId(), cupom.getCodigo());
    }

    public Cupom toCupom(CupomEntity cupomEntity) {
        return new Cupom(cupomEntity.getId(), cupomEntity.getCodigo());
    }

}
