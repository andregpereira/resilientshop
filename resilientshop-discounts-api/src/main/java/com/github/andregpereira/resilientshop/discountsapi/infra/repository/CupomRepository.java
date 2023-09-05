package com.github.andregpereira.resilientshop.discountsapi.infra.repository;

import com.github.andregpereira.resilientshop.discountsapi.infra.entity.CupomEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public non-sealed interface CupomRepository extends BaseRepository<CupomEntity> {

    boolean existsByCodigo(String codigo);

    Optional<CupomEntity> findByCodigo(String codigo);

}
