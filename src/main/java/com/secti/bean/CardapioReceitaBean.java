package com.secti.bean;

import java.io.Serializable;
import java.text.DecimalFormat;
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
	
	private double totalCalCarboidrato;
	private double totalCalProteina;
	private double totalCalLipidio;
	
	private double VET;

	@PostConstruct
	private void init() {
		tiposRefeicao = Arrays.asList(TipoRefeicao.values());
		diasSemana = Arrays.asList(DiasSemana.values());
		buscarDadosCardapio();
		buscarReceitas();
		listarReceitasDoCardapio();
		calcularValorNutricional();
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
	
	public void remover() {
		try {
			cardapioReceitaService.remover(cardapioReceita, cardapioReceita.getId());
			FacesUtil.msgInfo("Receita foi removida com sucesso ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void calcularValorNutricional() {
		
		for (CardapioReceita cardapioReceita : cardapiosReceitas) {
			this.cardapioReceita.setTotalCalcio(this.cardapioReceita.getTotalCalcio() + cardapioReceita.getReceita().getTotalCalcio());
			this.cardapioReceita.setTotalCarboidratos(this.cardapioReceita.getTotalCarboidratos() + cardapioReceita.getReceita().getTotalCarboidratos());
			this.cardapioReceita.setTotalFibras(this.cardapioReceita.getTotalFibras() + cardapioReceita.getReceita().getTotalFibras());
			this.cardapioReceita.setTotalLipidios(this.cardapioReceita.getTotalLipidios() + cardapioReceita.getReceita().getTotalLipidios());
			this.cardapioReceita.setTotalProteinas(this.cardapioReceita.getTotalProteinas() + cardapioReceita.getReceita().getTotalProteinas());
			this.cardapioReceita.setTotalZinco(this.cardapioReceita.getTotalZinco() + cardapioReceita.getReceita().getTotalZinco());
			this.cardapioReceita.setTotalFerro(this.cardapioReceita.getTotalFerro() + cardapioReceita.getReceita().getTotalFerro());
			this.cardapioReceita.setTotalSodio(this.cardapioReceita.getTotalSodio() + cardapioReceita.getReceita().getTotalSodio());
			this.cardapioReceita.setTotalMagnesio(this.cardapioReceita.getTotalMagnesio() + cardapioReceita.getReceita().getTotalMagnesio());
			this.cardapioReceita.setTotalVitaminaA(this.cardapioReceita.getTotalVitaminaA() + cardapioReceita.getReceita().getTotalVitaminaA());
			this.cardapioReceita.setTotalVitaminaC(this.cardapioReceita.getTotalVitaminaC() + cardapioReceita.getReceita().getTotalVitaminaC());
		}
		
		this.cardapioReceita.setTotalCarboidratos(this.cardapioReceita.getTotalCarboidratos() / 5);
		this.cardapioReceita.setTotalProteinas(this.cardapioReceita.getTotalProteinas() / 5);
		this.cardapioReceita.setTotalLipidios(this.cardapioReceita.getTotalLipidios() / 5);
		this.cardapioReceita.setTotalCalcio(this.cardapioReceita.getTotalCalcio() / 5);
		this.cardapioReceita.setTotalFibras(this.cardapioReceita.getTotalFibras() / 5);
		this.cardapioReceita.setTotalZinco(this.cardapioReceita.getTotalZinco() / 5);
		this.cardapioReceita.setTotalFerro(this.cardapioReceita.getTotalFerro() / 5);
		this.cardapioReceita.setTotalSodio(this.cardapioReceita.getTotalSodio() / 5);
		this.cardapioReceita.setTotalMagnesio(this.cardapioReceita.getTotalMagnesio() / 5);
		this.cardapioReceita.setTotalVitaminaA(this.cardapioReceita.getTotalVitaminaA() / 5);
		this.cardapioReceita.setTotalVitaminaC(this.cardapioReceita.getTotalVitaminaC() / 5);
		
		this.totalCalCarboidrato = this.cardapioReceita.getTotalCarboidratos() * 4;
		this.totalCalProteina = this.cardapioReceita.getTotalProteinas() * 4;
		this.totalCalLipidio = this.cardapioReceita.getTotalLipidios() * 9;
		
				
		this.setVET((this.totalCalCarboidrato + this.totalCalProteina + this.totalCalLipidio));
		
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

	public double getVET() {
		return VET;
	}

	public void setVET(double vET) {
		VET = vET;
	}
	
	

}
