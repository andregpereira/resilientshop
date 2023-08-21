package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.DescontoServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoFindAllUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoFindByIdUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoFindByTipoDescontoUc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CommonsConstants.PAGEABLE_ID;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoConstants.DESCONTO;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoConstants.PAGE_DESCONTO;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoDtoConstants.DESCONTO_DTO;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoDtoConstants.PAGE_DESCONTO_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.data.domain.Page.empty;

@ExtendWith(MockitoExtension.class)
class DescontoConsultaServiceImplTest {

    @InjectMocks
    DescontoConsultaServiceImpl consultaService;

    @Mock
    private DescontoFindAllUc findAllUc;

    @Mock
    private DescontoFindByTipoDescontoUc findByTipoDescontoUc;

    @Mock
    private DescontoFindByIdUc findByIdUc;

    @Mock
    private DescontoServiceMapper mapper;

    @Test
    void findAll_ReturnsDescontoPage() {
        given(findAllUc.findAll(any(Pageable.class))).willReturn(PAGE_DESCONTO);
        given(mapper.toDescontoDto(any(Desconto.class))).willReturn(DESCONTO_DTO);
        Page<DescontoDto> sut = consultaService.consultarTodos(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_DESCONTO_DTO);
    }

    @Test
    void findAll_ReturnsEmptyPage() {
        given(findAllUc.findAll(any(Pageable.class))).willReturn(empty());
        Page<DescontoDto> sut = consultaService.consultarTodos(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findByTipoDesconto_ReturnsDescontoPage() {
        given(findByTipoDescontoUc.findByTipoDesconto(anyString(), any(Pageable.class))).willReturn(PAGE_DESCONTO);
        given(mapper.toDescontoDto(any(Desconto.class))).willReturn(DESCONTO_DTO);
        Page<DescontoDto> sut = consultaService.consultarPorTipoDesconto(TipoDesconto.PROD.toString(), PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_DESCONTO_DTO);
    }

    @Test
    void findByTipoDesconto_ReturnsEmptyPage() {
        given(findByTipoDescontoUc.findByTipoDesconto(anyString(), any(Pageable.class))).willReturn(empty());
        Page<DescontoDto> sut = consultaService.consultarPorTipoDesconto(TipoDesconto.PROD.toString(), PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

    @Test
    void findById_ReturnsDesconto() {
        given(findByIdUc.findById(anyLong())).willReturn(DESCONTO);
        given(mapper.toDescontoDto(any(Desconto.class))).willReturn(DESCONTO_DTO);
        DescontoDto sut = consultaService.consultarPorId(1L);
        assertThat(sut).isEqualTo(DESCONTO_DTO);
    }

}