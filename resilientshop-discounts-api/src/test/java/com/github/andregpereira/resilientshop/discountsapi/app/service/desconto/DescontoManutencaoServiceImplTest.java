package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.DescontoServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoActivateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoCreateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoDeactivateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto.DescontoUpdateUc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoConstants.*;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoDtoConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DescontoManutencaoServiceImplTest {

    @InjectMocks
    private DescontoManutencaoServiceImpl manutencaoService;
    @Mock
    private DescontoCreateUc createUc;

    @Mock
    private DescontoUpdateUc updateUc;

    @Mock
    private DescontoActivateUc activateUc;

    @Mock
    private DescontoDeactivateUc deactivateUc;

    @Mock
    private DescontoServiceMapper mapper;

    @Test
    void create_ReturnsDescontoDto() {
        given(mapper.toDesconto(any(DescontoCreateDto.class))).willReturn(DESCONTO_CREATE);
        given(createUc.criar(any(Desconto.class))).willReturn(DESCONTO);
        given(mapper.toDescontoDto(any(Desconto.class))).willReturn(DESCONTO_DTO);
        DescontoDto sut = manutencaoService.criar(DESCONTO_CREATE_DTO);
        assertThat(sut).isEqualTo(DESCONTO_DTO);
    }

    @Test
    void update_ReturnsDescontoDto() {
        given(mapper.toDesconto(any(DescontoCreateDto.class))).willReturn(DESCONTO_CREATE_ATUALIZADO);
        given(updateUc.update(anyLong(), any(Desconto.class))).willReturn(DESCONTO_ATUALIZADO);
        given(mapper.toDescontoDto(any(Desconto.class))).willReturn(DESCONTO_DTO_ATUALIZADO);
        DescontoDto sut = manutencaoService.update(1L, DESCONTO_CREATE_DTO_ATUALIZADO);
        assertThat(sut).isEqualTo(DESCONTO_DTO_ATUALIZADO);
    }

    @Test
    void activate() {
        manutencaoService.activate(1L);
        then(activateUc).should().activate(anyLong());
    }

    @Test
    void deactivate() {
        manutencaoService.deactivate(20L);
        then(deactivateUc).should().deactivate(anyLong());
    }

}