package com.github.andregpereira.resilientshop.shoppingapi.cross.mappers;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Pedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING, uses = {UsuarioMapper.class, DetalhePedidoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoMapper {

    PedidoEntity toPedidoEntity(PedidoRegistrarDto dto);

    Pedido toPedido(PedidoEntity pedidoEntity);

    PedidoDto toPedidoDto(Pedido pedido);

    PedidoDto toPedidoDto(PedidoEntity pedido);

    PedidoDetalharDto toPedidoDetalharDto(Pedido pedido);

    PedidoDetalharDto toPedidoDetalharDto(PedidoEntity pedido);

}
