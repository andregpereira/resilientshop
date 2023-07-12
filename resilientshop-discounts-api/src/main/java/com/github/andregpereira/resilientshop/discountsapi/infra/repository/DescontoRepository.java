package com.github.andregpereira.resilientshop.discountsapi.infra.repository;

import com.github.andregpereira.resilientshop.discountsapi.infra.entity.DescontoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescontoRepository extends JpaRepository<DescontoEntity, Long> {

}
