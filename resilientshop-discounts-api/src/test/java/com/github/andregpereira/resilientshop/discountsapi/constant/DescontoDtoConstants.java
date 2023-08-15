package com.github.andregpereira.resilientshop.discountsapi.constant;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DescontoDtoConstants {

    public static final DescontoDto DESCONTO_DTO = new DescontoDto(1L, BigDecimal.valueOf(0.3), TipoDesconto.PROD, 5L,
            LocalDate.now(), LocalDate.now().plusDays(5), false);

    public static final DescontoDto DESCONTO_DTO_ATIVO = new DescontoDto(1L, BigDecimal.valueOf(0.3), TipoDesconto.PROD,
            5L, LocalDate.now(), LocalDate.now().plusDays(5), true);

    public static final DescontoDto DESCONTO_DTO_ATUALIZADO = new DescontoDto(1L, BigDecimal.valueOf(0.1),
            TipoDesconto.PROD, 7L, LocalDate.now(), LocalDate.now().plusDays(15), false);

    public static final DescontoDto DESCONTO_DTO_ATUALIZADO_ATIVO = new DescontoDto(1L, BigDecimal.valueOf(0.1),
            TipoDesconto.PROD, 5L, LocalDate.now(), LocalDate.now().plusDays(15), true);

    public static final DescontoCreateDto DESCONTO_CREATE_DTO = new DescontoCreateDto(BigDecimal.valueOf(0.3),
            TipoDesconto.PROD, 5L, LocalDate.now().plusDays(5));

    public static final DescontoCreateDto DESCONTO_CREATE_DTO_ATUALIZADO = new DescontoCreateDto(
            BigDecimal.valueOf(0.1), TipoDesconto.PROD, 7L, LocalDate.now().plusDays(15));

    public static final DescontoCreateDto DESCONTO_CREATE_DTO_INVALIDO = DescontoCreateDto.builder().build();

    private static final PageRequest PAGEABLE = PageRequest.of(0, 10, Sort.Direction.ASC, "id");

    public static final Page<DescontoDto> PAGE_DESCONTO_DTO = new PageImpl<>(
            List.of(DESCONTO_DTO, DESCONTO_DTO_ATUALIZADO), PAGEABLE, 10);

    public static final Page<DescontoDto> PAGE_DESCONTO_DTO_ATIVO = new PageImpl<>(
            List.of(DESCONTO_DTO_ATIVO, DESCONTO_DTO_ATUALIZADO_ATIVO), PAGEABLE, 10);

}
