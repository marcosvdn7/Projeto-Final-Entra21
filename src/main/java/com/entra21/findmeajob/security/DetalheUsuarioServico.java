package com.entra21.findmeajob.security;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entra21.findmeajob.models.Usuario;
import com.entra21.findmeajob.repository.UsuarioRepository;

@Service
@Transactional
public class DetalheUsuarioServico implements UserDetailsService {
	
	private UsuarioRepository usuarioRepository;
	
	public DetalheUsuarioServico(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		Usuario usuario = usuarioRepository.findByEmail(null);
		Set<GrantedAuthority> permissoes = new HashSet<>();
		if (usuario != null) {
			GrantedAuthority permissao = new SimpleGrantedAuthority(usuario.getPermissaoUsuario());
			permissoes.add(permissao);
			User user = new User(usuario.getEmail(), usuario.getSenha(), permissoes);
			
			return user;
		} else {
			throw new UsernameNotFoundException("Email nao cadastrado");
		}
		
	}

	

}
