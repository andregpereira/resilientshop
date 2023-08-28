package com.github.andregpereira.resilientshop.shoppingapi.infra.mapper;

import com.github.andregpereira.resilientshop.shoppingapi.domain.model.Pedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entity.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoDataProviderMapper {

    Pedido toPedido(PedidoEntity e);

    PedidoEntity toPedidoEntity(Pedido p);

}
