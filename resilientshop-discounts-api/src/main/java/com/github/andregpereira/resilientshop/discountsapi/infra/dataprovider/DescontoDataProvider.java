package com.github.andregpereira.resilientshop.discountsapi.infra.dataprovider;

import com.github.andregpereira.resilientshop.discountsapi.cross.exception.DescontoNotFoundException;
import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import com.github.andregpereira.resilientshop.discountsapi.infra.mapper.DescontoDataProviderMapper;
import com.github.andregpereira.resilientshop.discountsapi.infra.repository.DescontoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Component
@CacheConfig(cacheNames = "Descontos")
public class DescontoDataProvider implements DescontoGateway {

    private final DescontoRepository repository;
    private final DescontoDataProviderMapper mapper;

    @Override
    @Transactional
    public Desconto save(Desconto desconto) {
        return mapper.toDesconto(repository.save(mapper.toDescontoEntity(desconto)));
    }

    @Override
    public Page<Desconto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDesconto);
    }

    @Override
    public Page<Desconto> findAllByTipoDesconto(String tipoDesconto, Pageable pageable) {
        return repository.findAllByTipoDesconto(tipoDesconto, pageable).map(mapper::toDesconto);
    }

    @Override
    public Page<Desconto> findAllAtivo(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(mapper::toDesconto);
    }

    @Override
    public Page<Desconto> findAllInativo(Pageable pageable) {
        return repository.findAllByAtivoFalse(pageable).map(mapper::toDesconto);
    }

    @Override
    @Cacheable(key = "'id::' + #id")
    public Desconto findById(Long id) {
        return repository.findById(id).map(mapper::toDesconto).orElseThrow(() -> new DescontoNotFoundException(id));
    }

    @Override
    public Desconto findAtivoById(Long id) {
        return repository.findByIdAndAtivoTrue(id).map(mapper::toDesconto).orElseThrow(
                () -> new DescontoNotFoundException(id, true));
    }

    @Override
    public Desconto findInativoById(Long id) {
        return repository.findByIdAndAtivoFalse(id).map(mapper::toDesconto).orElseThrow(
                () -> new DescontoNotFoundException(id, false));
    }

}
