package com.loja.newloja.infra.model.dto.carrinhoDtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AdicionarItemDTO(
        @NotNull(message = "O ID do item de estoque é obrigatório")
        Long itemEstoqueId,

        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 1, message = "A quantidade mínima deve ser 1")
        Integer quantidade
) {}
