package com.github.andregpereira.resilientshop.discountsapi.infra.repository;

import com.github.andregpereira.resilientshop.discountsapi.infra.entity.CupomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CupomRepository extends JpaRepository<CupomEntity, Long> {

    Optional<CupomEntity> findByIdAndAtivoTrue(Long id);

    Optional<CupomEntity> findByIdAndAtivoFalse(Long id);

}
