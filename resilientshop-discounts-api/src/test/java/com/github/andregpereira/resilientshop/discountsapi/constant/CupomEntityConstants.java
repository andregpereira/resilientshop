package com.github.andregpereira.resilientshop.discountsapi.constant;

import com.github.andregpereira.resilientshop.discountsapi.infra.entity.CupomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CommonsConstants.PAGEABLE_ID;

public class CupomEntityConstants {

    public static final CupomEntity CUPOM_ENTITY = new CupomEntity(1L, "codigo", BigDecimal.valueOf(0.2), 3,
            BigDecimal.valueOf(50), BigDecimal.valueOf(12), LocalDate.now(), LocalDate.now().plusDays(5), false);

    public static final CupomEntity CUPOM_ENTITY_ATIVO = new CupomEntity(1L, "codigo", BigDecimal.valueOf(0.2), 3,
            BigDecimal.valueOf(50), BigDecimal.valueOf(12), LocalDate.now(), LocalDate.now().plusDays(5), true);

    public static final CupomEntity CUPOM_ENTITY_ATUALIZADO = new CupomEntity(1L, "codigo2", BigDecimal.valueOf(0.5), 4,
            BigDecimal.valueOf(200), BigDecimal.valueOf(15), LocalDate.now(), LocalDate.now().plusDays(5), false);

    public static final CupomEntity CUPOM_ENTITY_ATUALIZADO_ATIVO = new CupomEntity(1L, "codigo2",
            BigDecimal.valueOf(0.5), 4, BigDecimal.valueOf(200), BigDecimal.valueOf(15), LocalDate.now(),
            LocalDate.now().plusDays(5), true);

    public static final CupomEntity CUPOM_ENTITY_CREATE = new CupomEntity(null, "codigo", BigDecimal.valueOf(0.2), 3,
            BigDecimal.valueOf(50), BigDecimal.valueOf(12), null, LocalDate.now().plusDays(5), false);

    public static final CupomEntity CUPOM_ENTITY_CREATE_INVALIDO = new CupomEntity(null, "codigo",
            BigDecimal.valueOf(0.2), 3, BigDecimal.valueOf(50), BigDecimal.valueOf(12), null,
            LocalDate.now().plusDays(5), false);

    public static final CupomEntity CUPOM_ENTITY_UPDATE = CupomEntity.builder().build();

    public static final Page<CupomEntity> PAGE_CUPOM_ENTITY = new PageImpl<>(List.of(CUPOM_ENTITY), PAGEABLE_ID, 10);

    public static final Page<CupomEntity> PAGE_CUPOM_ENTITY_ATIVO = new PageImpl<>(List.of(CUPOM_ENTITY_ATIVO),
            PAGEABLE_ID, 10);

}
