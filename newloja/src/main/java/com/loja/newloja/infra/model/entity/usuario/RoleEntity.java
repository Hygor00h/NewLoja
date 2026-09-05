package com.loja.newloja.infra.model.entity.usuario;

import com.loja.newloja.infra.model.enums.RoleUser;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Table(name = "roles" )
@EqualsAndHashCode(of = "name")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "UUID")
	private UUID id;


	@Enumerated(EnumType.STRING)
	@Column(name = "name", unique = true, nullable = false, length = 30)
	private RoleUser name;

	public RoleEntity(UUID id, RoleUser name) {
		this.id = id;
		this.name = name;
	}

	public RoleEntity() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public RoleUser getName() {
		return name;
	}

	public void setName(RoleUser name) {
		this.name = name;
	}
}
