package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories;

import com.github.andregpereira.resilientshop.shoppingapi.domain.Status;
import com.github.andregpereira.resilientshop.shoppingapi.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByStatus(Status status);

}
