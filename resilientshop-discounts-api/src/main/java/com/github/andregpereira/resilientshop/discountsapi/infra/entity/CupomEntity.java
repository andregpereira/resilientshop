package com.github.andregpereira.resilientshop.discountsapi.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cupom")
@SequenceGenerator(name = "cupom", sequenceName = "sq_cupons", allocationSize = 1)
public class CupomEntity {

    @Id
    @Column(name = "id_cupom")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cupom")
    Long id;

    @Column(nullable = false)
    String codigo;

}
