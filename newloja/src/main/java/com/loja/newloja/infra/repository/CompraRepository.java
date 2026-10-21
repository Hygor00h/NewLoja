package com.loja.newloja.infra.repository;

import com.loja.newloja.infra.model.entity.carrinho.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface CompraRepository extends JpaRepository<CompraEntity, Long> {

    List<CompraEntity> findByCompradorId(UUID compradorId);
}
