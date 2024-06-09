package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.cross.exception.CupomNotFoundException;
import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomConstants.CUPOM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CupomFindByIdUcImplTest {

    @InjectMocks
    private CupomFindByIdUcImpl findByIdUc;

    @Mock
    private CupomGateway gateway;

    @Test
    void findById_ReturnsCupom() {
        given(gateway.findById(anyLong())).willReturn(CUPOM);
        Cupom sut = findByIdUc.findById(1L);
        assertThat(sut).isEqualTo(CUPOM);
    }

    @Test
    void findByNonExistentId_ThrowsException() {
        given(gateway.findById(anyLong())).willThrow(new CupomNotFoundException((10L)));
        assertThatThrownBy(() -> findByIdUc.findById(10L)).isInstanceOf(CupomNotFoundException.class);
    }

}
