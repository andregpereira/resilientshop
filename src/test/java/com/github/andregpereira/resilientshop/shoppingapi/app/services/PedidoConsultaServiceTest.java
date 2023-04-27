package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.DetalhePedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.ProdutoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutoFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.LISTA_DETALHES_PEDIDOS_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoDtoConstants.*;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY_ATUALIZADO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoDtoConstants.PRODUTO_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PedidoConsultaServiceTest {

    @InjectMocks
    private PedidoConsultaServiceImpl consultaService;

    @Spy
    private PedidoMapper pedidoMapper = Mappers.getMapper(PedidoMapper.class);

    @Spy
    private DetalhePedidoMapper detalhePedidoMapper = Mappers.getMapper(DetalhePedidoMapper.class);

    @Spy
    private ProdutoMapper produtoMapper = Mappers.getMapper(ProdutoMapper.class);

    @Mock
    private PedidoRepository repository;

    @Mock
    private ProdutoFeignClient produtoFeignClient;

    @BeforeEach
    void beforeEach() {
        PEDIDO_ENTITY.setDetalhePedido(LISTA_DETALHES_PEDIDOS_ENTITY);
    }

    @Test
    void listarPedidosExistentesRetornaPagePedidoDto() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        List<PedidoEntity> listaPedidos = new ArrayList<>();
        listaPedidos.add(PEDIDO_ENTITY);
        listaPedidos.add(PEDIDO_ENTITY_ATUALIZADO);
        Page<PedidoEntity> pagePedidos = new PageImpl<>(listaPedidos, pageable, 10);
        given(repository.findAll(pageable)).willReturn(pagePedidos);
        Page<PedidoDto> sut = consultaService.listar(pageable);
        assertThat(sut).isNotEmpty().hasSize(2);
        assertThat(sut.getContent().get(0)).isEqualTo(PEDIDO_DTO);
        assertThat(sut.getContent().get(1)).isEqualTo(PEDIDO_DTO_ATUALIZADO);
    }

    @Test
    void listarPedidosInexistentesThrowsException() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        given(repository.findAll(pageable)).willReturn(Page.empty());
        assertThatThrownBy(() -> consultaService.listar(pageable)).isInstanceOf(
                PedidoNotFoundException.class).hasMessage("Poxa! Ainda não há pedidos cadastrados");
    }

    @Test
    void consultarPedidoPorIdExistenteRetornaPedidoDetalharDto() {
        given(repository.findById(1L)).willReturn(Optional.of(PEDIDO_ENTITY));
        given(produtoFeignClient.consultarPorId(1L)).willReturn(PRODUTO_DTO);
        PedidoDetalharDto sut = consultaService.consultarPorId(1L);
        assertThat(sut).isNotNull().isEqualTo(PEDIDO_DETALHAR_DTO);
    }

    @Test
    void consultarPedidoPorIdInexistenteThrowsException() {
        assertThatThrownBy(() -> consultaService.consultarPorId(10L)).isInstanceOf(
                PedidoNotFoundException.class).hasMessage("Opa! Não foi encontrado um pedido com o id 10");
    }

    @Test
    void consultarPedidosExistentesPorStatusRetornaPedidoDto() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        List<PedidoEntity> listaPedidos = new ArrayList<>();
        listaPedidos.add(PEDIDO_ENTITY);
        listaPedidos.add(PEDIDO_ENTITY_ATUALIZADO);
        Page<PedidoEntity> pagePedidos = new PageImpl<>(listaPedidos, pageable, 10);
        given(repository.findAllByStatus(1, pageable)).willReturn(pagePedidos);
        Page<PedidoDto> sut = consultaService.consultarPorStatus(1, pageable);
        assertThat(sut).isNotEmpty().hasSize(2);
        assertThat(sut.getContent().get(0)).isEqualTo(PEDIDO_DTO);
        assertThat(sut.getContent().get(1)).isEqualTo(PEDIDO_DTO_ATUALIZADO);
    }

    @Test
    void consultarPedidosInexistentesPorStatusThrowsException() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        given(repository.findAllByStatus(3, pageable)).willReturn(Page.empty());
        assertThatThrownBy(() -> consultaService.consultarPorStatus(3, pageable)).isInstanceOf(
                PedidoNotFoundException.class).hasMessage(
                "Opa! Não foram encontrados pedidos com o status 3 (EM_SEPARACAO)");
    }

}
