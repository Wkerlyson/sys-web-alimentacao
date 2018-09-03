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
import javax.persistence.Transient;

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
	
	@Transient
	private double totalCarboidratos;
	
	@Transient
	private double totalFibras;
	
	@Transient
	private double totalLipidios;

	@Transient
	private double totalProteinas;

	@Transient
	private double totalColesterol;

	@Transient
	private double totalCalcio;

	@Transient
	private double totalZinco;

	@Transient
	private double totalFerro;
	
	@Transient
	private double totalSodio;

	@Transient
	private double totalMagnesio;

	@Transient
	private double totalVitaminaA;

	@Transient
	private double totalVitaminaC;
	
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

	public double getTotalCarboidratos() {
		return totalCarboidratos;
	}

	public void setTotalCarboidratos(double totalCarboidratos) {
		this.totalCarboidratos = totalCarboidratos;
	}

	public double getTotalFibras() {
		return totalFibras;
	}

	public void setTotalFibras(double totalFibras) {
		this.totalFibras = totalFibras;
	}

	public double getTotalLipidios() {
		return totalLipidios;
	}

	public void setTotalLipidios(double totalLipidios) {
		this.totalLipidios = totalLipidios;
	}

	public double getTotalProteinas() {
		return totalProteinas;
	}

	public void setTotalProteinas(double totalProteinas) {
		this.totalProteinas = totalProteinas;
	}

	public double getTotalColesterol() {
		return totalColesterol;
	}

	public void setTotalColesterol(double totalColesterol) {
		this.totalColesterol = totalColesterol;
	}

	public double getTotalCalcio() {
		return totalCalcio;
	}

	public void setTotalCalcio(double totalCalcio) {
		this.totalCalcio = totalCalcio;
	}

	public double getTotalZinco() {
		return totalZinco;
	}

	public void setTotalZinco(double totalZinco) {
		this.totalZinco = totalZinco;
	}

	public double getTotalFerro() {
		return totalFerro;
	}

	public void setTotalFerro(double totalFerro) {
		this.totalFerro = totalFerro;
	}

	public double getTotalSodio() {
		return totalSodio;
	}

	public void setTotalSodio(double totalSodio) {
		this.totalSodio = totalSodio;
	}

	public double getTotalMagnesio() {
		return totalMagnesio;
	}

	public void setTotalMagnesio(double totalMagnesio) {
		this.totalMagnesio = totalMagnesio;
	}

	public double getTotalVitaminaA() {
		return totalVitaminaA;
	}

	public void setTotalVitaminaA(double totalVitaminaA) {
		this.totalVitaminaA = totalVitaminaA;
	}

	public double getTotalVitaminaC() {
		return totalVitaminaC;
	}

	public void setTotalVitaminaC(double totalVitaminaC) {
		this.totalVitaminaC = totalVitaminaC;
	}
	
	
	
}
