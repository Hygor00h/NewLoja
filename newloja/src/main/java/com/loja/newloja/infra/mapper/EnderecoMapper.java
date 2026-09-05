package com.loja.newloja.infra.mapper;

import com.loja.newloja.infra.model.dto.EnderecoRequestDto;
import com.loja.newloja.infra.model.entity.usuario.EnderecoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "String")
public interface EnderecoMapper {

	EnderecoEntity toEntity(EnderecoRequestDto enderecoDto);

}
