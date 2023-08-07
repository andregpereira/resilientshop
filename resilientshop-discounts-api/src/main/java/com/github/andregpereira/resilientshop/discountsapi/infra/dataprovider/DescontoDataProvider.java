package com.github.andregpereira.resilientshop.discountsapi.infra.dataprovider;

import com.github.andregpereira.resilientshop.discountsapi.cross.exception.CupomNotFoundException;
import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import com.github.andregpereira.resilientshop.discountsapi.infra.mapper.DescontoDataProviderMapper;
import com.github.andregpereira.resilientshop.discountsapi.infra.repository.DescontoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DescontoDataProvider implements DescontoGateway {

    private final DescontoRepository repository;
    private final DescontoDataProviderMapper mapper;

    @Override
    public Desconto save(Desconto desconto) {
        return mapper.toDesconto(repository.save(mapper.toDescontoEntity(desconto)));
    }

    @Override
    public Page<Desconto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDesconto);
    }

    @Override
    public Desconto findById(Long id) {
        return repository.findById(id).map(mapper::toDesconto).orElseThrow(() -> new CupomNotFoundException(id));
    }

    @Override
    public Desconto findAtivoById(Long id) {
        return repository.findByIdAndAtivoTrue(id).map(mapper::toDesconto).orElseThrow(
                () -> new CupomNotFoundException(id, true));
    }

    @Override
    public Desconto findInativoById(Long id) {
        return repository.findByIdAndAtivoFalse(id).map(mapper::toDesconto).orElseThrow(
                () -> new CupomNotFoundException(id, false));
    }

}
