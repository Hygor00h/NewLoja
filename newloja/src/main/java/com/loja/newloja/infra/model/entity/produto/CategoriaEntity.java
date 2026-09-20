package com.loja.newloja.infra.model.entity.produto;

import com.loja.newloja.infra.model.enums.CategoriaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
