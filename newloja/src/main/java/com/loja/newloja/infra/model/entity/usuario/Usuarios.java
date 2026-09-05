package com.loja.newloja.infra.model.entity.usuario;


import com.loja.newloja.infra.model.enums.SexoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuarios implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "UUID")
	private UUID id;

	@Column(nullable = false, length = 150)
	private String name;

	@Column(unique = true, nullable = false, length = 14)
	private String cpf;

	@Column(nullable = false, length = 15)
	private String contato;

	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataDeNascimento;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private SexoEnum sexo;

	@Column(name = "consentimento_termos", nullable = false)
	private Boolean consentimentoTermos; // Registro de conformidade com a LGPD

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_roles",
					joinColumns = @JoinColumn(name = "usuarios_id", columnDefinition = "UUID"),
					inverseJoinColumns = @JoinColumn(name = "roles_id", columnDefinition = "UUID"))
	private Set<RoleEntity> roles = new HashSet<>();

	//@MapsId
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = false)
	private EnderecoEntity endereco;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream()
						.map(role -> new SimpleGrantedAuthority(role.getName().name()))
						.collect(Collectors.toList());
	}


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public EnderecoEntity getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEntity endereco) {
		this.endereco = endereco;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public Boolean getConsentimentoTermos() {
		return consentimentoTermos;
	}

	public void setConsentimentoTermos(Boolean consentimentoTermos) {
		this.consentimentoTermos = consentimentoTermos;
	}

	@Override
	public @Nullable String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}
}
