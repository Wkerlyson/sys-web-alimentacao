package com.secti.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_receitas")
public class Receita implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_receita")
	private Long id;
	
	@Column(nullable = false, length = 255)
	private String nome;
	
	@Column(name = "total_caboidratos", precision = 2, scale = 2)
	private double totalCarboidratos;
	
	@Column(name = "total_fibras", precision = 2, scale = 2)
	private double totalFibras;
	
	@Column(name = "total_lipidios", precision = 2, scale = 2)
	private double totalLipidios;

	@Column(name = "total_proteinas", precision = 2, scale = 2)
	private double totalProteinas;

	@Column(name = "total_colesterol", precision = 2, scale = 2)
	private double totalColesterol;

	@Column(name = "total_calcio", precision = 2, scale = 2)
	private double totalCalcio;

	@Column(name = "total_zinco", precision = 2, scale = 2)
	private double totalZinco;

	@Column(name = "total_ferro", precision = 2, scale = 2)
	private double totalFerro;
	
	@Column(name = "total_sodio", precision = 2, scale = 2)
	private double totalSodio;

	@Column(name = "total_magnesio", precision = 2, scale = 2)
	private double totalMagnesio;

	@Column(name = "total_vitamina_a", precision = 2, scale = 2)
	private double totalVitaminaA;

	@Column(name = "total_vitamina_c", precision = 2, scale = 2)
	private double totalVitaminaC;
	
	@OneToMany(mappedBy="receita", cascade = CascadeType.REMOVE)
	private List<ProdutoReceita> produtoReceitas;

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
	
	

	public List<ProdutoReceita> getProdutoReceitas() {
		return produtoReceitas;
	}

	public void setProdutoReceitas(List<ProdutoReceita> produtoReceitas) {
		this.produtoReceitas = produtoReceitas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receita other = (Receita) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
