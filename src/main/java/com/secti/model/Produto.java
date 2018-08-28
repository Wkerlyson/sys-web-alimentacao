package com.secti.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.secti.helpers.UnidadeMedida;

@Entity
@Table(name = "tbl_produtos")
public class Produto implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_produto")
	private Long id;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(name = "valor_monetario")
	private double valorMonetario;

	private double carboidratos;

	private double fibras;

	private double lipidios;

	private double proteinas;

	private double colesterol;

	private double calcio;

	private double zinco;

	private double ferro;

	private double sodio;

	private double magnesio;
	
	@Transient
	private double perCapta;

	@Column(name = "vitamina_a")
	private double vitaminaA;

	@Column(name = "vitamina_c")
	private double vitaminaC;

	@Column(name = "und_medida")
	@Enumerated(EnumType.STRING)
	private UnidadeMedida unidadeMedida;
	
	@Column(name = "valor_total_em_unidade_medida")
	private double valorTotalEmUndMedida;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorMonetario() {
		return valorMonetario;
	}

	public void setValorMonetario(double valorMonetario) {
		this.valorMonetario = valorMonetario;
	}

	public double getCarboidratos() {
		return carboidratos;
	}

	public void setCarboidratos(double carboidratos) {
		this.carboidratos = carboidratos;
	}

	public double getFibras() {
		return fibras;
	}

	public void setFibras(double fibras) {
		this.fibras = fibras;
	}

	public double getLipidios() {
		return lipidios;
	}

	public void setLipidios(double lipidios) {
		this.lipidios = lipidios;
	}

	public double getProteinas() {
		return proteinas;
	}

	public void setProteinas(double proteinas) {
		this.proteinas = proteinas;
	}

	public double getColesterol() {
		return colesterol;
	}

	public void setColesterol(double colesterol) {
		this.colesterol = colesterol;
	}

	public double getCalcio() {
		return calcio;
	}

	public void setCalcio(double calcio) {
		this.calcio = calcio;
	}

	public double getZinco() {
		return zinco;
	}

	public void setZinco(double zinco) {
		this.zinco = zinco;
	}

	public double getFerro() {
		return ferro;
	}

	public void setFerro(double ferro) {
		this.ferro = ferro;
	}

	public double getSodio() {
		return sodio;
	}

	public void setSodio(double sodio) {
		this.sodio = sodio;
	}

	public double getMagnesio() {
		return magnesio;
	}

	public void setMagnesio(double magnesio) {
		this.magnesio = magnesio;
	}

	public double getVitaminaA() {
		return vitaminaA;
	}

	public void setVitaminaA(double vitaminaA) {
		this.vitaminaA = vitaminaA;
	}

	public double getVitaminaC() {
		return vitaminaC;
	}

	public void setVitaminaC(double vitaminaC) {
		this.vitaminaC = vitaminaC;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public double getValorTotalEmUndMedida() {
		return valorTotalEmUndMedida;
	}

	public void setValorTotalEmUndMedida(double valorTotalEmUndMedida) {
		this.valorTotalEmUndMedida = valorTotalEmUndMedida;
	}

	public double getPerCapta() {
		return perCapta;
	}

	public void setPerCapta(double perCapta) {
		this.perCapta = perCapta;
	}

	
	

}
