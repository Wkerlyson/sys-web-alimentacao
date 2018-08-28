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

@Entity()
@Table(name = "tbl_receita_cardapio")
public class ReceitaCardapio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_receita_cardapio")
	private Long id;
	
	@Column(name = "dia_da_semana", nullable = false)
	private int diaDaSemana;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receita_id_receita", referencedColumnName = "id_receita", nullable = false)
	private Receita receita;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cardapio_id_cardapio", referencedColumnName = "id_cardapio", nullable = false)
	private Cardapio cardapio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(int diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}
	
	
}
