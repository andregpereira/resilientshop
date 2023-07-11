package com.github.andregpereira.resilientshop.authenticationapi.app.mapper;

import com.github.andregpereira.resilientshop.commons.dto.UsuarioCredentialRegistroDto;
import com.github.andregpereira.resilientshop.authenticationapi.app.dto.UsuarioCredentialDto;
import com.github.andregpereira.resilientshop.authenticationapi.domain.entity.UsuarioCredential;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioCredentialMapper {

    UsuarioCredential toUsuarioCredential(UsuarioCredentialRegistroDto dto);

    UsuarioCredentialDto toUsuarioCredentialDto(UsuarioCredential u);

}
