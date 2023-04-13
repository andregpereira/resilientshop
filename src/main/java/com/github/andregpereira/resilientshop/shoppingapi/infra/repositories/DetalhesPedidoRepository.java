package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories;

import com.github.andregpereira.resilientshop.shoppingapi.domain.entities.DetalhesPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalhesPedidoRepository extends JpaRepository<DetalhesPedido, Long> {

}
