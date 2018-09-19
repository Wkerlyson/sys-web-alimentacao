package com.secti.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.DragDropEvent;

import com.secti.model.Produto;
import com.secti.model.ProdutoReceita;
import com.secti.model.Receita;
import com.secti.service.ProdutoReceitaService;
import com.secti.service.ProdutoService;
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
	
	private List<Produto> produtosDisponiveis;

	private List<ProdutoReceita> produtosSelecionados;
	
	private List<Produto> produtosParaRemover;

	private @Inject ProdutoReceita produtoReceita;
	
	private @Inject ProdutoService produtoService;

	private @Inject ProdutoReceitaService produtoReceitaService;

	private @Inject Produto produto;
	
	@PostConstruct
	private void ini() {
		produtosSelecionados = new ArrayList<ProdutoReceita>();
		produtosDisponiveis = new ArrayList<Produto>();
		produtosParaRemover = new ArrayList<Produto>();
		
		buscarDadosReceita();
		listarProdutosDisponiveis();
	}
	
	public void onProdutoDrop(DragDropEvent ddEvent) {
		Produto prod = ((Produto) ddEvent.getData());
		produtoReceita = new ProdutoReceita();
		produtosDisponiveis.remove(prod);
		produtoReceita.setProduto(prod);
		produtosSelecionados.add(produtoReceita);
	}
	
	public void buscarDadosReceita() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id != null) {
			receita = service.buscarPorId(receita, Long.parseLong(id));
			buscarProdutoReceita(receita);
		}
	}
	
	public String salvar() {
		try {
			service.salvarProdutoReceita(receita, produtosSelecionados);
			FacesUtil.msgInfo("Receita " + receita.getNome().toUpperCase() + " salva com sucesso");
			receita = null;
			return "/private/receitas/lista-receita.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao salvar a receita");
		}
		return null;
	}
	
	public String editar() {
		try {
			zerarValores();
			
			for (ProdutoReceita produtoReceita : produtosSelecionados) {
				atualizarInfoNutriReceita(produtoReceita);
			}
			
			service.editarProdutoReceita(receita, produtosSelecionados, produtosParaRemover);
			FacesUtil.msgInfo("Receita " + receita.getNome().toUpperCase() + " editada com sucesso");
			receita = null;
			return "/private/receitas/lista-receita.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao editar a receita");
		}
		return null;
	}
	
	public void removerProdutoSelecionado(ProdutoReceita prod) {
		produtosDisponiveis.add(prod.getProduto());
		
		if (!(produtosParaRemover.contains(prod.getProduto()))) {
			produtosParaRemover.add(prod.getProduto());
		}
		
		produtosSelecionados.remove(prod);
	}
	
	public List<Receita> listarTodos(){
		receitas = service.listarTodos();
		return receitas;
	}
	
	public void listarProdutosDisponiveis(){
		produtosDisponiveis = produtoService.listarTodos();
	}
	
	public void buscarProdutoReceita(Receita receita){
		produtosSelecionados = produtoReceitaService.listarProdutos(receita);
		
		for (int i = 0; i < produtosSelecionados.size(); i++) {
			for (int j = 0; j < produtosDisponiveis.size(); j++) {
				if (produtosSelecionados.get(i).getProduto().getId() == produtosDisponiveis.get(j).getId()) {
					produtosDisponiveis.remove(j);
				}
			}

		}
		
	}
	
	public void atualizarInfoNutriReceita(ProdutoReceita pr) {
		receita.setTotalCalcio(((pr.getProduto().getCalcio() / 100) * pr.getPerCapita()) + receita.getTotalCalcio());
		receita.setTotalCarboidratos(((pr.getProduto().getCarboidratos() / 100) * pr.getPerCapita()) + receita.getTotalCarboidratos());
		receita.setTotalFibras(((pr.getProduto().getFibras() / 100) * pr.getPerCapita()) + receita.getTotalFibras());
		receita.setTotalLipidios(((pr.getProduto().getLipidios() / 100) * pr.getPerCapita()) + receita.getTotalLipidios());
		receita.setTotalProteinas(((pr.getProduto().getProteinas() / 100) * pr.getPerCapita()) + receita.getTotalProteinas());
		receita.setTotalColesterol(((pr.getProduto().getColesterol() / 100) * pr.getPerCapita()) + receita.getTotalColesterol());
		receita.setTotalZinco(((pr.getProduto().getZinco() / 100) * pr.getPerCapita()) + receita.getTotalZinco());
		receita.setTotalFerro(((pr.getProduto().getFerro() / 100) * pr.getPerCapita()) + receita.getTotalFerro());
		receita.setTotalSodio(((pr.getProduto().getSodio() / 100) * pr.getPerCapita()) + receita.getTotalSodio());
		receita.setTotalMagnesio(((pr.getProduto().getMagnesio() / 100) * pr.getPerCapita()) + receita.getTotalMagnesio());
		receita.setTotalVitaminaA(((pr.getProduto().getVitaminaA() / 100) * pr.getPerCapita()) + receita.getTotalVitaminaA());
		receita.setTotalVitaminaC(((pr.getProduto().getVitaminaC() / 100) * pr.getPerCapita()) + receita.getTotalVitaminaC());
	}
	
	public void zerarValores() {
		receita.setTotalCalcio(0);
		receita.setTotalCarboidratos(0);
		receita.setTotalFibras(0);
		receita.setTotalLipidios(0);
		receita.setTotalProteinas(0);
		receita.setTotalColesterol(0);
		receita.setTotalZinco(0);
		receita.setTotalFerro(0);
		receita.setTotalSodio(0);
		receita.setTotalMagnesio(0);
		receita.setTotalVitaminaA(0);
		receita.setTotalVitaminaC(0);
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


	public List<Produto> getProdutosDisponiveis() {
		return produtosDisponiveis;
	}


	public void setProdutosDisponiveis(List<Produto> produtosDisponiveis) {
		this.produtosDisponiveis = produtosDisponiveis;
	}


	public List<ProdutoReceita> getProdutosSelecionados() {
		return produtosSelecionados;
	}


	public void setProdutosSelecionados(List<ProdutoReceita> produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}


	public ProdutoReceita getProdutoReceita() {
		return produtoReceita;
	}


	public void setProdutoReceita(ProdutoReceita produtoReceita) {
		this.produtoReceita = produtoReceita;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	

}