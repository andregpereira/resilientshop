package com.github.andregpereira.resilientshop.discountsapi.infra.dataprovider;

import com.github.andregpereira.resilientshop.discountsapi.cross.exception.CupomNotFoundException;
import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.infra.mapper.CupomDataProviderMapper;
import com.github.andregpereira.resilientshop.discountsapi.infra.repository.CupomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CupomDataProvider implements CupomGateway {

    private final CupomRepository repository;
    private final CupomDataProviderMapper mapper;

    @Override
    @Transactional
    public Cupom save(Cupom cupom) {
        return mapper.toCupom(repository.save(mapper.toCupomEntity(cupom)));
    }

    @Override
    public boolean existsByCodigo(String codigo) {
        return repository.existsByCodigo(codigo);
    }

    @Override
    public Page<Cupom> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toCupom);
    }

    @Override
    public Cupom findById(Long id) {
        return repository.findById(id).map(mapper::toCupom).orElseThrow(() -> new CupomNotFoundException(id));
    }

    @Override
    public Cupom findAtivoById(Long id) {
        return repository.findByIdAndAtivoTrue(id).map(mapper::toCupom).orElseThrow(
                () -> new CupomNotFoundException(id, true));
    }

    @Override
    public Cupom findInativoById(Long id) {
        return repository.findByIdAndAtivoFalse(id).map(mapper::toCupom).orElseThrow(
                () -> new CupomNotFoundException(id, false));
    }

}
