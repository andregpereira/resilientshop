package com.github.andregpereira.resilientshop.discountsapi.infra.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
sealed interface BaseRepository<T> extends JpaRepository<T, Long> permits CupomRepository, DescontoRepository {

    Optional<T> findByIdAndAtivoTrue(Long id);

    Optional<T> findByIdAndAtivoFalse(Long id);

    Page<T> findAllByAtivoTrue(Pageable pageable);

    Page<T> findAllByAtivoFalse(Pageable pageable);

}
