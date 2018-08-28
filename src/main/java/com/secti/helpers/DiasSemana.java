package com.secti.helpers;

public enum DiasSemana {
	Segunda("Segunda-feira"), Terca("Terça-feira"), Quarta("Quarta-feira"), Quinta("Quinta-feira"), Sexta("Sexta-feira");
	
	private String descricao;

	private DiasSemana(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
