package com.loja.newloja.infra.model.dto;

import com.loja.newloja.infra.model.entity.carrinho.ItemCarrinhoEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CarrinhoDto (
        UUID usuarioId,
        LocalDateTime dataCriacao,
        List<ItemCarrinhoEntity> itens
){
}
