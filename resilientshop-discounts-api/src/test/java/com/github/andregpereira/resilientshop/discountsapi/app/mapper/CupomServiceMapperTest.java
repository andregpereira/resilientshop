package com.github.andregpereira.resilientshop.discountsapi.app.mapper;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomConstants.*;
import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomDtoConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CupomServiceMapperTest {

    @InjectMocks
    CupomServiceMapperImpl mapper;

    @Test
    void toCupom_ReturnsCupomCreate() {
        assertThat(mapper.toCupom(CUPOM_CREATE_DTO)).isEqualTo(CUPOM_CREATE);
    }

    @Test
    void nullCupomCreate_ReturnsNull() {
        assertThat(mapper.toCupom((CupomCreateDto) null)).isNull();
    }

    @Test
    void toCupom_ReturnsCupomUpdate() {
        assertThat(mapper.toCupom(CUPOM_UPDATE_DTO)).isEqualTo(CUPOM_UPDATE);
    }

    @Test
    void nullCupomUpdate_ReturnsNull() {
        assertThat(mapper.toCupom((CupomUpdateDto) null)).isNull();
    }

    @Test
    void toCupomDto() {
        assertThat(mapper.toCupomDto(CUPOM)).isEqualTo(CUPOM_DTO);
    }

    @Test
    void nullCupom_ReturnsNull() {
        assertThat(mapper.toCupomDto(null)).isNull();
    }

}