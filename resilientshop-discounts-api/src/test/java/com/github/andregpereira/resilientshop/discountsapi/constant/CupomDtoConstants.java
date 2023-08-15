package com.github.andregpereira.resilientshop.discountsapi.constant;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CupomDtoConstants {

    public static final CupomDto CUPOM_DTO = new CupomDto(1L, "codigo", BigDecimal.valueOf(0.2), 3,
            BigDecimal.valueOf(50), BigDecimal.valueOf(12), LocalDate.now(), LocalDate.now().plusDays(5), false);

    public static final CupomDto CUPOM_DTO_ATIVO = new CupomDto(1L, "codigo", BigDecimal.valueOf(0.2), 3,
            BigDecimal.valueOf(50), BigDecimal.valueOf(12), LocalDate.now(), LocalDate.now().plusDays(5), true);

    public static final CupomDto CUPOM_DTO_ATUALIZADO = new CupomDto(1L, "codigo2", BigDecimal.valueOf(0.5), 4,
            BigDecimal.valueOf(200), BigDecimal.valueOf(15), LocalDate.now(), LocalDate.now().plusDays(5), false);

    public static final CupomDto CUPOM_DTO_ATUALIZADO_ATIVO = new CupomDto(1L, "codigo2", BigDecimal.valueOf(0.5), 4,
            BigDecimal.valueOf(200), BigDecimal.valueOf(15), LocalDate.now(), LocalDate.now().plusDays(5), true);

    public static final CupomCreateDto CUPOM_CREATE_DTO = new CupomCreateDto("codigo", BigDecimal.valueOf(0.2), 3,
            BigDecimal.valueOf(50), BigDecimal.valueOf(12), LocalDate.now().plusDays(5));

    public static final CupomCreateDto CUPOM_CREATE_DTO_INVALIDO = CupomCreateDto.builder().build();

    public static final CupomUpdateDto CUPOM_UPDATE_DTO = new CupomUpdateDto("codigo", BigDecimal.valueOf(0.2), 3,
            BigDecimal.valueOf(50), BigDecimal.valueOf(12), LocalDate.now().plusDays(5), false);

    public static final CupomUpdateDto CUPOM_UPDATE_DTO_INVALIDO = CupomUpdateDto.builder().build();

    private static final PageRequest PAGEABLE = PageRequest.of(0, 10, Sort.Direction.ASC, "id");

    public static final Page<CupomDto> PAGE_CUPOM_DTO = new PageImpl<>(List.of(CUPOM_DTO, CUPOM_DTO_ATUALIZADO),
            PAGEABLE, 10);

    public static final Page<CupomDto> PAGE_CUPOM_DTO_ATIVO = new PageImpl<>(
            List.of(CUPOM_DTO_ATIVO, CUPOM_DTO_ATUALIZADO_ATIVO), PAGEABLE, 10);

}
