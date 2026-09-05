package com.loja.newloja.infra.model.entity.produto;

import jakarta.persistence.*;

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

	public ItemEstoqueEntity(Long id, ProdutoEntity produto, String tamanho, String cor, Integer quantidade) {
		this.id = id;
		this.produto = produto;
		this.tamanho = tamanho;
		this.cor = cor;
		this.quantidade = quantidade;
	}

	public ItemEstoqueEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}