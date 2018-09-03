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

import com.secti.dao.ProdutoReceitaDAO;
import com.secti.model.Produto;
import com.secti.model.ProdutoReceita;
import com.secti.model.Receita;
import com.secti.service.ProdutoReceitaService;
import com.secti.service.ProdutoService;
import com.secti.service.ReceitaService;
import com.secti.util.FacesUtil;

@Named
@ViewScoped
public class ProdutoReceitaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Receita receita;
	
	@Inject
	private ReceitaService receitaService;
	
	@Inject
	private Produto produto;
	
	@Inject
	private ProdutoReceita produtoReceita;
	
	@Inject
	private ProdutoService produtoService;
	
	@Inject
	private ProdutoReceitaService produtoReceitaService;
	
	@Inject
	private ProdutoReceitaDAO prDao;
	
	@Inject
	private Produto produtoSelecionado;
	
	@Inject
	private ProdutoReceitaDAO produtoReceitaDAO;
	
	private List<Produto> produtosDisponiveis;
	
	private List<Produto> produtosFiltrados;
	
	private List<Produto> droppedProdutosDisponiveis;
	
	private List<Produto> produtosParaRemover;
	
	
	
	private double perCapta;
	
	@PostConstruct
    private void init() {
		buscarDadosReceita();
		listarProdutosDisponiveis();
        droppedProdutosDisponiveis = new ArrayList<Produto>();
        produtosParaRemover = new ArrayList<Produto>();
        buscarProdutoReceita();
    }
	
	public void onProdutoDrop(DragDropEvent ddEvent) {
        Produto prod = ((Produto) ddEvent.getData());
  
        droppedProdutosDisponiveis.add(prod);
        produtosDisponiveis.remove(prod);
    }
	
	
	public List<Produto> listarProdutosDisponiveis(){
		produtosDisponiveis = produtoService.listarTodos();
		return produtosDisponiveis;
	}
	
	public void buscarDadosReceita() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id != null) {
			receita = receitaService.buscarPorId(receita, Long.parseLong(id));
		}
	}
	
	public String salvarProdutosNaReceita(){
		try {
			for (Produto produto : droppedProdutosDisponiveis) {
				produtoReceita = new ProdutoReceita();
				produtoReceita.setProduto(produto);
				produtoReceita.setReceita(receita);
				produtoReceita.setPerCapita(produto.getPerCapta());
				atualizarInfoNutriReceita(produto);
				produtoReceitaService.inserirProdutos(produtoReceita);
				produtoReceita = null;
			}
			receitaService.editar(receita);
			
			FacesUtil.msgInfo("Produtos adicionados com sucesso");
			return "/private/receitas/lista-receita.xhtml?faces-redirect=true";
			
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao salvar os produtos da receita. Erro: " + e );
		}	
		
		return null;
	}
	
	public void editarReceita() {
		
		try {
			
			for (Produto prodRemover : produtosParaRemover) {
				prDao.removerProduto(prodRemover, receita);
			}
			
			
			for (Produto produto : droppedProdutosDisponiveis) {
				produtoReceita.setProduto(produto);
				produtoReceita.setReceita(receita);
				produtoReceita.setPerCapita(produto.getPerCapta());				
				atualizarInfoNutriReceita2(produto);
			}
			
			FacesUtil.msgInfo("Produtos removidos com sucesso");
			
			receitaService.editar(receita);
						
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao salvar os produtos da receita. Erro: " + e );
		}	
	}
	
	
	public List<ProdutoReceita> buscarProdutoReceita(){
		produto = new Produto();
		List<ProdutoReceita> p = new ArrayList<ProdutoReceita>();
		p = produtoReceitaDAO.listarProdutos(receita);
		
		for (ProdutoReceita produtoReceita : p) {
			System.out.println(produtoReceita.getPerCapita());

			produtosDisponiveis.remove(produtoReceita.getProduto());
			droppedProdutosDisponiveis.add(produtoReceita.getProduto());
			produto.setPerCapta(produtoReceita.getPerCapita());
		}
		
		return p;
	}
	
	
	public void removerProdutoDropped(Produto produto) { //remove o produto da lista de selecionados
		
		produtosParaRemover.add(produto);
		
		for (Produto p : droppedProdutosDisponiveis) {
			if (p == produto) {
				droppedProdutosDisponiveis.remove(produto);
				produtosDisponiveis.add(produto);
				return;
			}
		}
	}
	
	public void atualizarInfoNutriReceita(Produto produto) {
		receita.setTotalCalcio(((produto.getCalcio() / 100) * produto.getPerCapta()) + receita.getTotalCalcio());
		receita.setTotalCarboidratos(((produto.getCarboidratos() / 100) * produto.getPerCapta()) + receita.getTotalCarboidratos());
		receita.setTotalFibras(((produto.getFibras() / 100) * produto.getPerCapta()) + receita.getTotalFibras());
		receita.setTotalLipidios(((produto.getLipidios() / 100) * produto.getPerCapta()) + receita.getTotalLipidios());
		receita.setTotalProteinas(((produto.getProteinas() / 100) * produto.getPerCapta()) + receita.getTotalProteinas());
		receita.setTotalColesterol(((produto.getColesterol() / 100) * produto.getPerCapta()) + receita.getTotalColesterol());
		receita.setTotalZinco(((produto.getZinco() / 100) * produto.getPerCapta()) + receita.getTotalZinco());
		receita.setTotalFerro(((produto.getFerro() / 100) * produto.getPerCapta()) + receita.getTotalFerro());
		receita.setTotalSodio(((produto.getSodio() / 100) * produto.getPerCapta()) + receita.getTotalSodio());
		receita.setTotalMagnesio(((produto.getMagnesio() / 100) * produto.getPerCapta()) + receita.getTotalMagnesio());
		receita.setTotalVitaminaA(((produto.getVitaminaA() / 100) * produto.getPerCapta()) + receita.getTotalVitaminaA());
		receita.setTotalVitaminaC(((produto.getVitaminaC() / 100) * produto.getPerCapta()) + receita.getTotalVitaminaC());
	}
	
	
	public void atualizarInfoNutriReceita2(Produto produto) {
		receita.setTotalCalcio(receita.getTotalCalcio() - ((produto.getCalcio() / 100) * produto.getPerCapta()));
		receita.setTotalCarboidratos(receita.getTotalCarboidratos() - ((produto.getCarboidratos() / 100) * produto.getPerCapta()));
		receita.setTotalFibras(receita.getTotalFibras() - ((produto.getFibras() / 100) * produto.getPerCapta()));
		receita.setTotalLipidios(receita.getTotalLipidios() - ((produto.getLipidios() / 100) * produto.getPerCapta()));
		receita.setTotalProteinas(receita.getTotalProteinas() - ((produto.getProteinas() / 100) * produto.getPerCapta()));
		receita.setTotalColesterol(receita.getTotalColesterol() - ((produto.getColesterol() / 100) * produto.getPerCapta()));
		receita.setTotalZinco(receita.getTotalZinco() - ((produto.getZinco() / 100) * produto.getPerCapta()));
		receita.setTotalFerro(receita.getTotalFerro() - ((produto.getFerro() / 100) * produto.getPerCapta()));
		receita.setTotalSodio(((produto.getSodio() / 100) * produto.getPerCapta()) + receita.getTotalSodio());
		receita.setTotalMagnesio(receita.getTotalMagnesio() - ((produto.getMagnesio() / 100) * produto.getPerCapta()));
		receita.setTotalVitaminaA(receita.getTotalVitaminaA() - ((produto.getVitaminaA() / 100) * produto.getPerCapta()));
		receita.setTotalVitaminaC(receita.getTotalVitaminaC() - ((produto.getVitaminaC() / 100) * produto.getPerCapta()));
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

	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
		}
		return produto;
	}
	
	

	public ReceitaService getReceitaService() {
		return receitaService;
	}

	public void setReceitaService(ReceitaService receitaService) {
		this.receitaService = receitaService;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public ProdutoReceitaService getProdutoReceitaService() {
		return produtoReceitaService;
	}

	public void setProdutoReceitaService(ProdutoReceitaService produtoReceitaService) {
		this.produtoReceitaService = produtoReceitaService;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutosDisponiveis() {
		return produtosDisponiveis;
	}

	public void setProdutosDisponiveis(List<Produto> produtosDisponiveis) {
		this.produtosDisponiveis = produtosDisponiveis;
	}

	public List<Produto> getDroppedProdutosDisponiveis() {
		return droppedProdutosDisponiveis;
	}

	public void setDroppedProdutosDisponiveis(List<Produto> droppedProdutosDisponiveis) {
		this.droppedProdutosDisponiveis = droppedProdutosDisponiveis;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	public ProdutoReceita getProdutoReceita() {
		if (produtoReceita == null) {
			produtoReceita = new ProdutoReceita();
		}
		return produtoReceita;
	}

	public void setProdutoReceita(ProdutoReceita produtoReceita) {
		this.produtoReceita = produtoReceita;
	}

	public double getPerCapta() {
		return perCapta;
	}

	public void setPerCapta(double perCapta) {
		this.perCapta = perCapta;
	}
	
	
	 
	
}
