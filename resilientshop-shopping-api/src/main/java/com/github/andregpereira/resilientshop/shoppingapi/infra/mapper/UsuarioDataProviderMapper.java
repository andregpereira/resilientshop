package com.github.andregpereira.resilientshop.shoppingapi.infra.mapper;

import com.github.andregpereira.resilientshop.commons.dto.UsuarioDetalhesDto;
import com.github.andregpereira.resilientshop.commons.entities.Usuario;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.usuario.UsuarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioDataProviderMapper {

    Usuario toUsuario(UsuarioDetalhesDto dto);

    UsuarioDto toUsuarioDto(Usuario usuario);

}
