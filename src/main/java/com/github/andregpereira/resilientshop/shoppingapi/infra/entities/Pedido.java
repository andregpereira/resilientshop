package com.github.andregpereira.resilientshop.shoppingapi.infra.entities;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private int status;
    private BigDecimal total;
    private List<DetalhePedido> detalhePedido;

}
