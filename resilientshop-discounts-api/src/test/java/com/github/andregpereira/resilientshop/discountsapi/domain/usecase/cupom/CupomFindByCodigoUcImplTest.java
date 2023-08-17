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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CupomFindByCodigoUcImplTest {

    @InjectMocks
    private CupomFindByCodigoUcImpl findByCodigoUc;

    @Mock
    private CupomGateway gateway;

    @Test
    void findByCodigo_ReturnsCupom() {
        given(gateway.findByCodigo(anyString())).willReturn(CUPOM);
        Cupom sut = findByCodigoUc.findByCodigo(CUPOM.getCodigo());
        assertThat(sut).isEqualTo(CUPOM);
    }

    @Test
    void findByNonExistentCodigo_ThrowsException() {
        given(gateway.findByCodigo(anyString())).willThrow(new CupomNotFoundException(anyString()));
        assertThatThrownBy(() -> findByCodigoUc.findByCodigo("codigo")).isInstanceOf(CupomNotFoundException.class);
    }

}
