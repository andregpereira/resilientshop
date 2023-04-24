package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories;

import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.DetalhePedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalhePedidoRepository extends JpaRepository<DetalhePedidoEntity, Long> {

}
