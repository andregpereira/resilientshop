package com.github.andregpereira.resilientshop.productsapi.app.services.produto;

import com.github.andregpereira.resilientshop.productsapi.cross.exceptions.ProdutoAlreadyExistsException;
import com.github.andregpereira.resilientshop.productsapi.cross.exceptions.ProdutoNotFoundException;
import com.github.andregpereira.resilientshop.productsapi.cross.exceptions.SubcategoriaNotFoundException;
import com.github.andregpereira.resilientshop.productsapi.cross.mappers.ProdutoMapper;
import com.github.andregpereira.resilientshop.productsapi.infra.repositories.ProdutoRepository;
import com.github.andregpereira.resilientshop.productsapi.infra.repositories.SubcategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.github.andregpereira.resilientshop.productsapi.constants.ProdutoConstants.*;
import static com.github.andregpereira.resilientshop.productsapi.constants.ProdutoDtoConstants.*;
import static com.github.andregpereira.resilientshop.productsapi.constants.SubcategoriaConstants.SUBCATEGORIA;
import static com.github.andregpereira.resilientshop.productsapi.constants.SubcategoriaConstants.SUBCATEGORIA_ATUALIZADA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoManutencaoServiceTest {

    @InjectMocks
    private ProdutoManutencaoServiceImpl manutencaoService;

    @Spy
    private ProdutoMapper mapper = Mappers.getMapper(ProdutoMapper.class);

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private SubcategoriaRepository subcategoriaRepository;

    @Test
    void criarProdutoComDadosValidosRetornaProdutoDetalhesDto() {
        given(produtoRepository.existsBySku(PRODUTO_LOCAL_DATE_TIME_FIXADO.getSku())).willReturn(false);
        given(produtoRepository.existsByNome(PRODUTO_LOCAL_DATE_TIME_FIXADO.getNome())).willReturn(false);
        given(subcategoriaRepository.findById(1L)).willReturn(Optional.of(SUBCATEGORIA));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(LOCAL_DATE_TIME_FIXADO);
            when(produtoRepository.save(PRODUTO_LOCAL_DATE_TIME_FIXADO)).thenReturn(PRODUTO_LOCAL_DATE_TIME_FIXADO);
            assertThat(manutencaoService.registrar(PRODUTO_REGISTRO_DTO)).isEqualTo(
                    PRODUTO_DETALHES_DTO_LOCAL_DATE_TIME_FIXADO);
        }
        then(produtoRepository).should().save(PRODUTO_LOCAL_DATE_TIME_FIXADO);
    }

    @Test
    void criarProdutoComDadosInvalidosThrowsException() {
        assertThatThrownBy(() -> manutencaoService.registrar(PRODUTO_REGISTRO_DTO_INVALIDO)).isInstanceOf(
                RuntimeException.class);
        then(produtoRepository).should(never()).save(PRODUTO);
    }

    @Test
    void criarProdutoComSkuExistenteThrowsException() {
        given(produtoRepository.existsBySku(PRODUTO.getSku())).willReturn(true);
        assertThatThrownBy(() -> manutencaoService.registrar(PRODUTO_REGISTRO_DTO)).isInstanceOf(
                ProdutoAlreadyExistsException.class).hasMessage(
                MessageFormat.format("Opa! Já existe um produto cadastrado com o SKU {0}", PRODUTO_REGISTRO_DTO.sku()));
        then(produtoRepository).should(never()).save(PRODUTO);
    }

    @Test
    void criarProdutoComNomeExistenteThrowsException() {
        given(produtoRepository.existsBySku(PRODUTO.getSku())).willReturn(false);
        given(produtoRepository.existsByNome(PRODUTO.getNome())).willReturn(true);
        assertThatThrownBy(() -> manutencaoService.registrar(PRODUTO_REGISTRO_DTO)).isInstanceOf(
                ProdutoAlreadyExistsException.class).hasMessage(
                MessageFormat.format("Opa! Já existe um produto cadastrado com o nome {0}",
                        PRODUTO_REGISTRO_DTO.nome()));
        then(produtoRepository).should(never()).save(PRODUTO);
    }

    @Test
    void criarProdutoComIdSubcategoriaInexistenteThrowsException() {
        given(produtoRepository.existsBySku(PRODUTO.getSku())).willReturn(false);
        given(produtoRepository.existsByNome(PRODUTO.getNome())).willReturn(false);
        given(subcategoriaRepository.findById(1L)).willReturn(Optional.empty());
        assertThatThrownBy(() -> manutencaoService.registrar(PRODUTO_REGISTRO_DTO)).isInstanceOf(
                SubcategoriaNotFoundException.class).hasMessage(
                MessageFormat.format("Ops! Não foi possível encontrar uma subcategoria com o id {0}",
                        PRODUTO_REGISTRO_DTO.idSubcategoria()));
        then(produtoRepository).should(never()).save(PRODUTO);
    }

    @Test
    void atualizarProdutoComDadosValidosRetornaProdutoDetalhesDto() {
        given(produtoRepository.findById(PRODUTO.getId())).willReturn(Optional.of(PRODUTO));
        given(produtoRepository.existsByNome(PRODUTO_ATUALIZADO.getNome())).willReturn(false);
        given(subcategoriaRepository.existsById(2L)).willReturn(true);
        given(subcategoriaRepository.getReferenceById(2L)).willReturn(SUBCATEGORIA_ATUALIZADA);
        when(produtoRepository.save(PRODUTO_ATUALIZADO)).thenReturn(PRODUTO_ATUALIZADO);
        assertThat(manutencaoService.atualizar(PRODUTO.getId(), PRODUTO_ATUALIZACAO_DTO)).isEqualTo(
                PRODUTO_DETALHES_DTO_ATUALIZADO);
        then(produtoRepository).should().save(PRODUTO_ATUALIZADO);
    }

    @Test
    void atualizarProdutoComDadosInvalidosThrowsException() {
        assertThatThrownBy(() -> manutencaoService.atualizar(10L, PRODUTO_ATUALIZACAO_DTO_INVALIDO)).isInstanceOf(
                RuntimeException.class);
        then(produtoRepository).should(never()).save(PRODUTO_ATUALIZADO);
    }

    @Test
    void atualizarProdutoComIdInexistenteThrowsException() {
        given(produtoRepository.findById(10L)).willReturn(Optional.empty());
        assertThatThrownBy(() -> manutencaoService.atualizar(10L, PRODUTO_ATUALIZACAO_DTO)).isInstanceOf(
                ProdutoNotFoundException.class).hasMessage("Poxa! Nenhum produto foi encontrado com o id 10");
        then(produtoRepository).should(never()).save(PRODUTO_ATUALIZADO);
    }

    @Test
    void atualizarProdutoComNomeExistenteThrowsException() {
        given(produtoRepository.findById(1L)).willReturn(Optional.of(PRODUTO));
        given(produtoRepository.existsByNome(PRODUTO_ATUALIZADO.getNome())).willReturn(true);
        assertThatThrownBy(() -> manutencaoService.atualizar(1L, PRODUTO_ATUALIZACAO_DTO)).isInstanceOf(
                ProdutoAlreadyExistsException.class).hasMessage(
                "Opa! Já existe um produto cadastrado com o nome nome2");
        then(produtoRepository).should(never()).save(PRODUTO_ATUALIZADO);
    }

    @Test
    void atualizarProdutoComIdSubcategoriaInexistenteThrowsException() {
        given(produtoRepository.findById(1L)).willReturn(Optional.of(PRODUTO));
        given(produtoRepository.existsByNome(PRODUTO_ATUALIZADO.getNome())).willReturn(false);
        given(subcategoriaRepository.existsById(2L)).willReturn(false);
        assertThatThrownBy(() -> manutencaoService.atualizar(1L, PRODUTO_ATUALIZACAO_DTO)).isInstanceOf(
                SubcategoriaNotFoundException.class).hasMessage(
                "Ops! Não foi possível encontrar uma subcategoria com o id 2");
        then(produtoRepository).should(never()).save(PRODUTO_ATUALIZADO);
    }

    @Test
    void removerProdutoComIdExistenteRetornaString() {
        given(produtoRepository.findById(1L)).willReturn(Optional.of(PRODUTO));
        assertThat(manutencaoService.remover(1L)).isEqualTo("Produto com id 1 removido com sucesso");
        then(produtoRepository).should().deleteById(1L);
    }

    @Test
    void removerProdutoComIdInexistenteThrowsException() {
        given(produtoRepository.findById(10L)).willReturn(Optional.empty());
        assertThatThrownBy(() -> manutencaoService.remover(10L)).isInstanceOf(
                ProdutoNotFoundException.class).hasMessage("Poxa! Nenhum produto foi encontrado com o id 10");
        then(produtoRepository).should(never()).deleteById(10L);
    }

}
