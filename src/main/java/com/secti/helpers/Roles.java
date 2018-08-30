package com.secti.helpers;

public enum Roles {
	NORMAL("Normal"), ADMIN("Administrador");
	
	private String descricao;

	private Roles(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	
}
