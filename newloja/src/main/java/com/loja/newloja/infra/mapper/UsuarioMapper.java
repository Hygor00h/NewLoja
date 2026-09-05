package com.loja.newloja.infra.mapper;

import com.loja.newloja.infra.model.dto.EnderecoRequestDto;
import com.loja.newloja.infra.model.dto.UsuarioRequestDto;
import com.loja.newloja.infra.model.dto.UsuarioResponseDto;
import com.loja.newloja.infra.model.dto.UsuarioUpdateDto;
import com.loja.newloja.infra.model.entity.usuario.EnderecoEntity;
import com.loja.newloja.infra.model.entity.usuario.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(
				componentModel = "spring",
				nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "roles", ignore = true)
	@Mapping(target = "password", ignore = true) // Ignora para você criptografar no Service
	@Mapping(target = "endereco", source = "endereco")
	Usuarios toEntity(UsuarioRequestDto dto);

	@Mapping(target = "id", ignore = true)
	EnderecoEntity toEnderecoEntity(EnderecoRequestDto dto);

	UsuarioRequestDto toDto(Usuarios usuarios);

	UsuarioResponseDto toResponseDto(Usuarios usuarios);

	@Mapping(target = "password", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "email", ignore = true)
	void updateEntityFromUpdateDto(UsuarioUpdateDto usuarioUpdateDto, @MappingTarget Usuarios usuarios);


	Usuarios updateEntity(UsuarioRequestDto usuarioRequestDto, @MappingTarget Usuarios usuarios);

	void updateDto(Usuarios usuarios,@MappingTarget UsuarioRequestDto usuarioRequestDto);

}
