package com.github.andregpereira.resilientshop.shoppingapi.cross.mappers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoConstants.PRODUTO;
import static com.github.andregpereira.resilientshop.shoppingapi.constants.ProdutoDtoConstants.PRODUTO_DTO;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = ProdutoMapper.class)
class ProdutoMapperTest {

    @InjectMocks
    private ProdutoMapperImpl mapper;
//    @BeforeEach
//    void beforeEach() {
//        PEDIDO_ENTITY.setDetalhePedido(LISTA_DETALHES_PEDIDO_ENTITY);
//    }

    @Test
    void produtoRetornaProdutoDto() {
        assertThat(mapper.toProdutoDto(PRODUTO)).isEqualTo(PRODUTO_DTO);
    }

    @Test
    void produtoNuloRetornaProdutoDtoNull() {
        assertThat(mapper.toProdutoDto(null)).isNull();
    }

    @Test
    void produtoDtoRetornaProduto() {
        assertThat(mapper.toProduto(PRODUTO_DTO)).isEqualTo(PRODUTO);
    }

    @Test
    void produtoDtoNuloRetornaProdutoNull() {
        assertThat(mapper.toProduto(null)).isNull();
    }

}
