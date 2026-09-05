package com.loja.newloja.infra.repository;

import com.loja.newloja.infra.model.entity.usuario.Usuarios;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, UUID> {

	boolean existsByEmail(String email);

	Optional<Usuarios> findByEmail(String email);

	@Transactional
	void deleteByEmail(String email);
}
