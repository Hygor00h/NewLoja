package com.loja.newloja.infra.repository;

import com.loja.newloja.infra.model.entity.produto.ItemEstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemEstoqueRepository extends JpaRepository<ItemEstoqueEntity, Long> {

    Optional<ItemEstoqueEntity>findById(Long id);
}
