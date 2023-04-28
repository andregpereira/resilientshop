package com.github.andregpereira.resilientshop.shoppingapi.cross.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoConstants.DETALHE_PEDIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoConstants.LISTA_DETALHES_PEDIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoDtoConstants.*;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.*;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoConstants.PRODUTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoDtoConstants.PRODUTO_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = PedidoMapperImpl.class)
class DetalhePedidoMapperTest {

    @InjectMocks
    private DetalhePedidoMapperImpl detalhePedidoMapper;

    @Mock
    private ProdutoMapper produtoMapper;

    @BeforeEach
    void beforeEach(){
        DETALHE_PEDIDO.setProduto(PRODUTO);
    }

    @Test
    void listaDetalhePedidoRegistrarDtoRetornaListaDetalhePedidoEntity() {
        assertThat(detalhePedidoMapper.toListaDetalhePedidoEntity(LISTA_DETALHES_PEDIDO_REGISTRAR_DTO)).isEqualTo(
                LISTA_DETALHES_PEDIDO_ENTITY_MAPEADO);
    }

    @Test
    void listaDetalhePedidoRegistrarDtoNuloRetornaListaDetalhePedidoEntityNull() {
        assertThat(detalhePedidoMapper.toListaDetalhePedidoEntity(null)).isNull();
    }

    @Test
    void listaDetalhePedidoEntityRetornaListaDetalhePedido() {
        assertThat(detalhePedidoMapper.toListaDetalhePedido(LISTA_DETALHES_PEDIDO_ENTITY)).isEqualTo(
                LISTA_DETALHES_PEDIDO);
    }

    @Test
    void listaDetalhePedidoEntityNuloRetornaListaDetalhePedidoNull() {
        assertThat(detalhePedidoMapper.toListaDetalhePedido(null)).isNull();
    }

    @Test
    void listaDetalhePedidoRetornaListaDetalhePedidoDto() {
        given(produtoMapper.toProdutoDto(PRODUTO)).willReturn(PRODUTO_DTO);
        assertThat(detalhePedidoMapper.toListaDetalhePedidoDto(LISTA_DETALHES_PEDIDO)).isEqualTo(
                LISTA_DETALHES_PEDIDO_DTO);
    }

    @Test
    void listaDetalhePedidoNuloRetornaListaDetalhePedidoDtoNull() {
        assertThat(detalhePedidoMapper.toListaDetalhePedidoDto(null)).isNull();
    }

    @Test
    void detalhePedidoRegistrarDtoRetornaDetalhePedidoEntity() {
        assertThat(detalhePedidoMapper.toDetalhePedidoEntity(DETALHE_PEDIDO_REGISTRAR_DTO)).isEqualTo(
                DETALHE_PEDIDO_ENTITY_MAPEADO);
    }

    @Test
    void detalhePedidoRegistrarDtoNuloRetornaDetalhePedidoEntityNull() {
        assertThat(detalhePedidoMapper.toDetalhePedidoEntity(null)).isNull();
    }

    @Test
    void detalhePedidoEntityRetornaDetalhePedido() {
        assertThat(detalhePedidoMapper.toDetalhePedido(DETALHE_PEDIDO_ENTITY)).isEqualTo(DETALHE_PEDIDO);
    }

    @Test
    void detalhePedidoEntityNuloRetornaDetalhePedidoNull() {
        assertThat(detalhePedidoMapper.toDetalhePedido(null)).isNull();
    }

    @Test
    void detalhePedidoRetornaDetalhePedidoDto() {
        given(produtoMapper.toProdutoDto(PRODUTO)).willReturn(PRODUTO_DTO);
        assertThat(detalhePedidoMapper.toDetalhePedidoDto(DETALHE_PEDIDO)).isEqualTo(DETALHE_PEDIDO_DTO);
    }

    @Test
    void detalhePedidoNuloRetornaDetalhePedidoDtoNull() {
        assertThat(detalhePedidoMapper.toDetalhePedidoDto(null)).isNull();
    }

}
