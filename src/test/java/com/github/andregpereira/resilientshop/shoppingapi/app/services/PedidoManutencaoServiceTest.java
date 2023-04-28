package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.DetalhePedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.ProdutoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutoFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.DetalhePedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoConstants.LISTA_DETALHES_PEDIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.LISTA_DETALHES_PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.LISTA_DETALHES_PEDIDO_ENTITY_VAZIO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PEDIDO_LOCAL_DATE_TIME;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoConstants.PEDIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoDtoConstants.*;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY_INVALIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoConstants.PRODUTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoDtoConstants.PRODUTO_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class PedidoManutencaoServiceTest {

    @InjectMocks
    private PedidoManutencaoServiceImpl manutencaoService;

    @Mock
    private PedidoMapper pedidoMapper;

    @Mock
    private DetalhePedidoMapper detalhePedidoMapper;

    @Mock
    private ProdutoMapper produtoMapper;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private DetalhePedidoRepository detalhePedidoRepository;

    @Mock
    private ProdutoFeignClient produtoFeignClient;

    @AfterEach
    void afterEach() {
        PEDIDO_ENTITY.setStatus(1);
    }

    @BeforeEach
    void beforeEach() {
        PEDIDO_ENTITY.setDetalhePedido(LISTA_DETALHES_PEDIDO_ENTITY);
    }

    @Test
    void criarPedidoComDadosValidosRetornaPedidoDetalharDto() {
        given(pedidoMapper.toPedidoEntity(PEDIDO_REGISTRAR_DTO)).willReturn(PEDIDO_ENTITY);
        given(produtoFeignClient.consultarPorId(1L)).willReturn(PRODUTO_DTO);
        given(produtoMapper.toProduto(PRODUTO_DTO)).willReturn(PRODUTO);
        given(pedidoRepository.save(PEDIDO_ENTITY)).willReturn(PEDIDO_ENTITY);
        given(detalhePedidoRepository.saveAll(LISTA_DETALHES_PEDIDO_ENTITY)).willReturn(LISTA_DETALHES_PEDIDO_ENTITY);
        given(pedidoMapper.toPedido(PEDIDO_ENTITY)).willReturn(PEDIDO);
        given(detalhePedidoMapper.toListaDetalhePedido(LISTA_DETALHES_PEDIDO_ENTITY)).willReturn(LISTA_DETALHES_PEDIDO);
        given(pedidoMapper.toPedidoDetalharDto(PEDIDO)).willReturn(PEDIDO_DETALHAR_DTO);
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(PEDIDO_LOCAL_DATE_TIME);
            assertThat(manutencaoService.criar(PEDIDO_REGISTRAR_DTO)).isEqualTo(PEDIDO_DETALHAR_DTO);
        }
        then(detalhePedidoRepository).should().saveAll(LISTA_DETALHES_PEDIDO_ENTITY);
        then(pedidoRepository).should().save(PEDIDO_ENTITY);
    }

    @Test
    void criarPedidoComDadosInvalidosThrowsException() {
        assertThatThrownBy(() -> manutencaoService.criar(PEDIDO_REGISTRAR_DTO_INVALIDO)).isInstanceOf(
                RuntimeException.class);
        then(detalhePedidoRepository).should(never()).saveAll(LISTA_DETALHES_PEDIDO_ENTITY_VAZIO);
        then(pedidoRepository).should(never()).save(PEDIDO_ENTITY_INVALIDO);
    }

    @Test
    void cancelarPedidoComStatus1PorIdExistenteRetornaString() {
        given(pedidoRepository.findByIdAndStatusAguardandoPagamento(1L)).willReturn(Optional.of(PEDIDO_ENTITY));
        assertThat(manutencaoService.cancelar(1L)).isEqualTo("Pedido com id 1 cancelado");
    }

    @Test
    void cancelarPedidoPorIdInexistenteThrowsException() {
        given(pedidoRepository.findByIdAndStatusAguardandoPagamento(10L)).willReturn(Optional.empty());
        assertThatThrownBy(() -> manutencaoService.cancelar(10L)).isInstanceOf(
                PedidoNotFoundException.class).hasMessage(
                "Poxa! Não foi encontrado um pedido aguardando pagamento com o id 10");
    }

}
