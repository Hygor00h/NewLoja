package com.loja.newloja.infra.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public class EnderecoRequestDto {

	@NotBlank(message = "O CEP é obrigatório.")
	@Pattern(regexp = "\\d{5}-\\d{3}|\\d{8}", message = "O CEP deve estar no formato 12345-678 ou conter 8 dígitos numéricos.")
	private String cep;

	@NotBlank(message = "A rua é obrigatória.")
	@Size(max = 150, message = "A rua não pode exceder 150 caracteres.")
	private String rua;

	@NotBlank(message = "O número é obrigatório.")
	@Size(max = 10, message = "O número não pode exceder 10 caracteres.")
	private String numero;

	@NotBlank(message = "O bairro é obrigatório.")
	@Size(max = 100, message = "O bairro não pode exceder 100 caracteres.")
	private String bairro;

	@NotBlank(message = "A cidade é obrigatória.")
	@Size(max = 100, message = "A cidade não pode exceder 100 caracteres.")
	private String cidade;

	@NotBlank(message = "O estado (UF) é obrigatório.")
	@Size(min = 2, max = 2, message = "O estado deve conter exatamente 2 caracteres (Ex: SP).")
	private String uf;

	public EnderecoRequestDto(String cep, String rua, String numero, String bairro, String cidade, String uf) {
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
	}

	public EnderecoRequestDto() {
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}