package com.github.andregpereira.resilientshop.shoppingapi.cross.mappers;

import com.github.andregpereira.resilientshop.commons.entities.Produto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.produto.ProdutoDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProdutoMapper {

    Produto toProduto(ProdutoDto dto);

    ProdutoDto toProdutoDto(Produto produto);

}
