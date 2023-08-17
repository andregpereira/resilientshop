package com.github.andregpereira.resilientshop.discountsapi.constant;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CommonsConstants.PAGEABLE_ID;

public class DescontoConstants {

    public static final Desconto DESCONTO = new Desconto(1L, BigDecimal.valueOf(0.3), TipoDesconto.PROD.toString(), 5L,
            LocalDate.now(), LocalDate.now().plusDays(5), false);

    public static final Desconto DESCONTO_ATIVO = new Desconto(1L, BigDecimal.valueOf(0.3),
            TipoDesconto.PROD.toString(), 5L, LocalDate.now(), LocalDate.now().plusDays(5), true);

    public static final Desconto DESCONTO_ATUALIZADO = new Desconto(1L, BigDecimal.valueOf(0.1),
            TipoDesconto.PROD.toString(), 7L, LocalDate.now(), LocalDate.now().plusDays(15), false);

    public static final Desconto DESCONTO_ATUALIZADO_ATIVO = new Desconto(1L, BigDecimal.valueOf(0.1),
            TipoDesconto.PROD.toString(), 7L, LocalDate.now(), LocalDate.now().plusDays(15), true);

    public static final Desconto DESCONTO_CREATE = new Desconto(null, BigDecimal.valueOf(0.3),
            TipoDesconto.PROD.toString(), 5L, null, LocalDate.now().plusDays(5), false);

    public static final Desconto DESCONTO_CREATE_ATUALIZADO = new Desconto(null, BigDecimal.valueOf(0.1),
            TipoDesconto.PROD.toString(), 7L, null, LocalDate.now().plusDays(15), false);

    public static final Page<Desconto> PAGE_DESCONTO = new PageImpl<>(List.of(DESCONTO), PAGEABLE_ID, 10);

    public static final Page<Desconto> PAGE_DESCONTO_ATIVO = new PageImpl<>(List.of(DESCONTO_ATIVO), PAGEABLE_ID, 10);

}
