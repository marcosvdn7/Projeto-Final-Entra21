package com.entra21.findmeajob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.entra21.findmeajob.models.Usuario;
import com.entra21.findmeajob.services.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService us;
	
	@GetMapping(value = "/cadastrarUsuario")
	public String cadastrarUsuario() {
		return "usuario/cadastrarUsuario";
	}
	
	@PostMapping(value = "/cadastrarUsuario")
	public String cadastrarUsuario(Usuario usuario) {
		us.cadastrarUsuario(usuario);
		
		return "redirect:/cadastrarUsuario";
	}
	
	
	
	@GetMapping(value = "/listaUsuarios")
	public ResponseEntity<List<Usuario>> listaUsuarios() {
		List<Usuario> usuarios = us.listaUsuarios();
		return ResponseEntity.ok().body(usuarios);
	}
	
}
