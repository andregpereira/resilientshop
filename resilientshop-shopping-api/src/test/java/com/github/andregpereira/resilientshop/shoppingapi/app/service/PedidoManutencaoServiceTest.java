package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.produto.ProdutoAtualizarEstoqueDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.EnderecoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.ProdutoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.mapper.UsuarioDataProviderMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclient.ProdutosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclient.UsuariosFeignClient;
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
import java.util.Collections;
import java.util.Optional;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.DetalhePedidoEntityConstants.*;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.EnderecoConstants.ENDERECO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.EnderecoDtoConstants.ENDERECO_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.LocalDateTimeConstants.PEDIDO_LOCAL_DATE_TIME;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoDtoConstants.*;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY_INVALIDO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoConstants.PRODUTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoDtoConstants.PRODUTO_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.UsuarioConstants.USUARIO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.UsuarioDtoConstants.USUARIO_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class PedidoManutencaoServiceTest {

    @InjectMocks
    private PedidoManutencaoServiceImpl manutencaoService;

    @Mock
    private PedidoMapper pedidoMapper;

    @Mock
    private UsuarioDataProviderMapper usuarioMapper;

    @Mock
    private EnderecoMapper enderecoMapper;

    @Mock
    private ProdutoMapper produtoMapper;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private DetalhePedidoRepository detalhePedidoRepository;

    @Mock
    private UsuariosFeignClient usuariosFeignClient;

    @Mock
    private ProdutosFeignClient produtosFeignClient;

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
        given(usuariosFeignClient.findUsuarioById(1L)).willReturn(USUARIO_DTO);
        given(usuarioMapper.toUsuario(USUARIO_DTO)).willReturn(USUARIO);
        given(usuariosFeignClient.consultarEnderecoPorApelido(1L, "apelido")).willReturn(ENDERECO_DTO);
        willDoNothing().given(produtosFeignClient).subtrair(Collections.singletonList(
                new ProdutoAtualizarEstoqueDto(DETALHE_PEDIDO_ENTITY.getIdProduto(),
                        DETALHE_PEDIDO_ENTITY.getQuantidade())));
        given(enderecoMapper.toEndereco(ENDERECO_DTO)).willReturn(ENDERECO);
        given(pedidoMapper.toPedidoEntity(PEDIDO_REGISTRAR_DTO)).willReturn(PEDIDO_ENTITY);
        given(produtosFeignClient.findProdutoById(1L)).willReturn(PRODUTO_DTO);
        given(produtoMapper.toProduto(PRODUTO_DTO)).willReturn(PRODUTO);
        given(pedidoRepository.save(PEDIDO_ENTITY)).willReturn(PEDIDO_ENTITY);
        given(pedidoMapper.toPedido(PEDIDO_ENTITY)).willReturn(PEDIDO_ENTITY);
        given(pedidoMapper.toPedidoDetalharDto(PEDIDO_ENTITY)).willReturn(PEDIDO_DETALHAR_DTO);
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(PEDIDO_LOCAL_DATE_TIME);
            assertThat(manutencaoService.criar(PEDIDO_REGISTRAR_DTO)).isEqualTo(PEDIDO_DETALHAR_DTO);
        }
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
        willDoNothing().given(produtosFeignClient).retornarEstoque(Collections.singletonList(
                new ProdutoAtualizarEstoqueDto(DETALHE_PEDIDO_ENTITY.getIdProduto(),
                        DETALHE_PEDIDO_ENTITY.getQuantidade())));
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
