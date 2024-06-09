package com.github.andregpereira.resilientshop.shoppingapi.infra.repository;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    Page<PedidoEntity> findAllByIdUsuario(Long id, Pageable pageable);

    Page<PedidoEntity> findAllByStatus(Integer status, Pageable pageable);

    @Query(value = """
            SELECT * FROM tb_pedidos p
            WHERE p.id_pedido=:id
              AND p.status=1
            """, nativeQuery = true)
    Optional<PedidoEntity> findByIdAndStatusAguardandoPagamento(@Param("id") Long id);

}
