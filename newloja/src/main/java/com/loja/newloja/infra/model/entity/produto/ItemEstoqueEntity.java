package com.loja.newloja.infra.model.entity.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_item_estoque", uniqueConstraints = {
				@UniqueConstraint(columnNames = {"produto_id", "tamanho", "cor"})
})
public class ItemEstoqueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id", nullable = false)
	private ProdutoEntity produto;

	@Column(nullable = false, length = 10)
	private String tamanho; // Ex: P, M, G, GG, 42, 44

	@Column(nullable = false, length = 30)
	private String cor;

	@Column(nullable = false)
	private Integer quantidade;


}