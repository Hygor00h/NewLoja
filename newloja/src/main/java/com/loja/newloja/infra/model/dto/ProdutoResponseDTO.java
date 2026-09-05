package com.loja.newloja.infra.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProdutoResponseDTO(
				Long id,
				String nome,
				String descricao,
				BigDecimal preco,
				String nomeCategoria,
				LocalDateTime dataCriacao,
				List<ItemEstoqueResponseDTO> estoque
) {}
