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

import com.secti.model.Escola;
import com.secti.model.EscolaPrograma;
import com.secti.model.Programa;
import com.secti.service.EscolaProgramaService;
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

	private @Inject EscolaPrograma escolaPrograma;

	private @Inject EscolaProgramaService escolaProgramaService;

	private @Inject Programa programa;

	@PostConstruct
	public void init() {
		programasSelecionados = new ArrayList<EscolaPrograma>();

		listarProgramasCadastrados();
		prepararEditar();
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

	public void removerPrograma(EscolaPrograma escolaPrograma) {
		try {
			
			if (escolaPrograma.getId() != null) {
				escolaProgramaService.remover(escolaPrograma, escolaPrograma.getId());
			}

			programasSelecionados.remove(escolaPrograma);

			programa.setId(escolaPrograma.getPrograma().getId());
			programa.setNome(escolaPrograma.getPrograma().getNome());
			programa.setRecurso(escolaPrograma.getPrograma().getRecurso());
			programasDisponiveis.add(programa);

			FacesUtil.msgInfo("Programa removido com sucesso");

		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao remover o programa. ERRO: " + e.getMessage());
		}
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
			buscarEscolaPrograma();
		}

	}

	public String editar() {
		try {
			service.editarEscolaPrograma(escola, programasSelecionados);
			FacesUtil.msgInfo("Escola editado com sucesso.");
			escola = null;
			return "/private/escolas/lista-escola.xhtml?faces-redirect=true";

		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao editar o Programa. ERRO:" + e.getMessage());
		}

		return null;
	}

	public void listarTodos() {
		escolas = service.listarTodos();
	}

	public void listarProgramasCadastrados() {
		programasDisponiveis = programaService.listarTodos();
	}

	private void buscarEscolaPrograma() {
		programasSelecionados = escolaProgramaService.listarProgramas(escola);

		for (int i = 0; i < programasSelecionados.size(); i++) {
			for (int j = 0; j < programasDisponiveis.size(); j++) {
				if (programasSelecionados.get(i).getPrograma().getId() == programasDisponiveis.get(j).getId()) {
					programasDisponiveis.remove(j);
				}
			}

		}
	}

	public void onProgramaDrop(DragDropEvent ddEvent) {
		Programa prog = ((Programa) ddEvent.getData());
		escolaPrograma = new EscolaPrograma();

		programasDisponiveis.remove(prog);
		escolaPrograma.setPrograma(prog);
		programasSelecionados.add(escolaPrograma);
	}

	public Escola getEscola() {
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
