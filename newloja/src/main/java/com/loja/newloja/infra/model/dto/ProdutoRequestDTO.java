package com.loja.newloja.infra.model.dto;

import com.loja.newloja.infra.model.enums.CategoriaEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoRequestDTO(
				@NotBlank(message = "O nome do produto é obrigatório")
				@Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
				String nome,

				String descricao,

				@NotNull(message = "O preço é obrigatório")
				@Positive(message = "O preço deve ser maior que zero")
				BigDecimal preco,

				@NotNull(message = "O ID da categoria é obrigatório")
				Long categoriaId,

				@NotEmpty(message = "É necessário informar ao menos uma variação de estoque")
				@Valid
				List<ItemEstoqueRequestDTO> estoque
) {}
