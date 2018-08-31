package com.secti.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.exception.ConstraintViolationException;

import com.secti.helpers.UnidadeMedida;
import com.secti.model.Produto;
import com.secti.service.ProdutoService;
import com.secti.util.FacesUtil;

@Named
@ViewScoped
public class ProdutoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produto produto;
	
	@Inject
	private ProdutoService service;
	
	private List<Produto> produtos;
	
	private List<UnidadeMedida> unidadeMedidas;
	
	private boolean renderComponentes = false;

	
	@PostConstruct
	private void init() {
		unidadeMedidas = Arrays.asList(UnidadeMedida.values());
		prepararEditar();
	}
	
	public String salvar() {
		try {
			service.salvar(produto);
			FacesUtil.msgInfo("O produto " + produto.getNome().toUpperCase() + " foi salvo com sucesso ");
			produto = null;
			return "/private/produtos/lista-produto.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao salvar o produto. ERRO: " + e);
		}
		
		return null;
	}
	
	
	public List<Produto> listarTodos(){
		produtos = service.listarTodos();
		return produtos;
	}
	
	public void remover() {
		try {
			service.remover(produto, produto.getId());
			FacesUtil.msgInfo("O produto " + produto.getNome().toUpperCase() + " foi removido com sucesso ");
			produto = null;
		}catch (ConstraintViolationException e) {
			FacesUtil.msgErro("Não foi possível remover o produto. O mesmo encontra-se cadastrado em uma receita. "
					+ "Remova da receita para posteriormente removê-lo." + e.getMessage());
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao remover o produto. " + e.getMessage());
		}
	}
	
	public void prepararEditar() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id != null) {
			produto = service.buscarPorId(produto, Long.parseLong(id));
			renderComponentes();
		}

	}
	
	public String editar() {
		try {
			service.editar(produto);
			FacesUtil.msgInfo("Produto editado com sucesso");
			produto = null;
			return "/private/produtos/lista-produto.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao editar o produto. " + e.getMessage());
		}
		return null;
	}
	
	public UnidadeMedida[] getUnidades() {
		return UnidadeMedida.values();
	}


	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
		}
		return produto;
	}
	
	
	public void renderComponentes() {		
		if (produto.getUnidadeMedida().getDescricao().equals("g - Gramas") || produto.getUnidadeMedida().getDescricao().equals("ml - Mililitro")) {
			renderComponentes = true;
		}else {
			renderComponentes = false;
		}
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public List<UnidadeMedida> getUnidadeMedidas() {
		return unidadeMedidas;
	}


	public void setUnidadeMedidas(List<UnidadeMedida> unidadeMedidas) {
		this.unidadeMedidas = unidadeMedidas;
	}

	public boolean isRenderComponentes() {
		return renderComponentes;
	}

	public void setRenderComponentes(boolean renderComponentes) {
		this.renderComponentes = renderComponentes;
	}



	public ProdutoService getService() {
		return service;
	}



	public void setService(ProdutoService service) {
		this.service = service;
	}



	public List<Produto> getProdutos() {
		return produtos;
	}



	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	

}
