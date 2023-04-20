package com.github.andregpereira.resilientshop.shoppingapi.cross.mappers;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Pedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoMapper {

    Pedido toPedido(PedidoRegistrarDto dto);

    Pedido toPedido(PedidoEntity pedidoEntity);

    PedidoEntity toPedidoEntity(Pedido pedido);

    PedidoDto toPedidoDto(Pedido pedido);

    PedidoDetalharDto toPedidoDetalharDto(Pedido pedido);

}
