package com.github.andregpereira.resilientshop.discountsapi.infra.dataprovider;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import com.github.andregpereira.resilientshop.discountsapi.cross.exception.DescontoNotFoundException;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import com.github.andregpereira.resilientshop.discountsapi.infra.entity.DescontoEntity;
import com.github.andregpereira.resilientshop.discountsapi.infra.mapper.DescontoDataProviderMapper;
import com.github.andregpereira.resilientshop.discountsapi.infra.repository.DescontoRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CommonsConstants.PAGEABLE_ID;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoConstants.*;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoEntityConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DescontoDataProviderTest {

    @InjectMocks
    private DescontoDataProvider dataProvider;

    @Mock
    private DescontoRepository repository;

    @Mock
    private DescontoDataProviderMapper mapper;

    @AfterAll
    static void afterAll() {
        DESCONTO_ENTITY_CREATE.setId(null);
    }

    @Test
    void save() {
        given(mapper.toDescontoEntity(any(Desconto.class))).willReturn(DESCONTO_ENTITY_CREATE);
        given(repository.save(any(DescontoEntity.class))).willReturn(DESCONTO_ENTITY);
        given(mapper.toDesconto(any(DescontoEntity.class))).willReturn(DESCONTO);
        Desconto sut = dataProvider.save(DESCONTO_CREATE);
        assertThat(sut).isEqualTo(DESCONTO);
    }

    @Test
    void saveInvalidDescontoEntity_ThrowsException() {
        given(mapper.toDescontoEntity(any(Desconto.class))).willReturn(DESCONTO_ENTITY_CREATE_INVALIDO);
        given(repository.save(any(DescontoEntity.class))).willThrow(new RuntimeException());
        assertThatThrownBy(() -> dataProvider.save(DESCONTO_CREATE_INVALIDO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void findAll() {
        given(repository.findAll(any(Pageable.class))).willReturn(PAGE_DESCONTO_ENTITY);
        given(mapper.toDesconto(any(DescontoEntity.class))).willReturn(DESCONTO);
        Page<Desconto> sut = dataProvider.findAll(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_DESCONTO);
    }

    @Test
    void findAll_ReturnsEmptyPage() {
        given(repository.findAll(any(Pageable.class))).willReturn(Page.empty());
        Page<Desconto> sut = dataProvider.findAll(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findByTipoDesconto() {
        given(repository.findAllByTipoDesconto(anyString(), any(Pageable.class))).willReturn(PAGE_DESCONTO_ENTITY);
        given(mapper.toDesconto(any(DescontoEntity.class))).willReturn(DESCONTO);
        Page<Desconto> sut = dataProvider.findByTipoDesconto(TipoDesconto.PROD.toString(), PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_DESCONTO);
    }

    @Test
    void findByTipoDesconto_ReturnsEmptyPage() {
        given(repository.findAllByTipoDesconto(anyString(), any(Pageable.class))).willReturn(Page.empty());
        Page<Desconto> sut = dataProvider.findByTipoDesconto(TipoDesconto.SUBCAT.toString(), PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findAllAtivo() {
        given(repository.findAllByAtivoTrue(any(Pageable.class))).willReturn(PAGE_DESCONTO_ENTITY_ATIVO);
        given(mapper.toDesconto(any(DescontoEntity.class))).willReturn(DESCONTO_ATIVO);
        Page<Desconto> sut = dataProvider.findAllAtivo(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_DESCONTO_ATIVO);
    }

    @Test
    void findAllAtivo_ReturnsEmptyPage() {
        given(repository.findAllByAtivoTrue(any(Pageable.class))).willReturn(Page.empty());
        Page<Desconto> sut = dataProvider.findAllAtivo(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findAllInativo() {
        given(repository.findAllByAtivoFalse(any(Pageable.class))).willReturn(PAGE_DESCONTO_ENTITY);
        given(mapper.toDesconto(any(DescontoEntity.class))).willReturn(DESCONTO);
        Page<Desconto> sut = dataProvider.findAllInativo(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_DESCONTO);
    }

    @Test
    void findAllInativo_ReturnsEmptyPage() {
        given(repository.findAllByAtivoFalse(any(Pageable.class))).willReturn(Page.empty());
        Page<Desconto> sut = dataProvider.findAllInativo(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findById() {
        given(repository.findById(anyLong())).willReturn(Optional.of(DESCONTO_ENTITY));
        given(mapper.toDesconto(any(DescontoEntity.class))).willReturn(DESCONTO);
        Desconto sut = dataProvider.findById(1L);
        assertThat(sut).isEqualTo(DESCONTO);
    }

    @Test
    void findByNonExistentId_ThrowsException() {
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> dataProvider.findById(1L)).isInstanceOf(DescontoNotFoundException.class);
    }

    @Test
    void findAtivoById() {
        given(repository.findByIdAndAtivoTrue(anyLong())).willReturn(Optional.of(DESCONTO_ENTITY_ATIVO));
        given(mapper.toDesconto(any(DescontoEntity.class))).willReturn(DESCONTO_ATIVO);
        Desconto sut = dataProvider.findAtivoById(1L);
        assertThat(sut).isEqualTo(DESCONTO_ATIVO);
    }

    @Test
    void findNonAtivoOrNonExistentById() {
        given(repository.findByIdAndAtivoTrue(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> dataProvider.findAtivoById(10L)).isInstanceOf(DescontoNotFoundException.class);
    }

    @Test
    void findInativoById() {
        given(repository.findByIdAndAtivoFalse(anyLong())).willReturn(Optional.of(DESCONTO_ENTITY));
        given(mapper.toDesconto(any(DescontoEntity.class))).willReturn(DESCONTO);
        Desconto sut = dataProvider.findInativoById(5L);
        assertThat(sut).isEqualTo(DESCONTO);
    }

    @Test
    void findNonInativoOrNonExistentById() {
        given(repository.findByIdAndAtivoFalse(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> dataProvider.findInativoById(15L)).isInstanceOf(DescontoNotFoundException.class);
    }

}