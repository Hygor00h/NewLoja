package com.loja.newloja.infra.model.enums;

public enum SexoEnum {

	MASCULINO("MASCULINO"),
	FEMININO("FEMININO");

	private String descricao;

	SexoEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
