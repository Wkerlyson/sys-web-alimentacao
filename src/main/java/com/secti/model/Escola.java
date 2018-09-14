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
@Table(name = "tbl_escolas")
public class Escola implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_escola")
	private Long id;
	
	@Column(nullable = false)
	private Long inep;
	
	@Column(length = 150, nullable = false)
	private String nome;
	
	@OneToMany(mappedBy="escola", cascade = CascadeType.REMOVE)
	private List<EscolaPrograma> escolaProgramas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Long getInep() {
		return inep;
	}

	public void setInep(Long inep) {
		this.inep = inep;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public List<EscolaPrograma> getEscolaProgramas() {
		return escolaProgramas;
	}

	public void setEscolaProgramas(List<EscolaPrograma> escolaProgramas) {
		this.escolaProgramas = escolaProgramas;
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
		Escola other = (Escola) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
