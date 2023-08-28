package com.github.andregpereira.resilientshop.shoppingapi.app.mapper;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.domain.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoServiceMapper {

    Pedido toPedido(PedidoRegistrarDto dto);

    PedidoDto toPedidoDto(Pedido p);

    PedidoDetalharDto toPedidoDetalharDto(Pedido p);

}
