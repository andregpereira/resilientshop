package com.github.andregpereira.resilientshop.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalhePedido {

    private Long id;
    private int quantidade;
    private BigDecimal subtotal;
    private Long idProduto;
    private Produto produto;

}
