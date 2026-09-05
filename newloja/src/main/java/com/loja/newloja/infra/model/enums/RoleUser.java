package com.loja.newloja.infra.model.enums;

public enum RoleUser {

	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_USER("ROLE_USER");

	private final String role;

	RoleUser(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

}
