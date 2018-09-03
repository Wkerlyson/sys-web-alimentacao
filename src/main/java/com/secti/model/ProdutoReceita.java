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
@Table(name = "tbl_produto_receita")
@NamedQueries({
	@NamedQuery(name = "ProdutoReceita.listarProdutos", query = "SELECT pr FROM ProdutoReceita pr WHERE pr.receita = :receita"),
	@NamedQuery(name = "ProdutoReceita.removerProduto", query = "DELETE FROM ProdutoReceita WHERE produto = :produto"),
})
public class ProdutoReceita implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_produto_receita")
	private Long id;
	
	@Column(name = "per_capta", nullable = false)
	private double perCapita;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto_id_produto", referencedColumnName = "id_produto", nullable = false)
	private Produto produto;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receita_id_receita", referencedColumnName = "id_receita", nullable = false)
	private Receita receita;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public double getPerCapita() {
		return perCapita;
	}


	public void setPerCapita(double perCapita) {
		this.perCapita = perCapita;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Receita getReceita() {
		return receita;
	}


	public void setReceita(Receita receita) {
		this.receita = receita;
	}
	
	
}
