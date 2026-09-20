package com.loja.newloja.infra.mapper;

import com.loja.newloja.infra.model.dto.CategoriaRequestDTO;
import com.loja.newloja.infra.model.dto.CategoriaResponseDTO;
import com.loja.newloja.infra.model.entity.produto.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "string")
public interface CategoriaMapper {

	CategoriaResponseDTO toDto(CategoriaEntity categoria);

	@Mapping(target = "id", ignore = true)
	CategoriaEntity toEntity(CategoriaRequestDTO dto);
}