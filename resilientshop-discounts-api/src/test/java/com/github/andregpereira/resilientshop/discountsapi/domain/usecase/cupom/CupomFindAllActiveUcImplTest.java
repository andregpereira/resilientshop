package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CommonsConstants.PAGEABLE_ID;
import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomConstants.PAGE_CUPOM_ATIVO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CupomFindAllAtivoUcImplTest {

    @InjectMocks
    private CupomFindAllAtivoUcImpl findAllAtivoUc;

    @Mock
    private CupomGateway gateway;

    @Test
    void findAllAtivo_ReturnsCupomPage() {
        given(gateway.findAllAtivo(any(Pageable.class))).willReturn(PAGE_CUPOM_ATIVO);
        Page<Cupom> sut = findAllAtivoUc.findAllAtivo(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_CUPOM_ATIVO);
    }

    @Test
    void findAllAtivo_ReturnsEmptyPage() {
        given(gateway.findAllAtivo(any(Pageable.class))).willReturn(Page.empty());
        Page<Cupom> sut = findAllAtivoUc.findAllAtivo(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

}
