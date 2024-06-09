package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.cross.exception.CupomNotFoundException;
import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomConstants.CUPOM;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CupomActivateUcImplTest {

    @InjectMocks
    private CupomActivateUcImpl activateUc;

    @Mock
    private CupomGateway gateway;

    @AfterAll
    static void afterAll() {
        CUPOM.setAtivo(false);
    }

    @Test
    void activate() {
        given(gateway.findInativoById(anyLong())).willReturn(CUPOM);
        activateUc.activate(1L);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThatCode(() -> then(gateway).should().save(any(Cupom.class))).doesNotThrowAnyException();
            softly.assertThat(CUPOM).returns(true, from(Cupom::isAtivo));
        });
    }

    @Test
    void activateByNonExistentId_ThrowsException() {
        given(gateway.findInativoById(anyLong())).willThrow(new CupomNotFoundException((anyLong())));
        assertThatThrownBy(() -> activateUc.activate(10L)).isInstanceOf(CupomNotFoundException.class);
    }

}
