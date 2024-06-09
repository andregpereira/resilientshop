package com.github.andregpereira.resilientshop.discountsapi.app.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoDesconto {
    PROD("PROD"),
    CAT("CAT"),
    SUBCAT("SUBCAT");

    private final String tipo;
}
