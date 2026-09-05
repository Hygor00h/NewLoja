package com.loja.newloja.infra.repository;

import com.loja.newloja.infra.model.entity.usuario.RoleEntity;
import com.loja.newloja.infra.model.enums.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, UUID> {

	Optional<RoleEntity> findByName(RoleUser name);
}
