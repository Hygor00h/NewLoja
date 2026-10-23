package com.loja.newloja.infra.model.dto;


import com.loja.newloja.infra.model.entity.carrinho.CarrinhoEntity;
import com.loja.newloja.infra.model.entity.produto.ItemEstoqueEntity;

public record ItemCarrinhoDto(
        CarrinhoEntity carrinho,
        ItemEstoqueEntity itemEstoque,
        Integer quantidade
) {
}
