package com.secti.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.secti.helpers.DiasSemana;
import com.secti.helpers.TipoRefeicao;

@Entity
@Table(name = "tbl_cardapio_receita")
@NamedQueries({@NamedQuery(name = "CardapioReceita.listarReceitas", query = "SELECT r FROM CardapioReceita r WHERE r.cardapio = :cardapio")})
public class CardapioReceita implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cardapio_receita")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cardapio_id_cardapio", referencedColumnName = "id_cardapio", nullable = false)
	private Cardapio cardapio;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receita_id_receita", referencedColumnName = "id_receita", nullable = false)
	private Receita receita;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_refeicao")
	private TipoRefeicao tipoRefeicao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "dia_semana")
	private DiasSemana diaSemana;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public TipoRefeicao getTipoRefeicao() {
		return tipoRefeicao;
	}

	public void setTipoRefeicao(TipoRefeicao tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}

	public DiasSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiasSemana diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	
	
}
