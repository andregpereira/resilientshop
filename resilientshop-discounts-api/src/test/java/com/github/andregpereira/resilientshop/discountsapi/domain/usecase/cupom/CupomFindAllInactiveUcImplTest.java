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
import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomConstants.PAGE_CUPOM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CupomFindAllInativoUcImplTest {

    @InjectMocks
    private CupomFindAllInativoUcImpl findAllInativoUc;

    @Mock
    private CupomGateway gateway;

    @Test
    void findAllInativo_ReturnsCupomPage() {
        given(gateway.findAllInativo(any(Pageable.class))).willReturn(PAGE_CUPOM);
        Page<Cupom> sut = findAllInativoUc.findAllInativo(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_CUPOM);
    }

    @Test
    void findAllInativo_ReturnsEmptyPage() {
        given(gateway.findAllInativo(any(Pageable.class))).willReturn(Page.empty());
        Page<Cupom> sut = findAllInativoUc.findAllInativo(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

}
