package com.secti.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.secti.model.Receita;
import com.secti.service.ReceitaService;
import com.secti.util.FacesUtil;

@Named
@ViewScoped
public class ReceitaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReceitaService service;
	
	@Inject
	private Receita receita;
	
	private List<Receita> receitas;
	
	
	public String salvar() {
		try {
			service.salvar(receita);
			FacesUtil.msgInfo("Receita " + receita.getNome().toUpperCase() + " salva com sucesso");
			receita = null;
			return "/private/receitas/lista-receita.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao salvar a receita");
		}
		return null;
	}
	
	public List<Receita> listarTodos(){
		receitas = service.listarTodos();
		return receitas;
	}


	public Receita getReceita() {
		if (receita == null) {
			receita = new Receita();
		}		
		return receita;
	}


	public void setReceita(Receita receita) {
		this.receita = receita;
	}


	public List<Receita> getReceitas() {
		return receitas;
	}


	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

}
