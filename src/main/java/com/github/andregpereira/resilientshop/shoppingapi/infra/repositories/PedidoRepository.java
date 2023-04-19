package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories;

import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    Page<PedidoEntity> findAllByStatus(Integer status, Pageable pageable);

    @Query(value = """
            select * from tb_pedidos p
            where p.id_pedido=:id and p.status=1
            """, nativeQuery = true)
    Optional<PedidoEntity> findByIdAndStatusAguardandoPagamento(@Param("id") Long id);

}
