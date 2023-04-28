package com.github.andregpereira.resilientshop.shoppingapi.cross.mappers;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.detalhepedido.DetalhePedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.detalhepedido.DetalhePedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.DetalhePedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.DetalhePedidoEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = ComponentModel.SPRING, uses = ProdutoMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DetalhePedidoMapper {

    List<DetalhePedidoEntity> toListaDetalhePedidoEntity(List<DetalhePedidoRegistrarDto> detalhePedido);

    List<DetalhePedido> toListaDetalhePedido(List<DetalhePedidoEntity> detalhePedido);

    List<DetalhePedidoDto> toListaDetalhePedidoDto(List<DetalhePedido> detalhePedido);

    DetalhePedidoEntity toDetalhePedidoEntity(DetalhePedidoRegistrarDto dto);

    DetalhePedido toDetalhePedido(DetalhePedidoEntity detalhePedidoEntity);

    DetalhePedidoDto toDetalhePedidoDto(DetalhePedido detalhePedido);

}
