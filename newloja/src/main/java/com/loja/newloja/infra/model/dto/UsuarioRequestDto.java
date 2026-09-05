package com.loja.newloja.infra.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.loja.newloja.infra.model.enums.SexoEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Builder
public class UsuarioRequestDto {

	@NotBlank(message = "O e-mail é obrigatório.")
	private String email;

	@NotBlank(message = "A senha é obrigatória.")
	@Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres.")
	private String password;

	@NotBlank(message = "O nome é obrigatório.")
	@Size(max = 150, message = "O nome não pode exceder 150 caracteres.")
	private String name;

	//@Pattern(regexp = "\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve conter 11 dígitos numéricos ou estar no formato 123.456.789-00")
	//@NotBlank(message = "O CPF é obrigatório.")
	//@CPF
	private String cpf;

	@NotNull(message = "O sexo é obrigatório.")
	private SexoEnum sexo;

	@NotBlank(message = "O contato é obrigatório.")
	@Size(max = 15, message = "O contato deve ter no máximo 15 caracteres.")
	private String contato;

	@NotNull(message = "A data de nascimento é obrigatória.")
	@Past(message = "A data de nascimento deve ser uma data passada.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataDeNascimento;

	@Valid
	@NotNull(message = "Os dados de endereço são obrigatórios.")
	private EnderecoRequestDto endereco;

	@NotNull(message = "Você precisa aceitar a Política de Privacidade e os Termos de Uso.")
	@AssertTrue(message = "O consentimento deve ser verdadeiro.")
	private Boolean consentimentoTermos;

	public UsuarioRequestDto(String email, String password, String name, String cpf, SexoEnum sexo, String contato, LocalDate dataDeNascimento, EnderecoRequestDto endereco, Boolean consentimentoTermos) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.cpf = cpf;
		this.sexo = sexo;
		this.contato = contato;
		this.dataDeNascimento = dataDeNascimento;
		this.endereco = endereco;
		this.consentimentoTermos = consentimentoTermos;
	}

	public UsuarioRequestDto() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
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

	public EnderecoRequestDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoRequestDto endereco) {
		this.endereco = endereco;
	}

	public Boolean getConsentimentoTermos() {
		return consentimentoTermos;
	}

	public void setConsentimentoTermos(Boolean consentimentoTermos) {
		this.consentimentoTermos = consentimentoTermos;
	}
}
