package com.loja.newloja.infra.repository;

import com.loja.newloja.infra.model.entity.carrinho.CarrinhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long> {
    Optional<CarrinhoEntity> findByUsuarioId(UUID usuarioId);
}