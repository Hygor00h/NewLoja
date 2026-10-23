package com.loja.newloja.infra.mapper;

import com.loja.newloja.infra.model.dto.CarrinhoDto;
import com.loja.newloja.infra.model.dto.ItemCarrinhoDto;
import com.loja.newloja.infra.model.dto.carrinhoDtos.CarrinhoResponseDTO;
import com.loja.newloja.infra.model.entity.carrinho.CarrinhoEntity;
import com.loja.newloja.infra.model.entity.carrinho.ItemCarrinhoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "string")
public interface CarrinhoMapper {

    CarrinhoDto toDto(CarrinhoEntity entity);

    CarrinhoResponseDTO toCarinhoResponse(CarrinhoEntity entity);

    ItemCarrinhoDto toItemDto(ItemCarrinhoEntity entity);

}
