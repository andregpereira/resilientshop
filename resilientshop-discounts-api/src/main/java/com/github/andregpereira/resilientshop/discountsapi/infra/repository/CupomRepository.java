package com.github.andregpereira.resilientshop.discountsapi.infra.repository;

import com.github.andregpereira.resilientshop.discountsapi.infra.entity.CupomEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepository extends BaseRepository<CupomEntity> {

    boolean existsByCodigo(String codigo);

}
