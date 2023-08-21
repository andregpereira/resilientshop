package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CommonsConstants.PAGEABLE_ID;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoConstants.PAGE_DESCONTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.data.domain.Page.empty;

@ExtendWith(MockitoExtension.class)
class DescontoFindAllUcImplTest {

    @InjectMocks
    private DescontoFindAllUcImpl findAllUc;
    @Mock
    private DescontoGateway gateway;

    @Test
    void findAll() {
        given(gateway.findAll(any(Pageable.class))).willReturn(PAGE_DESCONTO);
        Page<Desconto> sut = findAllUc.findAll(PAGEABLE_ID);
        assertThat(sut).isNotEmpty().hasSize(1).isEqualTo(PAGE_DESCONTO);
    }

    @Test
    void findAll_ReturnsEmptyPage() {
        given(gateway.findAll(any(Pageable.class))).willReturn(empty());
        Page<Desconto> sut = findAllUc.findAll(PAGEABLE_ID);
        assertThat(sut).isEmpty();
    }

}
