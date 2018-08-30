package com.secti.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.secti.cdi.CDIServiceLocator;
import com.secti.dao.UsuarioDAO;
import com.secti.model.Usuario;

public class UsuarioDetailsService implements UserDetailsService{

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = CDIServiceLocator.getBean(UsuarioDAO.class).buscarPorNomeUsuario(username);
		
		if (usuario != null) {
			return new UsuarioSistema(usuario, obtemGruposdoUsuario(usuario));			
		}
		
		throw new UsernameNotFoundException("Usuário não encontrado");
	}
	
	private Collection<? extends GrantedAuthority> obtemGruposdoUsuario(Usuario usuario) {
		List<GrantedAuthority> papeis = new ArrayList<GrantedAuthority>();
		
		papeis.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRoles()));

		return papeis;
	}

}
