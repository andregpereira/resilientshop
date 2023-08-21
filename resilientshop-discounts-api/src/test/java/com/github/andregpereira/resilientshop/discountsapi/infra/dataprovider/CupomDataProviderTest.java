package com.github.andregpereira.resilientshop.discountsapi.infra.dataprovider;

import com.github.andregpereira.resilientshop.discountsapi.cross.exception.CupomNotFoundException;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.infra.entity.CupomEntity;
import com.github.andregpereira.resilientshop.discountsapi.infra.mapper.CupomDataProviderMapper;
import com.github.andregpereira.resilientshop.discountsapi.infra.repository.CupomRepository;
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
import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomConstants.*;
import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomEntityConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CupomDataProviderTest {

    @InjectMocks
    private CupomDataProvider dataProvider;

    @Mock
    private CupomRepository repository;

    @Mock
    private CupomDataProviderMapper mapper;

    @AfterAll
    static void afterAll() {
        CUPOM_ENTITY_CREATE.setId(null);
    }

    @Test
    void save() {
        given(mapper.toCupomEntity(any(Cupom.class))).willReturn(CUPOM_ENTITY_CREATE);
        given(repository.save(any(CupomEntity.class))).willReturn(CUPOM_ENTITY);
        given(mapper.toCupom(any(CupomEntity.class))).willReturn(CUPOM);
        Cupom sut = dataProvider.save(CUPOM_CREATE);
        assertThat(sut).isEqualTo(CUPOM);
    }

    @Test
    void saveInvalidCupomEntity_ThrowsException() {
        given(mapper.toCupomEntity(any(Cupom.class))).willReturn(CUPOM_ENTITY_CREATE_INVALIDO);
        given(repository.save(any(CupomEntity.class))).willThrow(new RuntimeException());
        assertThatThrownBy(() -> dataProvider.save(CUPOM_CREATE_INVALIDO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void existsByCodigo_ReturnsTrue() {
        given(repository.existsByCodigo(anyString())).willReturn(true);
        boolean sut = dataProvider.existsByCodigo("codigo");
        assertThat(sut).isTrue();
    }

    @Test
    void existsByCodigo_ReturnsFalse() {
        given(repository.existsByCodigo(anyString())).willReturn(false);
        boolean sut = dataProvider.existsByCodigo("codigo_inexistente");
        assertThat(sut).isFalse();
    }

    @Test
    void findAll() {
        given(repository.findAll(any(Pageable.class))).willReturn(PAGE_CUPOM_ENTITY);
        given(mapper.toCupom(any(CupomEntity.class))).willReturn(CUPOM);
        Page<Cupom> sut = dataProvider.findAll(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_CUPOM);
    }

    @Test
    void findAll_ReturnsEmptyPage() {
        given(repository.findAll(any(Pageable.class))).willReturn(Page.empty());
        Page<Cupom> sut = dataProvider.findAll(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findAllAtivo() {
        given(repository.findAllByAtivoTrue(any(Pageable.class))).willReturn(PAGE_CUPOM_ENTITY_ATIVO);
        given(mapper.toCupom(any(CupomEntity.class))).willReturn(CUPOM_ATIVO);
        Page<Cupom> sut = dataProvider.findAllAtivo(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_CUPOM_ATIVO);
    }

    @Test
    void findAllAtivo_ReturnsEmptyPage() {
        given(repository.findAllByAtivoTrue(any(Pageable.class))).willReturn(Page.empty());
        Page<Cupom> sut = dataProvider.findAllAtivo(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findAllInativo() {
        given(repository.findAllByAtivoFalse(any(Pageable.class))).willReturn(PAGE_CUPOM_ENTITY);
        given(mapper.toCupom(any(CupomEntity.class))).willReturn(CUPOM);
        Page<Cupom> sut = dataProvider.findAllInativo(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_CUPOM);
    }

    @Test
    void findAllInativo_ReturnsEmptyPage() {
        given(repository.findAllByAtivoFalse(any(Pageable.class))).willReturn(Page.empty());
        Page<Cupom> sut = dataProvider.findAllInativo(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findById() {
        given(repository.findById(anyLong())).willReturn(Optional.of(CUPOM_ENTITY));
        given(mapper.toCupom(any(CupomEntity.class))).willReturn(CUPOM);
        Cupom sut = dataProvider.findById(1L);
        assertThat(sut).isEqualTo(CUPOM);
    }

    @Test
    void findByNonExistentId_ThrowsException() {
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> dataProvider.findById(10L)).isInstanceOf(CupomNotFoundException.class);
    }

    @Test
    void findByCodigo() {
        given(repository.findByCodigo(anyString())).willReturn(Optional.of(CUPOM_ENTITY));
        given(mapper.toCupom(any(CupomEntity.class))).willReturn(CUPOM);
        Cupom sut = dataProvider.findByCodigo("codigo");
        assertThat(sut).isEqualTo(CUPOM);
    }

    @Test
    void findByNonExistentCodigo_ThrowsException() {
        given(repository.findByCodigo(anyString())).willReturn(Optional.empty());
        assertThatThrownBy(() -> dataProvider.findByCodigo("codigo_inexistente")).isInstanceOf(
                CupomNotFoundException.class);
    }

    @Test
    void findAtivoById() {
        given(repository.findByIdAndAtivoTrue(anyLong())).willReturn(Optional.of(CUPOM_ENTITY_ATIVO));
        given(mapper.toCupom(any(CupomEntity.class))).willReturn(CUPOM_ATIVO);
        Cupom sut = dataProvider.findAtivoById(5L);
        assertThat(sut).isEqualTo(CUPOM_ATIVO);
    }

    @Test
    void findNonAtivoOrNonExistentById_ThrowsException() {
        given(repository.findByIdAndAtivoTrue(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> dataProvider.findAtivoById(5L)).isInstanceOf(CupomNotFoundException.class);
    }

    @Test
    void findInativoById() {
        given(repository.findByIdAndAtivoFalse(anyLong())).willReturn(Optional.of(CUPOM_ENTITY));
        given(mapper.toCupom(any(CupomEntity.class))).willReturn(CUPOM);
        Cupom sut = dataProvider.findInativoById(8L);
        assertThat(sut).isEqualTo(CUPOM);
    }

    @Test
    void findNonInativoOrNonExistentById_ThrowsException() {
        given(repository.findByIdAndAtivoFalse(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> dataProvider.findInativoById(8L)).isInstanceOf(CupomNotFoundException.class);
    }

}