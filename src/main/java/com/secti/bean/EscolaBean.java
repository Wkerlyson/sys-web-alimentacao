package com.secti.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JOptionPane;

import org.primefaces.event.DragDropEvent;

import com.secti.model.Escola;
import com.secti.model.EscolaPrograma;
import com.secti.model.Produto;
import com.secti.model.Programa;
import com.secti.service.EscolaService;
import com.secti.service.ProgramaService;
import com.secti.util.FacesUtil;

@Named
@ViewScoped
public class EscolaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EscolaService service;

	@Inject
	private Escola escola;

	private @Inject ProgramaService programaService;

	private List<Escola> escolas;

	private List<Escola> escolasFiltradas;

	private List<Programa> programasDisponiveis;


	private List<EscolaPrograma> programasSelecionados;

	@Inject
	private EscolaPrograma escolaPrograma;

	
	@PostConstruct
	public void init() {
		prepararEditar();
		listarProgramas();

		programasSelecionados = new ArrayList<EscolaPrograma>();
	}

	public void onProgramaDrop(DragDropEvent ddEvent) {
		Programa prog = ((Programa) ddEvent.getData());
		escolaPrograma = new EscolaPrograma();
				
		programasDisponiveis.remove(prog);
		escolaPrograma.setPrograma(prog);
		programasSelecionados.add(escolaPrograma);
	}

	public String salvar() {

		try {

			service.salvarEscolaPrograma(escola, programasSelecionados);
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

	public void listarProgramas() {
		programasDisponiveis = programaService.listarTodos();
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

	public List<Programa> getProgramasDisponiveis() {
		return programasDisponiveis;
	}

	public void setProgramasDisponiveis(List<Programa> programasDisponiveis) {
		this.programasDisponiveis = programasDisponiveis;
	}

	public List<EscolaPrograma> getProgramasSelecionados() {
		return programasSelecionados;
	}

	public void setProgramasSelecionados(List<EscolaPrograma> programasSelecionados) {
		this.programasSelecionados = programasSelecionados;
	}
	
	

}
