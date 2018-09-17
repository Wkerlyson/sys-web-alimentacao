package com.secti.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.secti.model.Cardapio;
import com.secti.model.Programa;
import com.secti.service.CardapioService;
import com.secti.service.ProgramaService;
import com.secti.util.FacesUtil;

@Named
@ViewScoped
public class CardapioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cardapio cardapio;
	
	@Inject
	private CardapioService service;
	
	private @Inject ProgramaService programaService;
	
	private List<Cardapio> cardapios;
	private List<Programa> programas;
	
	@PostConstruct
	private void init() {
		listarTodos();
		listarProgramasCadastrados();
		prepararEditar();
	}
	
	
	public String salvar() {
		try {
			service.salvar(cardapio);
			FacesUtil.msgInfo("Cardápio criado com sucesso. Por favor, insira as receitas.");
			cardapio = null;
			return "/private/cardapios/lista-cardapio.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao salvar o cardápio. ERRO: " + e);
		}
		
		return null;
	}
	
	public void prepararEditar() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id != null) {
			cardapio = service.buscarPorId(cardapio, Long.parseLong(id));
		}

	}

	public String editar() {
		try {
			service.editar(cardapio);
			FacesUtil.msgInfo("Cardápio editado com sucesso.");
			cardapio = null;
			return "/private/cardapios/lista-cardapio.xhtml?faces-redirect=true";

		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao editar o Cardápio. ERRO:" + e.getMessage());
		}

		return null;
	}
	
	public void remover() {
		try {
			service.remover(cardapio, cardapio.getId());
			FacesUtil.msgInfo("Cardápio removido com sucesso.");
			cardapio = null;
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao remover a Cardápio. ERRO:" + e.getMessage());
		}
	}
	
	public void listarTodos() {
		cardapios = service.listarTodos();
	}
	
	public void listarProgramasCadastrados() {
		 programas = programaService.listarTodos();
	}

	public Cardapio getCardapio() {
		if (cardapio == null) {
			cardapio = new Cardapio();
		}
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}


	public List<Cardapio> getCardapios() {
		return cardapios;
	}


	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}


	public List<Programa> getProgramas() {
		return programas;
	}


	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}
	
	
}
