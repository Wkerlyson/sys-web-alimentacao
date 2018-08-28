package com.secti.helpers;

public enum TipoRefeicao {
	
	LancheManha("Lanche (Manhã)"), LancheTarde("Lanche (Tarde)"), Almoco("Almoço"), AlmocoJantar("Almoço e Jantar");
	
	private String descricao;

	private TipoRefeicao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
