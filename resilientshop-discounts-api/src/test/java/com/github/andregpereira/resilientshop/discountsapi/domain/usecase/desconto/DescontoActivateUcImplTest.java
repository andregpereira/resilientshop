package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.cross.exception.DescontoNotFoundException;
import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoConstants.DESCONTO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DescontoActivateUcImplTest {

    @InjectMocks
    private DescontoActivateUcImpl activateUc;
    @Mock
    private DescontoGateway gateway;

    @AfterAll
    static void afterAll() {
        DESCONTO.setAtivo(false);
    }

    @Test
    void activate() {
        given(gateway.findInativoById(anyLong())).willReturn(DESCONTO);
        activateUc.activate(1L);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThatCode(() -> then(gateway).should().save(any(Desconto.class))).doesNotThrowAnyException();
            softly.assertThat(DESCONTO).returns(true, from(Desconto::isAtivo));
        });
    }

    @Test
    void activateByNonExistentId_ThrowsException() {
        given(gateway.findInativoById(anyLong())).willThrow(new DescontoNotFoundException((anyLong())));
        assertThatThrownBy(() -> activateUc.activate(10L)).isInstanceOf(DescontoNotFoundException.class);
    }

}
