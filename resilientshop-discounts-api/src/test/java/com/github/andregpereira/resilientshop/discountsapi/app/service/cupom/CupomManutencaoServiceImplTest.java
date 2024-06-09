package com.github.andregpereira.resilientshop.discountsapi.app.service.cupom;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.mapper.CupomServiceMapper;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomActivateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomCreateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomDeactivateUc;
import com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom.CupomUpdateUc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomConstants.*;
import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomDtoConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CupomManutencaoServiceImplTest {

    @InjectMocks
    private CupomManutencaoServiceImpl manutencaoService;
    @Mock
    private CupomCreateUc createUc;

    @Mock
    private CupomUpdateUc updateUc;

    @Mock
    private CupomDeactivateUc deactivateUc;

    @Mock
    private CupomActivateUc activateUc;

    @Mock
    private CupomServiceMapper mapper;

    @Test
    void create_ReturnsCupomDto() {
        given(mapper.toCupom(any(CupomCreateDto.class))).willReturn(CUPOM_CREATE);
        given(createUc.criar(any(Cupom.class))).willReturn(CUPOM);
        given(mapper.toCupomDto(any(Cupom.class))).willReturn(CUPOM_DTO);
        CupomDto sut = manutencaoService.criar(CUPOM_CREATE_DTO);
        assertThat(sut).isEqualTo(CUPOM_DTO);
    }

    @Test
    void update_ReturnsCupomDto() {
        given(mapper.toCupom(any(CupomUpdateDto.class))).willReturn(CUPOM_UPDATE);
        given(updateUc.update(anyLong(), any(Cupom.class))).willReturn(CUPOM_ATUALIZADO);
        given(mapper.toCupomDto(any(Cupom.class))).willReturn(CUPOM_DTO_ATUALIZADO);
        CupomDto sut = manutencaoService.update(1L, CUPOM_UPDATE_DTO);
        assertThat(sut).isEqualTo(CUPOM_DTO_ATUALIZADO);
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
