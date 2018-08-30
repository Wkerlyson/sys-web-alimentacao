package com.secti.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private static final String URL_ACESSO_NEGADO = "/public/403.xhtml";
	private static final String URL_ACESSO_ADMIN = "/private/usuarios/**";
	

	private static final String URL_LOGIN = "/public/login.xhtml";
	private static final String URL_LOGIN_ERROR = URL_LOGIN + "?invalid=true";
	private static final String LOGOUT = "/logout";
	
	private static final String[] URLS_ACESSO_PUBLICO = new String[] { "/javax.faces.resource/**", "/public/**",
			"/resources/fonts/**", "/resources/img/**" };
	
	private static UsuarioDetailsService usuarioService = new UsuarioDetailsService();
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	   auth.userDetailsService(usuarioService);
    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.exceptionHandling().accessDeniedPage(URL_ACESSO_NEGADO);
		
		http.authorizeRequests().antMatchers(URLS_ACESSO_PUBLICO).permitAll();
		
		http.authorizeRequests().antMatchers(URL_ACESSO_ADMIN).hasRole("ADMIN");
		
		//Login
		http.formLogin().loginPage(URL_LOGIN).permitAll()
				.defaultSuccessUrl("/private/dashboard.xhtml", true)
				.failureUrl(URL_LOGIN_ERROR)
				.usernameParameter("username")
				.passwordParameter("password");
		
		//Logout
		http.logout().logoutUrl(LOGOUT)
						.logoutSuccessUrl(URL_LOGIN);
		
		// Todas as requisições para partes internas da aplicação devem ser autenticadas
		http.authorizeRequests().anyRequest().authenticated();
		
	}
	
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
        web.ignoring().antMatchers("/javax.faces.resource/**");
    }
	
}
