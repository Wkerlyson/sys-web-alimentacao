package com.secti.helpers;

public enum TipoRefeicao {
	
	LancheManha("Lanche (Manh�)"), LancheTarde("Lanche (Tarde)"), Almoco("Almo�o"), AlmocoJantar("Almo�o e Jantar");
	
	private String descricao;

	private TipoRefeicao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
