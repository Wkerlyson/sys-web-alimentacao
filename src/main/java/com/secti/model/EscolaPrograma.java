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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_escola_programa")
@NamedQueries({
	@NamedQuery(name = "EscolaPrograma.listarProgramas", query = "SELECT ep FROM EscolaPrograma ep WHERE ep.escola = :escola")
})
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
		EscolaPrograma other = (EscolaPrograma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
