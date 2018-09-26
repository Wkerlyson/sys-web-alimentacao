package com.secti.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.secti.model.Programa;
import com.secti.service.ProgramaService;
import com.secti.util.FacesUtil;
import com.secti.util.report.GeraRelatorio;

import net.sf.jasperreports.engine.JRException;


@Named
@ViewScoped
public class ProgramaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProgramaService service;
	
	@Inject
	private Programa programa;
	
	@Inject
	private GeraRelatorio relatorio;
	
	private List<Programa> programas;
	
	private List<Programa> programasFiltrados;
	
	@PostConstruct
	private void init() {
		prepararEditar();
		
	}
	
	
	public String salvar() {
		
		try {
			
			service.salvar(programa);
			FacesUtil.msgInfo("Programa salvo com sucesso");
			programa = null;
			return "/private/programas/lista-programa.xhtml?faces-redirect=true";
			
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao salvar o Programa. ERRO: " + e);
		}
		
		return null;
	}
	
	
	public void  listarTodos(){
		programas = service.listarTodos();
	}
	
	public void remover() {
		try {
			service.remover(programa, programa.getId());
			FacesUtil.msgInfo("Programa removido com sucesso.");
			programa = null;
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao remover o Programa. ERRO: \" + e.getMessage()");
		}
	}
	
	public void prepararEditar() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (id != null) {
			programa = service.buscarPorId(programa, Long.parseLong(id));
		}
			
	}
	
	public String editar() {
		try {
			service.editar(programa);
			FacesUtil.msgInfo("Programa editado com sucesso.");
			programa = null;
			return "/private/programas/lista-programa.xhtml?faces-redirect=true";
			
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao editar o Programa. ERRO: \" + e.getMessage()");
		}
		
		return null;
	}
	
	public void imprimirProgramas() {
		List<Programa> programas = new ArrayList<Programa>();
		
		programas = service.listarTodos();
		
		try {
			relatorio.gerarRelatorio(null, programas, "relatorio-programa", "Programas");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Programa getPrograma() {
		if (programa == null) {
			programa = new Programa();
		}
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public ProgramaService getService() {
		return service;
	}

	public void setService(ProgramaService service) {
		this.service = service;
	}
	
	public List<Programa> getProgramas() {
		return programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}

	public List<Programa> getProgramasFiltrados() {
		return programasFiltrados;
	}

	public void setProgramasFiltrados(List<Programa> programasFiltrados) {
		this.programasFiltrados = programasFiltrados;
	}
	
}
