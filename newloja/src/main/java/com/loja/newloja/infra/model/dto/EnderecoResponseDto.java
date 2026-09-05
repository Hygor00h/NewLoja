package com.loja.newloja.infra.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponseDto {

	private UUID id;
	private String cep;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;


	public UUID getId() { return id; }
	public void setId(UUID id) { this.id = id; }
	public String getCep() { return cep; }
	public void setCep(String cep) { this.cep = cep; }
	public String getRua() { return rua; }
	public void setRua(String rua) { this.rua = rua; }
	public String getNumero() { return numero; }
	public void setNumero(String numero) { this.numero = numero; }
	public String getBairro() { return bairro; }
	public void setBairro(String bairro) { this.bairro = bairro; }
	public String getCidade() { return cidade; }
	public void setCidade(String cidade) { this.cidade = cidade; }
	public String getUf() { return uf; }
	public void setUf(String uf) { this.uf = uf; }
}