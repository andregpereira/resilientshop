package com.github.andregpereira.resilientshop.productsapi.cross.mappers;

import com.github.andregpereira.resilientshop.productsapi.app.dto.subcategoria.SubcategoriaDetalhesDto;
import com.github.andregpereira.resilientshop.productsapi.app.dto.subcategoria.SubcategoriaDto;
import com.github.andregpereira.resilientshop.productsapi.app.dto.subcategoria.SubcategoriaRegistroDto;
import com.github.andregpereira.resilientshop.productsapi.infra.entities.Subcategoria;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

/**
 * Interface mapper de {@link Subcategoria}, {@link SubcategoriaDto} e {@link SubcategoriaDetalhesDto}.
 *
 * @author André Garcia
 * @see CategoriaMapper
 */
@Mapper(componentModel = ComponentModel.SPRING, uses = CategoriaMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubcategoriaMapper {

    Subcategoria toSubcategoria(SubcategoriaRegistroDto dto);

    SubcategoriaDto toSubcategoriaDto(Subcategoria subcategoria);

    SubcategoriaDetalhesDto toSubcategoriaDetalhesDto(Subcategoria subcategoria);

}