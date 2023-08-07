package com.github.andregpereira.resilientshop.discountsapi.domain.gateway;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseGateway<T> {

    T save(T t);

    Page<T> findAll(Pageable pageable);

    Page<T> findAllAtivo(Pageable pageable);

    Page<T> findAllInativo(Pageable pageable);

    T findById(Long id);

    T findAtivoById(Long id);

    T findInativoById(Long id);

}
