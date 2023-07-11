package com.github.andregpereira.resilientshop.shoppingapi.cross.mappers;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoConstants.LISTA_DETALHES_PEDIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoDtoConstants.LISTA_DETALHES_PEDIDO_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoDtoConstants.LISTA_DETALHES_PEDIDO_REGISTRAR_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.LISTA_DETALHES_PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.LISTA_DETALHES_PEDIDO_ENTITY_MAPEADO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoConstants.PEDIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoDtoConstants.*;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY_MAPEADO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.UsuarioConstants.USUARIO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.UsuarioDtoConstants.USUARIO_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = PedidoMapperImpl.class)
class PedidoMapperTest {

    @InjectMocks
    private PedidoMapperImpl pedidoMapper;

    @Mock
    private DetalhePedidoMapper detalhePedidoMapper;

    @Mock
    private UsuarioMapper usuarioMapper;

    @BeforeEach
    void beforeEach() {
        PEDIDO_ENTITY.setDetalhePedido(LISTA_DETALHES_PEDIDO_ENTITY);
    }

    @Test
    void pedidoRegistrarDtoRetornaPedidoEntity() {
        given(detalhePedidoMapper.toListaDetalhePedidoEntity(LISTA_DETALHES_PEDIDO_REGISTRAR_DTO)).willReturn(
                LISTA_DETALHES_PEDIDO_ENTITY_MAPEADO);
        assertThat(pedidoMapper.toPedidoEntity(PEDIDO_REGISTRAR_DTO)).isEqualTo(PEDIDO_ENTITY_MAPEADO);
    }

    @Test
    void pedidoRegistrarDtoNuloRetornaPedidoEntityNull() {
        assertThat(pedidoMapper.toPedidoEntity(null)).isNull();
    }

    @Test
    void pedidoEntityRetornaPedido() {
        given(detalhePedidoMapper.toListaDetalhePedido(LISTA_DETALHES_PEDIDO_ENTITY)).willReturn(LISTA_DETALHES_PEDIDO);
        assertThat(pedidoMapper.toPedido(PEDIDO_ENTITY)).isEqualTo(PEDIDO);
    }

    @Test
    void pedidoEntityNuloRetornaPedidoNull() {
        assertThat(pedidoMapper.toPedido(null)).isNull();
    }

    @Test
    void pedidoRetornaPedidoDto() {
        assertThat(pedidoMapper.toPedidoDto(PEDIDO)).isEqualTo(PEDIDO_DTO);
    }

    @Test
    void pedidoNuloRetornaPedidoDtoNull() {
        assertThat(pedidoMapper.toPedidoDto((Pedido) null)).isNull();
    }

    @Test
    void pedidoRetornaPedidoDetalharDto() {
        given(usuarioMapper.toUsuarioDto(USUARIO)).willReturn(USUARIO_DTO);
        given(detalhePedidoMapper.toListaDetalhePedidoDto(LISTA_DETALHES_PEDIDO)).willReturn(LISTA_DETALHES_PEDIDO_DTO);
        assertThat(pedidoMapper.toPedidoDetalharDto(PEDIDO)).isEqualTo(PEDIDO_DETALHAR_DTO);
    }

    @Test
    void pedidoNuloRetornaPedidoDetalharDtoNull() {
        assertThat(pedidoMapper.toPedidoDetalharDto(null)).isNull();
    }

}
