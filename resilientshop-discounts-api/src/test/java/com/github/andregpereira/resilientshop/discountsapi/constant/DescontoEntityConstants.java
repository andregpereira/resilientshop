package com.github.andregpereira.resilientshop.discountsapi.constant;

import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import com.github.andregpereira.resilientshop.discountsapi.infra.entity.DescontoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CommonsConstants.PAGEABLE_ID;

public class DescontoEntityConstants {

    public static final DescontoEntity DESCONTO_ENTITY = new DescontoEntity(1L, BigDecimal.valueOf(0.3),
            TipoDesconto.PROD.toString(), 5L, LocalDate.now(), LocalDate.now().plusDays(5), false);

    public static final DescontoEntity DESCONTO_ENTITY_ATIVO = new DescontoEntity(1L, BigDecimal.valueOf(0.3),
            TipoDesconto.PROD.toString(), 5L, LocalDate.now(), LocalDate.now().plusDays(5), true);

    public static final DescontoEntity DESCONTO_ENTITY_ATUALIZADO = new DescontoEntity(1L, BigDecimal.valueOf(0.1),
            TipoDesconto.PROD.toString(), 7L, LocalDate.now(), LocalDate.now().plusDays(15), false);

    public static final DescontoEntity DESCONTO_ENTITY_ATUALIZADO_ATIVO = new DescontoEntity(1L,
            BigDecimal.valueOf(0.1), TipoDesconto.PROD.toString(), 7L, LocalDate.now(), LocalDate.now().plusDays(15),
            true);

    public static final DescontoEntity DESCONTO_ENTITY_CREATE = new DescontoEntity(null, BigDecimal.valueOf(0.3),
            TipoDesconto.PROD.toString(), 5L, null, LocalDate.now().plusDays(5), false);

    public static final DescontoEntity DESCONTO_ENTITY_CREATE_INVALIDO = new DescontoEntity();

    public static final DescontoEntity DESCONTO_ENTITY_CREATE_ATUALIZADO = new DescontoEntity(null,
            BigDecimal.valueOf(0.1), TipoDesconto.PROD.toString(), 7L, null, LocalDate.now().plusDays(15), false);

    public static final Page<DescontoEntity> PAGE_DESCONTO_ENTITY = new PageImpl<>(List.of(DESCONTO_ENTITY),
            PAGEABLE_ID, 10);

    public static final Page<DescontoEntity> PAGE_DESCONTO_ENTITY_ATIVO = new PageImpl<>(List.of(DESCONTO_ENTITY_ATIVO),
            PAGEABLE_ID, 10);

}
