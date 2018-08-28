package com.secti.helpers;


public enum UnidadeMedida {
	
	Undidades("Und - Unidade"), Gramas("g - Gramas"), Mililitros("ml - Mililitro");
	
	private String descricao;

	private UnidadeMedida(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
