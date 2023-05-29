package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.*;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.UsuariosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.LISTA_DETALHES_PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.EnderecoConstants.ENDERECO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.EnderecoDtoConstants.ENDERECO_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoConstants.PEDIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoDtoConstants.PEDIDO_DETALHAR_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoDtoConstants.PEDIDO_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY_ATUALIZADO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoDtoConstants.PRODUTO_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.UsuarioConstants.USUARIO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.UsuarioDtoConstants.USUARIO_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PedidoConsultaServiceTest {

    @InjectMocks
    private PedidoConsultaServiceImpl consultaService;

    @Mock
    private PedidoMapper pedidoMapper;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private EnderecoMapper enderecoMapper;

    @Mock
    private UsuariosFeignClient usuariosFeignClient;

    @Mock
    private DetalhePedidoMapper detalhePedidoMapper;

    @Mock
    private ProdutoMapper produtoMapper;

    @Mock
    private PedidoRepository repository;

    @Mock
    private ProdutosFeignClient produtosFeignClient;

    @BeforeEach
    void beforeEach() {
        PEDIDO_ENTITY.setDetalhePedido(LISTA_DETALHES_PEDIDO_ENTITY);
    }

    @Test
    void listarPedidosExistentesRetornaPagePedidoDto() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        List<PedidoEntity> listaPedidos = new ArrayList<>();
        listaPedidos.add(PEDIDO_ENTITY);
        listaPedidos.add(PEDIDO_ENTITY_ATUALIZADO);
        Page<PedidoEntity> pagePedidos = new PageImpl<>(listaPedidos, pageable, 10);
        given(repository.findAll(pageable)).willReturn(pagePedidos);
        given(pedidoMapper.toPedido(PEDIDO_ENTITY)).willReturn(PEDIDO);
        given(pedidoMapper.toPedido(PEDIDO_ENTITY_ATUALIZADO)).willReturn(PEDIDO);
        given(pedidoMapper.toPedidoDto(PEDIDO)).willReturn(PEDIDO_DTO);
        Page<PedidoDto> sut = consultaService.listar(pageable);
        assertThat(sut).isNotEmpty().hasSize(2);
        assertThat(sut.getContent().get(0)).isEqualTo(PEDIDO_DTO);
//        assertThat(sut.getContent().get(1)).isEqualTo(PEDIDO_DTO_ATUALIZADO);
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
        given(pedidoMapper.toPedido(PEDIDO_ENTITY)).willReturn(PEDIDO);
        given(usuariosFeignClient.consultarUsuarioPorId(1L)).willReturn(USUARIO_DTO);
        given(usuarioMapper.toUsuario(USUARIO_DTO)).willReturn(USUARIO);
        given(usuariosFeignClient.consultarEnderecoPorId(any(), any())).willReturn(ENDERECO_DTO);
        given(enderecoMapper.toEndereco(ENDERECO_DTO)).willReturn(ENDERECO);
        given(produtosFeignClient.consultarPorId(1L)).willReturn(PRODUTO_DTO);
        given(pedidoMapper.toPedidoDetalharDto(PEDIDO)).willReturn(PEDIDO_DETALHAR_DTO);
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
//        Page<PedidoEntity> pagePedidos = new PageImpl<>(listaPedidos, pageable, 10);
        given(pedidoMapper.toPedido(PEDIDO_ENTITY)).willReturn(PEDIDO);
        given(pedidoMapper.toPedido(PEDIDO_ENTITY_ATUALIZADO)).willReturn(PEDIDO);
        given(pedidoMapper.toPedidoDto(PEDIDO)).willReturn(PEDIDO_DTO);
        Page<PedidoDto> sut = consultaService.consultarPorStatus(1, pageable);
        assertThat(sut).isNotEmpty().hasSize(2);
        assertThat(sut.getContent().get(0)).isEqualTo(PEDIDO_DTO);
//        assertThat(sut.getContent().get(1)).isEqualTo(PEDIDO_DTO_ATUALIZADO);
    }

    @Test
    void consultarPedidosInexistentesPorStatusThrowsException() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        given(repository.findAllByStatus(3, pageable)).willReturn(Page.empty());
        assertThatThrownBy(() -> consultaService.consultarPorStatus(3, pageable)).isInstanceOf(
                PedidoNotFoundException.class).hasMessage(
                "Opa! Não foram encontrados pedidos com o status em separação");
    }

    @Test
    void consultarPedidosPorStatusInvalidoThrowsException() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        given(repository.findAllByStatus(9, pageable)).willReturn(Page.empty());
        assertThatThrownBy(() -> consultaService.consultarPorStatus(9, pageable)).isInstanceOf(
                PedidoNotFoundException.class).hasMessage("Opa! Não foram encontrados pedidos com o status informado");
    }

}
