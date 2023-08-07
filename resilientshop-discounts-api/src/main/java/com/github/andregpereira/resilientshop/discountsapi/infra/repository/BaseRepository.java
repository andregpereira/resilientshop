package com.github.andregpereira.resilientshop.discountsapi.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {

    Optional<T> findByIdAndAtivoTrue(Long id);

    Optional<T> findByIdAndAtivoFalse(Long id);

}
