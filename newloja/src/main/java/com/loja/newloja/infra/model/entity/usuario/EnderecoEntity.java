package com.loja.newloja.infra.model.entity.usuario;


import com.loja.newloja.infra.model.enums.UfEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "enderecos")
public class EnderecoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "UUID")
	private UUID id;

	@Column(nullable = false, length = 150)
	private String rua;

	@Column(nullable = false, length = 10)
	private String numero;

	@Column(nullable = false, length = 100)
	private String bairro;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 2)
	private UfEnum uf;

	@Column(nullable = false, length = 100)
	private String cidade;

	@Column(nullable = false, length = 9)
	private String cep;

	public EnderecoEntity(UUID id, String rua, String numero, String bairro, UfEnum uf, String cidade, String cep) {
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.uf = uf;
		this.cidade = cidade;
		this.cep = cep;
	}

	public EnderecoEntity() {
	}

	// GETTERS E SETTERS
	public UUID getId() { return id; }
	public void setId(UUID id) { this.id = id; }

	public String getRua() { return rua; }
	public void setRua(String rua) { this.rua = rua; }

	public String getNumero() { return numero; }
	public void setNumero(String numero) { this.numero = numero; }

	public String getBairro() { return bairro; }
	public void setBairro(String bairro) { this.bairro = bairro; }

	public UfEnum getUf() {
		return uf;
	}

	public void setUf(UfEnum uf) {
		this.uf = uf;
	}

	public String getCidade() { return cidade; }
	public void setCidade(String cidade) { this.cidade = cidade; }

	public String getCep() { return cep; }
	public void setCep(String cep) { this.cep = cep; }
}