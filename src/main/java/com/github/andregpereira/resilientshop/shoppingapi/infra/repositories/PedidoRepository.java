package com.github.andregpereira.resilientshop.shoppingapi.infra.exception.repositories;

import com.github.andregpereira.resilientshop.shoppingapi.domain.entities.StatusPedido;
import com.github.andregpereira.resilientshop.shoppingapi.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByStatus(StatusPedido status);

}
