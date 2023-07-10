package com.github.andregpereira.resilientshop.discountsapi.infra.dataprovider;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.infra.mapper.CupomDataProviderMapper;
import com.github.andregpereira.resilientshop.discountsapi.infra.repository.CupomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CupomDataProvider implements CupomGateway {

    private final CupomRepository repository;
    private final CupomDataProviderMapper mapper;

    @Override
    public Cupom registrar(Cupom cupom) {
        return mapper.toCupom(repository.save(mapper.toCupomEntity(cupom)));
    }

}
