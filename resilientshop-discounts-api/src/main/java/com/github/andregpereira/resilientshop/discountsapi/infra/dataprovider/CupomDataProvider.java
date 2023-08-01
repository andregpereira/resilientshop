package com.github.andregpereira.resilientshop.discountsapi.infra.dataprovider;

import com.github.andregpereira.resilientshop.discountsapi.cross.exception.CupomNotFoundException;
import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.infra.mapper.CupomDataProviderMapper;
import com.github.andregpereira.resilientshop.discountsapi.infra.repository.CupomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.function.Predicate.not;

@RequiredArgsConstructor
@Component
public class CupomDataProvider implements CupomGateway {

    private final CupomRepository repository;
    private final CupomDataProviderMapper mapper;

    @Override
    public Cupom save(Cupom cupom) {
        return mapper.toCupom(repository.save(mapper.toCupomEntity(cupom)));
    }

    @Override
    public Page<Cupom> findAll(Pageable pageable) {
        return Optional.of(repository.findAll(pageable)).filter(not(Page::isEmpty)).map(
                p -> p.map(mapper::toCupom)).orElseThrow(CupomNotFoundException::new);
    }

    @Override
    public Cupom findById(Long id) {
        return repository.findById(id).map(mapper::toCupom).orElseThrow(() -> new CupomNotFoundException(id));
    }

    @Override
    public Cupom findActivatedById(Long id) {
        return repository.findByIdAndAtivoTrue(id).map(mapper::toCupom).orElseThrow(
                () -> new CupomNotFoundException(id, true));
    }

    @Override
    public Cupom findDeactivatedById(Long id) {
        return repository.findByIdAndAtivoFalse(id).map(mapper::toCupom).orElseThrow(
                () -> new CupomNotFoundException(id, false));
    }

}
