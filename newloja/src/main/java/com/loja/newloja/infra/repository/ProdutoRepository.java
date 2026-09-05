package com.loja.newloja.infra.repository;

import com.loja.newloja.infra.model.entity.produto.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

	@Query("""
        SELECT p FROM ProdutoEntity p
        LEFT JOIN FETCH p.categoria
        LEFT JOIN FETCH p.estoque
        WHERE p.id = :id
    """)
	Optional<ProdutoEntity> findByIdWithCategoriaAndEstoque(@Param("id") Long id);
}
