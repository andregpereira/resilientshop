package com.github.andregpereira.resilientshop.discountsapi.infra.repository;

import com.github.andregpereira.resilientshop.discountsapi.infra.entity.DescontoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public non-sealed interface DescontoRepository extends BaseRepository<DescontoEntity> {

    Page<DescontoEntity> findAllByTipoDesconto(String tipoDesconto, Pageable pageable);

}
