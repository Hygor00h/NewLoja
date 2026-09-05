package com.loja.newloja.infra.model.entity.produto;

import com.loja.newloja.infra.model.enums.CategoriaEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_categoria")
public class CategoriaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true, length = 50)
	private CategoriaEnum nome;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<ProdutoEntity> produtos = new ArrayList<>();

	public CategoriaEntity(Long id, CategoriaEnum nome, List<ProdutoEntity> produtos) {
		this.id = id;
		this.nome = nome;
		this.produtos = produtos;
	}

	public CategoriaEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoriaEnum getNome() {

		return nome;
	}

	public void setNome(CategoriaEnum nome) {
		this.nome = nome;
	}

	public List<ProdutoEntity> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoEntity> produtos) {
		this.produtos = produtos;
	}
}
