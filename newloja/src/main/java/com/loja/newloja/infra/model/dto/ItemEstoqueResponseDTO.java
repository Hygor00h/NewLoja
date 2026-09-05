package com.loja.newloja.infra.model.dto;

public record ItemEstoqueResponseDTO(
				Long id,
				String tamanho,
				String cor,
				Integer quantidade
) {}
