package com.loja.newloja.infra.model.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioUpdateDto {

	@NotBlank
	@Size(max = 150)
	private String name;

	@NotBlank
	@Size(max = 15)
	private String contato;

	private String password;

	@Valid
	@NotNull
	private EnderecoRequestDto endereco;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EnderecoRequestDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoRequestDto endereco) {
		this.endereco = endereco;
	}
}
