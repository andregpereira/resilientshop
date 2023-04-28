package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories;

import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.config.PostgreSQLContainerConfig;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Optional;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.DETALHE_PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.LISTA_DETALHES_PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PEDIDO_LOCAL_DATE_TIME;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class PedidoRepositoryPostgreSQLContainerTest extends PostgreSQLContainerConfig {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private TestEntityManager em;

    @AfterEach
    public void afterEach() {
        PEDIDO_ENTITY.setId(null);
        DETALHE_PEDIDO_ENTITY.setId(null);
    }

    @Test
    void criarPedidoComDadosValidosRetornaPedido() {
        em.persist(DETALHE_PEDIDO_ENTITY);
        PedidoEntity pedido = repository.save(PEDIDO_ENTITY);
        PedidoEntity sut = em.find(PedidoEntity.class, pedido.getId());
        assertThat(sut).isNotNull();
        assertThat(sut.getDataCriacao()).isEqualTo(PEDIDO_ENTITY.getDataCriacao());
        assertThat(sut.getDataModificacao()).isEqualTo(PEDIDO_ENTITY.getDataModificacao());
        assertThat(sut.getStatus()).isEqualTo(PEDIDO_ENTITY.getStatus());
        assertThat(sut.getTotal()).isEqualTo(PEDIDO_ENTITY.getTotal());
        assertThat(sut.getDetalhePedido()).isEqualTo(PEDIDO_ENTITY.getDetalhePedido());
    }

    @Test
    void criarPedidoComDadosInvalidosThrowsException() {
        assertThatThrownBy(() -> repository.saveAndFlush(PEDIDO_ENTITY_VAZIO)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> repository.saveAndFlush(PEDIDO_ENTITY_INVALIDO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void listarPedidosExistentesRetornaPedidos() {
        em.persist(DETALHE_PEDIDO_ENTITY);
        em.persist(PEDIDO_ENTITY);
        em.persist(DETALHE_PEDIDO_ENTITY);
        PedidoEntity sut2 = new PedidoEntity(null, PEDIDO_LOCAL_DATE_TIME, PEDIDO_LOCAL_DATE_TIME, 1,
                BigDecimal.valueOf(48), LISTA_DETALHES_PEDIDO_ENTITY);
        em.persist(sut2);
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        Page<PedidoEntity> pagePedidos = repository.findAll(pageable);
        assertThat(pagePedidos).isNotEmpty().hasSize(2);
    }

    @Test
    void listarPedidosInexistentesRetornaEmpty() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        Page<PedidoEntity> pagePedidos = repository.findAll(pageable);
        assertThat(pagePedidos).isEmpty();
    }

    @Test
    void consultarPedidoPorIdExistenteRetornaPedido() {
        em.persist(DETALHE_PEDIDO_ENTITY);
        PedidoEntity pedido = em.persistFlushFind(PEDIDO_ENTITY);
        Optional<PedidoEntity> optionalPedido = repository.findById(pedido.getId());
        assertThat(optionalPedido).isNotEmpty().get().isEqualTo(pedido);
    }

    @Test
    void consultarPedidoPorIdInexistenteRetornaEmpty() {
        Optional<PedidoEntity> optionalPedido = repository.findById(10L);
        assertThat(optionalPedido).isEmpty();
    }

    @Test
    void consultarPedidosExistentesPorStatusRetornaPedidos() {
        em.persist(DETALHE_PEDIDO_ENTITY);
        PedidoEntity pedido = em.persistFlushFind(PEDIDO_ENTITY);
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        Page<PedidoEntity> pagePedidos = repository.findAllByStatus(pedido.getStatus(), pageable);
        assertThat(pagePedidos).isNotEmpty().hasSize(1);
        assertThat(pagePedidos.getContent().get(0)).isEqualTo(pedido);
    }

    @Test
    void consultarPedidosInexistentesPorStatusRetornaEmpty() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        Page<PedidoEntity> pagePedidos = repository.findAllByStatus(1, pageable);
        assertThat(pagePedidos).isEmpty();
    }

    @Test
    void cancelarPedidoPorIdExistenteRetornaPedidoCancelado() {
        em.persist(DETALHE_PEDIDO_ENTITY);
        PedidoEntity sut = em.persistFlushFind(PEDIDO_ENTITY);
        sut.setStatus(0);
        repository.save(sut);
        PedidoEntity pedidoCancelado = em.find(PedidoEntity.class, sut.getId());
        assertThat(pedidoCancelado.getStatus()).isZero();
    }

    @Test
    void removerPedidoPorIdExistenteRetornaNulo() {
        em.persist(DETALHE_PEDIDO_ENTITY);
        PedidoEntity sut = em.persistFlushFind(PEDIDO_ENTITY);
        repository.deleteById(sut.getId());
        PedidoEntity pedidoRemovido = em.find(PedidoEntity.class, sut.getId());
        assertThat(pedidoRemovido).isNull();
    }

}
