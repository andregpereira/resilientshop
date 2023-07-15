package com.github.andregpereira.resilientshop.discountsapi.infra.repository;

import com.github.andregpereira.resilientshop.discountsapi.infra.entity.DescontoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DescontoRepository extends JpaRepository<DescontoEntity, Long> {

    Optional<DescontoEntity> findByIdAndAtivoTrue(Long id);

    Optional<DescontoEntity> findByIdAndAtivoFalse(Long id);

}
