package com.loja.newloja.infra.repository;

import com.loja.newloja.infra.model.entity.produto.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}
