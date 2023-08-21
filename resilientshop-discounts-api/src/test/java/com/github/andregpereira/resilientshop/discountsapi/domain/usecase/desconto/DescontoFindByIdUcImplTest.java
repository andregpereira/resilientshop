package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.cross.exception.DescontoNotFoundException;
import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoConstants.DESCONTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DescontoFindByIdUcImplTest {

    @InjectMocks
    private DescontoFindByIdUcImpl findByIdUc;
    @Mock
    private DescontoGateway gateway;

    @Test
    void findById() {
        given(gateway.findById(anyLong())).willReturn(DESCONTO);
        Desconto sut = findByIdUc.findById(1L);
        assertThat(sut).isEqualTo(DESCONTO);
    }

    @Test
    void findByNonExistentId_ThrowsException() {
        given(gateway.findById(anyLong())).willThrow(new DescontoNotFoundException(anyLong()));
        assertThatThrownBy(() -> findByIdUc.findById(10L)).isInstanceOf(DescontoNotFoundException.class);
    }

}