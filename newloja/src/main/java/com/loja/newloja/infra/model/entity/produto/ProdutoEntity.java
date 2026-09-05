package com.loja.newloja.infra.model.entity.produto;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_produto")
public class ProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(columnDefinition = "TEXT")
	private String descricao;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal preco;

	@Column(name = "data_criacao", nullable = false, updatable = false)
	private LocalDateTime dataCriacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id", nullable = false)
	private CategoriaEntity categoria;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemEstoqueEntity> estoque = new ArrayList<>();

	@PrePersist
	protected void onCreate() {
		this.dataCriacao = LocalDateTime.now();
	}

	public ProdutoEntity(Long id, String nome, String descricao, BigDecimal preco, LocalDateTime dataCriacao, CategoriaEntity categoria, List<ItemEstoqueEntity> estoque) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.dataCriacao = dataCriacao;
		this.categoria = categoria;
		this.estoque = estoque;
	}

	public ProdutoEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	public List<ItemEstoqueEntity> getEstoque() {
		return estoque;
	}

	public void setEstoque(List<ItemEstoqueEntity> estoque) {
		this.estoque = estoque;
	}
}
