package com.github.andregpereira.resilientshop.shoppingapi.cross.mappers;

import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.DetalhePedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.DetalhePedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DetalhePedidoMapper {

    DetalhePedidoEntity toDetalhePedidoEntity(DetalhePedido detalhePedido);

    DetalhePedido toDetalhePedido(DetalhePedidoEntity detalhePedidoEntity);

    List<DetalhePedido> toListaDetalhePedido(List<DetalhePedidoEntity> detalhePedido);

}
