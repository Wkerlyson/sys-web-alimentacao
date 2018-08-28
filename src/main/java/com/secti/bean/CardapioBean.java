package com.secti.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.secti.model.Cardapio;
import com.secti.service.CardapioService;
import com.secti.util.FacesUtil;

@Named
@ViewScoped
public class CardapioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cardapio cardapio;
	
	@Inject
	private CardapioService service;
	
	private List<Cardapio> cardapios;
	
	@PostConstruct
	private void init() {
		listarTodos();
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
	
	public void listarTodos() {
		cardapios = service.listarTodos();
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
}
