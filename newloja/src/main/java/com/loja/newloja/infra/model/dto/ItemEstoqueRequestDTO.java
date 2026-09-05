package com.loja.newloja.infra.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemEstoqueRequestDTO(
				@NotBlank(message = "O tamanho é obrigatório")
				String tamanho,

				@NotBlank(message = "A cor é obrigatória")
				String cor,

				@NotNull(message = "A quantidade é obrigatória")
				@Min(value = 0, message = "A quantidade não pode ser negativa")
				Integer quantidade
) {}
