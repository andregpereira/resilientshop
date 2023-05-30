package com.github.andregpereira.resilientshop.shoppingapi.cross.mappers;

import com.github.andregpereira.resilientshop.commons.entities.Endereco;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.endereco.EnderecoDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper {

    Endereco toEndereco(EnderecoDto dto);

    EnderecoDto toEnderecoDto(Endereco endereco);

}
