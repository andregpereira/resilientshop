package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.DetalhePedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.ProdutoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutoFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoDtoConstants.PEDIDO_DETALHAR_DTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoEntityConstants.PEDIDO_ENTITY;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoDtoConstants.PRODUTO_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PedidoConsultaServiceTest {

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

}
