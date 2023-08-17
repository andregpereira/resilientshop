package com.github.andregpereira.resilientshop.discountsapi.app.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoConstants.DESCONTO;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoConstants.DESCONTO_CREATE;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoDtoConstants.DESCONTO_CREATE_DTO;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoDtoConstants.DESCONTO_DTO;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DescontoServiceMapperTest {

    @InjectMocks
    DescontoServiceMapperImpl mapper;

    @Test
    void toDesconto() {
        assertThat(mapper.toDesconto(DESCONTO_CREATE_DTO)).isEqualTo(DESCONTO_CREATE);
    }

    @Test
    void toDescontoNull_ReturnsNull() {
        assertThat(mapper.toDesconto(null)).isNull();
    }

    @Test
    void toDescontoDto() {
        assertThat(mapper.toDescontoDto(DESCONTO)).isEqualTo(DESCONTO_DTO);
    }

    @Test
    void toDescontoDtoNull_ReturnsNull() {
        assertThat(mapper.toDescontoDto(null)).isNull();
    }

}