package com.loja.newloja.infra.model.dto.carrinhoDtos;

import java.math.BigDecimal;
import java.util.List;

public record CarrinhoResponseDTO(
        Long id,
        List<ItemCarrinhoResponseDTO> itens,
        BigDecimal valorTotal,
        Integer quantidadeTotalItens
) {}