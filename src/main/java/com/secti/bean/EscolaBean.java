package com.secti.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.secti.model.Escola;
import com.secti.service.EscolaService;
import com.secti.util.FacesUtil;

@Named
@ViewScoped
public class EscolaBean implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Inject
	private EscolaService service;
	
	@Inject
	private Escola escola;

	private List<Escola> escolas;

	private List<Escola> escolasFiltradas;

	@PostConstruct
	public void init() {
		prepararEditar();
	}
	
	
	public String salvar() {

		try {

			service.salvar(escola);
			FacesUtil.msgInfo("Escola salva com sucesso");
			escola = null;
			return "/private/escolas/lista-escola.xhtml?faces-redirect=true";

		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao salvar a Escola. ERRO: " + e);
		}

		return null;
	}

	public void listarTodos() {
		escolas = service.listarTodos();
	}

	public void remover() {
		try {
			service.remover(escola, escola.getId());
			FacesUtil.msgInfo("Escola removida com sucesso.");
			escola = null;
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao remover a Escola. ERRO:" + e.getMessage());
		}
	}

	public void prepararEditar() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id != null) {
			escola = service.buscarPorId(escola, Long.parseLong(id));
		}

	}

	public String editar() {
		try {
			service.editar(escola);
			FacesUtil.msgInfo("Escola editado com sucesso.");
			escola = null;
			return "/private/escolas/lista-escola.xhtml?faces-redirect=true";

		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao editar o Programa. ERRO:" + e.getMessage());
		}

		return null;
	}

	public Escola getEscola() {
		if (escola == null) {
			escola = new Escola();
		}
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public List<Escola> getEscolas() {
		return escolas;
	}

	public void setEscolas(List<Escola> escolas) {
		this.escolas = escolas;
	}

	public List<Escola> getEscolasFiltradas() {
		return escolasFiltradas;
	}

	public void setEscolasFiltradas(List<Escola> escolasFiltradas) {
		this.escolasFiltradas = escolasFiltradas;
	}
	
	

}
