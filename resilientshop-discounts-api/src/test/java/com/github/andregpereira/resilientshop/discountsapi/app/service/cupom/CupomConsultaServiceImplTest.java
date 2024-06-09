package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CommonsConstants.PAGEABLE_ID;
import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomConstants.*;
import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomDtoConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CupomConsultaServiceImplTest {

    @InjectMocks
    CupomConsultaServiceImpl consultaService;

    @Mock
    private CupomFindAllUc findAllUc;

    @Mock
    private CupomFindAllAtivoUc findAllAtivoUc;

    @Mock
    private CupomFindAllInativoUc findAllInativoUc;

    @Mock
    private CupomFindByIdUc findByIdUc;

    @Mock
    private CupomFindByCodigoUc findByCodigoUc;

    @Mock
    private CupomServiceMapper mapper;

    @Test
    void findAll_ReturnsPage() {
        given(findAllUc.findAll(any(Pageable.class))).willReturn(PAGE_CUPOM);
        given(mapper.toCupomDto(any(Cupom.class))).willReturn(CUPOM_DTO);
        Page<CupomDto> sut = consultaService.consultarTodos(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_CUPOM_DTO);
    }

    @Test
    void findAll_ReturnsEmptyPage() {
        given(findAllUc.findAll(any(Pageable.class))).willReturn(Page.empty());
        Page<CupomDto> sut = consultaService.consultarTodos(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findAllAtivo_ReturnsPage() {
        given(findAllAtivoUc.findAllAtivo(any(Pageable.class))).willReturn(PAGE_CUPOM_ATIVO);
        given(mapper.toCupomDto(any(Cupom.class))).willReturn(CUPOM_DTO_ATIVO);
        Page<CupomDto> sut = consultaService.consultarAtivo(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_CUPOM_DTO_ATIVO);
    }

    @Test
    void findAllAtivo_ReturnsEmptyPage() {
        given(findAllAtivoUc.findAllAtivo(any(Pageable.class))).willReturn(Page.empty());
        Page<CupomDto> sut = consultaService.consultarAtivo(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findAllInativo_ReturnsPage() {
        given(findAllInativoUc.findAllInativo(any(Pageable.class))).willReturn(PAGE_CUPOM);
        given(mapper.toCupomDto(any(Cupom.class))).willReturn(CUPOM_DTO);
        Page<CupomDto> sut = consultaService.consultarInativo(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_CUPOM_DTO);
    }

    @Test
    void findAllInativo_ReturnsEmptyPage() {
        given(findAllInativoUc.findAllInativo(any(Pageable.class))).willReturn(Page.empty());
        Page<CupomDto> sut = consultaService.consultarInativo(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findById() {
        given(findByIdUc.findById(anyLong())).willReturn(CUPOM);
        given(mapper.toCupomDto(any(Cupom.class))).willReturn(CUPOM_DTO);
        CupomDto sut = consultaService.consultarPorId(1L);
        assertThat(sut).isEqualTo(CUPOM_DTO);
    }

    @Test
    void findByCodigo() {
        given(findByCodigoUc.findByCodigo(anyString())).willReturn(CUPOM);
        given(mapper.toCupomDto(any(Cupom.class))).willReturn(CUPOM_DTO);
        CupomDto sut = consultaService.consultarPorCodigo(CUPOM_DTO.codigo());
        assertThat(sut).isEqualTo(CUPOM_DTO);
    }

}
