package com.secti.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.secti.helpers.Roles;
import com.secti.model.Usuario;
import com.secti.service.UsuarioService;
import com.secti.util.FacesUtil;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private @Inject UsuarioService service;
	
	private @Inject Usuario usuario;
	
	private List<Roles> roles;
	
	private List<Usuario> usuarios;
	
	
	@PostConstruct
	private void init() {
		roles = Arrays.asList(Roles.values());
		listarTodos();
		prepararEditar();
	}
	
	public String salvar() {
		try {
			service.salvar(usuario);
			FacesUtil.msgInfo("Usuário adicionado com sucesso");
			usuario = null;
			return "/private/usuarios/lista-usuario.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao adicionar o usuário. " + e.getMessage());
		}
		
		return null;
	}
	
	public void listarTodos(){
		usuarios = service.listarTodos();
	}
	
	public void prepararEditar() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		
		if (id != null) {
			usuario = service.buscarPorId(usuario, Long.parseLong(id));
		}
	}
	
	public String editar() {
		try {
			service.editar(usuario);
			FacesUtil.msgInfo("Usuario editado com sucesso");
			usuario = null;
			return "/private/usuarios/lista-usuario.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesUtil.msgErro("Erro ao editar o usuário. ERRO: " + e.getMessage());
		}
		
		return null;
	}
	
	
	public UsuarioService getService() {
		return service;
	}

	public void setService(UsuarioService service) {
		this.service = service;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
