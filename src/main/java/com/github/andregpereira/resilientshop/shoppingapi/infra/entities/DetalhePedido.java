package com.github.andregpereira.resilientshop.shoppingapi.infra.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalhePedido {

    private Long id;
    private int quantidade;
    private BigDecimal subtotal;
    private Long idProduto;
    private Produto produto;

}
