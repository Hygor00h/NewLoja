package com.loja.newloja.infra.model.dto.carrinhoDtos;

import java.math.BigDecimal;

public record ItemCarrinhoResponseDTO(
        Long id,
        Long itemEstoqueId,
        String nomeProduto,
        String imagemUrl,
        String tamanho,
        String cor,
        BigDecimal precoUnitario,
        Integer quantidade,
        BigDecimal subtotal
) {}