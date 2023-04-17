package com.github.andregpereira.resilientshop.shoppingapi.app.service.mappers;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.domain.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoMapper {

    Pedido toPedido(PedidoRegistrarDto dto);

    PedidoDto toPedidoDto(Pedido pedido);

    PedidoDto toPedidoDetalhesDto(Pedido pedido);

}
