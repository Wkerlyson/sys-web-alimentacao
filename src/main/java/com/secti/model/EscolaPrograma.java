package com.secti.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_escola_programa")
public class EscolaPrograma implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_escola_programa")
	private Long id;
	
	@Column(name = "qtd_aluno")
	private int qtdAluno;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "escola_id_escola", referencedColumnName = "id_escola", nullable = false)
	private Escola escola;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "programa_id_programa", referencedColumnName = "id_programa", nullable = false)
	private Programa programa;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cardapio_id_cardapio", referencedColumnName = "id_cardapio", nullable = false)
	private Cardapio cardapio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQtdAluno() {
		return qtdAluno;
	}

	public void setQtdAluno(int qtdAluno) {
		this.qtdAluno = qtdAluno;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}
	
	
	
	
	
}
