package com.secti.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.secti.helpers.DiasSemana;
import com.secti.helpers.TipoRefeicao;
import com.secti.model.Cardapio;
import com.secti.model.CardapioReceita;
import com.secti.model.Receita;
import com.secti.service.CardapioReceitaService;
import com.secti.service.CardapioService;
import com.secti.service.ReceitaService;
import com.secti.util.FacesUtil;

@Named
@ViewScoped
public class CardapioReceitaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CardapioService cardapioService;
	
	@Inject
	private Cardapio cardapio;
	
	@Inject
	private CardapioReceita cardapioReceita;
	
	@Inject
	private CardapioReceitaService cardapioReceitaService;
	
	@Inject
	private ReceitaService receitaService;
	
	private List<Receita> receitasCadastradas;

	private List<TipoRefeicao> tiposRefeicao;
	private List<DiasSemana> diasSemana;
	private List<CardapioReceita> cardapiosReceitas;

	@PostConstruct
	private void init() {
		tiposRefeicao = Arrays.asList(TipoRefeicao.values());
		diasSemana = Arrays.asList(DiasSemana.values());
		buscarDadosCardapio();
		buscarReceitas();
		listarReceitasDoCardapio();
	}
	
	public void listarReceitasDoCardapio(){
		cardapiosReceitas = cardapioReceitaService.listarReceitasPorCardapio(cardapio);
	}

	public void salvar() {

		try {
			cardapioReceita.setCardapio(cardapio);
			cardapioReceitaService.salvar(cardapioReceita);
			FacesUtil.msgInfo("Receita adicionado ao cardapio com sucesso.");
			cardapioReceita = null;
			listarReceitasDoCardapio();
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao adicionar a receita ao cardápio. ERRO: " + e.getStackTrace());
			System.err.println(e.getStackTrace());
		}

	}

	private void buscarReceitas() {
		receitasCadastradas = receitaService.listarTodos();
	}

	private void buscarDadosCardapio() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id != null) {
			cardapio = cardapioService.buscarPorId(cardapio, Long.parseLong(id));
		}
	}
	
	
	public List<CardapioReceita> getCardapiosReceitas() {
		return cardapiosReceitas;
	}

	public void setCardapiosReceitas(List<CardapioReceita> cardapiosReceitas) {
		this.cardapiosReceitas = cardapiosReceitas;
	}

	public Cardapio getCardapio() {
		if (cardapio == null)
			cardapio = new Cardapio();
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public CardapioReceita getCardapioReceita() {
		if (cardapioReceita == null)
			cardapioReceita = new CardapioReceita();
		return cardapioReceita;
	}

	public void setCardapioReceita(CardapioReceita cardapioReceita) {
		this.cardapioReceita = cardapioReceita;
	}

	public List<TipoRefeicao> getTiposRefeicao() {
		return tiposRefeicao;
	}

	public void setTiposRefeicao(List<TipoRefeicao> tiposRefeicao) {
		this.tiposRefeicao = tiposRefeicao;
	}

	public List<DiasSemana> getDiasSemana() {
		return diasSemana;
	}

	public void setDiasSemana(List<DiasSemana> diasSemana) {
		this.diasSemana = diasSemana;
	}

	public List<Receita> getReceitasCadastradas() {
		return receitasCadastradas;
	}

	public void setReceitasCadastradas(List<Receita> receitasCadastradas) {
		this.receitasCadastradas = receitasCadastradas;
	}

}
