package com.github.andregpereira.resilientshop.discountsapi.app.rest.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public sealed interface BaseFacade<R extends Record, C extends Record, U extends Record> permits CupomFacade, DescontoFacade {

    ResponseEntity<R> criar(C c);

    ResponseEntity<R> update(Long id, U u);

    ResponseEntity<String> activate(Long id);

    ResponseEntity<String> deactivate(Long id);

    ResponseEntity<R> findById(Long id);

    ResponseEntity<Page<R>> findAll(Pageable pageable);

    ResponseEntity<Page<R>> findAllAtivo(Pageable pageable);

    ResponseEntity<Page<R>> findAllInativo(Pageable pageable);

}
