package com.loja.newloja.infra.mapper;

import com.loja.newloja.infra.model.dto.LoginResponseDto;
import com.loja.newloja.infra.model.dto.UsuarioRequestDto;
import com.loja.newloja.infra.model.entity.usuario.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(
				componentModel = "spring",
				nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LoginResponseDtoMapper {

	LoginResponseDto toLoginResponseDto (Usuarios usuarios);

	UsuarioRequestDto toDto(Usuarios usuarios);



}
