package com.github.andregpereira.resilientshop.discountsapi.constant;

import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CommonsConstants.PAGEABLE_ID;

public class CupomConstants {

    public static final Cupom CUPOM = new Cupom(1L, "codigo", BigDecimal.valueOf(0.2), 3, BigDecimal.valueOf(50),
            BigDecimal.valueOf(12), LocalDate.now(), LocalDate.now().plusDays(5), false);

    public static final Cupom CUPOM_ATIVO = new Cupom(1L, "codigo", BigDecimal.valueOf(0.2), 3, BigDecimal.valueOf(50),
            BigDecimal.valueOf(12), LocalDate.now(), LocalDate.now().plusDays(5), true);

    public static final Cupom CUPOM_ATUALIZADO = new Cupom(1L, "codigo2", BigDecimal.valueOf(0.5), 4,
            BigDecimal.valueOf(200), BigDecimal.valueOf(15), LocalDate.now(), LocalDate.now().plusDays(5), false);

    public static final Cupom CUPOM_ATUALIZADO_ATIVO = new Cupom(1L, "codigo2", BigDecimal.valueOf(0.5), 4,
            BigDecimal.valueOf(200), BigDecimal.valueOf(15), LocalDate.now(), LocalDate.now().plusDays(5), true);

    public static final Cupom CUPOM_CREATE = new Cupom(null, "codigo", BigDecimal.valueOf(0.2), 3,
            BigDecimal.valueOf(50), BigDecimal.valueOf(12), null, LocalDate.now().plusDays(5), false);

    public static final Cupom CUPOM_UPDATE = new Cupom(null, "codigo2", BigDecimal.valueOf(0.5), 4,
            BigDecimal.valueOf(200), BigDecimal.valueOf(15), null, LocalDate.now().plusDays(5), false);

    public static final Page<Cupom> PAGE_CUPOM = new PageImpl<>(List.of(CUPOM), PAGEABLE_ID, 10);

    public static final Page<Cupom> PAGE_CUPOM_ATIVO = new PageImpl<>(List.of(CUPOM_ATIVO), PAGEABLE_ID, 10);

}
