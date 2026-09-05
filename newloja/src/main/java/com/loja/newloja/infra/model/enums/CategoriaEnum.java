package com.loja.newloja.infra.model.enums;

public enum CategoriaEnum {

    CAMISETAS("CAMISETAS"),
    CALCAS("CALCAS"),
    CALCADOS("CALCADOS");

    private String descricao;

    CategoriaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


}
