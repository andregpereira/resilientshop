package com.github.andregpereira.resilientshop.discountsapi.infra.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoConstants.DESCONTO;
import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoEntityConstants.DESCONTO_ENTITY;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DescontoDataProviderMapperTest {

    @InjectMocks
    DescontoDataProviderMapperImpl mapper;

    @Test
    void toDesconto() {
        assertThat(mapper.toDesconto(DESCONTO_ENTITY)).isEqualTo(DESCONTO);
    }

    @Test
    void nullDesconto_ReturnsNull() {
        assertThat(mapper.toDesconto(null)).isNull();
    }

    @Test
    void toDescontoEntity() {
        assertThat(mapper.toDescontoEntity(DESCONTO)).isEqualTo(DESCONTO_ENTITY);
    }

    @Test
    void nullDescontoEntity_ReturnsNull() {
        assertThat(mapper.toDescontoEntity(null)).isNull();
    }

}