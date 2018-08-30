package com.secti.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secti.util.FacesUtil;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private FacesContext context;
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private HttpServletResponse response;
	
	private static final String URL_LOGIN = "/public/login.xhtml";

	public void acessar() throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(URL_LOGIN);
		dispatcher.forward(request, response);
		
		context.responseComplete();
	}
	
	public void preRender() {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		if ("true".equals(request.getParameter("invalid"))) {
			FacesUtil.msgErro("Login incorreto");
		}

	}

}
