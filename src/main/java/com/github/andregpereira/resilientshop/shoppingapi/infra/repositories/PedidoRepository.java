package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories;

import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    Optional<PedidoEntity> findByStatus(StatusPedido status);

}
