package com.github.andregpereira.resilientshop.discountsapi.infra.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomConstants.CUPOM;
import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomEntityConstants.CUPOM_ENTITY;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CupomDataProviderMapperTest {

    @InjectMocks
    CupomDataProviderMapperImpl mapper;

    @Test
    void toCupom() {
        assertThat(mapper.toCupom(CUPOM_ENTITY)).isEqualTo(CUPOM);
    }

    @Test
    void nullCupom_ReturnsNull() {
        assertThat(mapper.toCupom(null)).isNull();
    }

    @Test
    void toCupomEntity() {
        assertThat(mapper.toCupomEntity(CUPOM)).isEqualTo(CUPOM_ENTITY);
    }

    @Test
    void nullCupomEntity_ReturnsNull() {
        assertThat(mapper.toCupomEntity(null)).isNull();
    }

}