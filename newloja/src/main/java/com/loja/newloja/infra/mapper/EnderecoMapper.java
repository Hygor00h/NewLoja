package com.loja.newloja.infra.mapper;

import com.loja.newloja.infra.model.dto.EnderecoRequestDto;
import com.loja.newloja.infra.model.entity.usuario.EnderecoEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "string")
public interface EnderecoMapper {

	EnderecoEntity toEntity(EnderecoRequestDto enderecoDto);

}
