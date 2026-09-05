package com.loja.newloja.infra.model.dto;


public class UsuarioResponseDto {

	private String name;

	private String contato;

	private String password; // Opcional! Se vier nulo/vazio, não altera.

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
}
