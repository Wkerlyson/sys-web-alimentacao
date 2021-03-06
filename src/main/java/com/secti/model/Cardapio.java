package com.secti.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_cardapio")
public class Cardapio implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cardapio")
	private Long id;
	
	@Column(length = 200, nullable = false)
	private String nome;
	
	@Column(name = "semestre_cardapio", nullable = false)
	private Integer semestre;
	
	@Column(name = "ano_cardapio", nullable = false)
	private Integer ano;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "programa_id_cardapio", referencedColumnName = "id_programa", nullable = false)
	private Programa programa;
	
	@OneToMany(mappedBy="cardapio", cascade = CascadeType.REMOVE)
	private List<CardapioReceita> cardapioReceitas;
	
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
	

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	

	public List<CardapioReceita> getCardapioReceitas() {
		return cardapioReceitas;
	}

	public void setCardapioReceitas(List<CardapioReceita> cardapioReceitas) {
		this.cardapioReceitas = cardapioReceitas;
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
		Cardapio other = (Cardapio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
