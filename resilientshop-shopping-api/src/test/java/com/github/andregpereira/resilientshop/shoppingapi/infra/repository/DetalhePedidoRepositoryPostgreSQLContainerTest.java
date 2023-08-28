package com.github.andregpereira.resilientshop.shoppingapi.infra.repositories;

import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.config.PostgreSQLContainerConfig;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entity.DetalhePedidoEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.*;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ContextConfiguration(initializers = PostgreSQLContainerConfig.PostgreSQLContainerInitializer.class)
class DetalhePedidoRepositoryPostgreSQLContainerTest extends PostgreSQLContainerConfig {

    @Autowired
    private DetalhePedidoRepository repository;

    @Autowired
    private TestEntityManager em;

    @AfterEach
    public void afterEach() {
        PEDIDO_ENTITY.setId(null);
        LISTA_DETALHES_PEDIDO_ENTITY.stream().forEach(dp -> dp.setId(null));
    }

    @Test
    void criarDetalhePedidoComDadosValidosRetornaDetalhePedido() {
        DetalhePedidoEntity detalhePedido = repository.save(DETALHE_PEDIDO_ENTITY);
        DetalhePedidoEntity sut = em.find(DetalhePedidoEntity.class, detalhePedido.getId());
        assertThat(sut).isNotNull();
        assertThat(sut.getQuantidade()).isEqualTo(DETALHE_PEDIDO_ENTITY.getQuantidade());
        assertThat(sut.getSubtotal()).isEqualTo(DETALHE_PEDIDO_ENTITY.getSubtotal());
        assertThat(sut.getIdProduto()).isEqualTo(DETALHE_PEDIDO_ENTITY.getIdProduto());
        assertThat(sut.getPedido()).isEqualTo(DETALHE_PEDIDO_ENTITY.getPedido());
    }

    @Test
    void criarDetalhePedidoComDadosInvalidosThrowsException() {
        assertThatThrownBy(() -> repository.saveAndFlush(DETALHE_PEDIDO_ENTITY_VAZIO)).isInstanceOf(
                RuntimeException.class);
        assertThatThrownBy(() -> repository.saveAndFlush(DETALHE_PEDIDO_ENTITY_INVALIDO)).isInstanceOf(
                RuntimeException.class);
    }
//    @Test
//    void listarPedidosExistentesRetornaPedidos() {
//        em.persist(DETALHE_PEDIDO_ENTITY);
//        em.persist(PEDIDO_ENTITY);
//        em.persist(DETALHE_PEDIDO_ENTITY);
//        DetalhePedidoEntity sut2 = new DetalhePedidoEntity(null, LOCAL_DATE_TIME, LOCAL_DATE_TIME, 1,
//                BigDecimal.valueOf(48), DETALHES_PEDIDO_ENTITY);
//        em.persist(sut2);
//        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
//        Page<DetalhePedidoEntity> pagePedidos = repository.findAll(pageable);
//        assertThat(pagePedidos).isNotEmpty().hasSize(2);
//    }

    //    @Test
//    void listarPedidosInexistentesRetornaEmpty() {
//        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
//        Page<DetalhePedidoEntity> pagePedidos = repository.findAll(pageable);
//        assertThat(pagePedidos).isEmpty();
//    }
//
    @Test
    void consultarDetalhePedidoPorIdExistenteRetornaDetalhePedido() {
        em.persist(PEDIDO_ENTITY);
        DetalhePedidoEntity detalhePedido = em.persistFlushFind(DETALHE_PEDIDO_ENTITY);
        Optional<DetalhePedidoEntity> optionalDetalhePedido = repository.findById(detalhePedido.getId());
        assertThat(optionalDetalhePedido).isNotEmpty().get().isEqualTo(detalhePedido);
    }

    @Test
    void consultarDetalhePedidoPorIdInexistenteRetornaEmpty() {
        Optional<DetalhePedidoEntity> optionalDetalhePedido = repository.findById(10L);
        assertThat(optionalDetalhePedido).isEmpty();
    }

    @Test
    void removerDetalhePedidoPorIdExistenteRetornaNulo() {
        em.persist(PEDIDO_ENTITY);
        DetalhePedidoEntity sut = em.persistFlushFind(DETALHE_PEDIDO_ENTITY);
        repository.deleteById(sut.getId());
        DetalhePedidoEntity detalhePedido = em.find(DetalhePedidoEntity.class, sut.getId());
        assertThat(detalhePedido).isNull();
    }

}
