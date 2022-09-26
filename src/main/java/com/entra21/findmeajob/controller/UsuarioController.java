package com.entra21.findmeajob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entra21.findmeajob.models.Usuario;
import com.entra21.findmeajob.services.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService us;

	// Redireciona para a pagina de cadastro
	@GetMapping(value = "/cadastrarUsuario")
	public String cadastrarUsuario() {
		return "usuario/cadastrarUsuario";
	}

	// Cadastra o usuario no banco de dados
	@PostMapping(value = "/cadastrarUsuario")
	public String cadastrarUsuario(Usuario usuario) {
		us.cadastrar(usuario);
		return "redirect:/cadastrarUsuario";
	}

	// Mostra uma lista com todos os usuarios cadastrados
	@GetMapping(value = "/listaUsuarios")
	public ResponseEntity<List<Usuario>> listaUsuarios() {
		List<Usuario> usuarios = us.listaUsuarios();
		return ResponseEntity.ok().body(usuarios);
	}

	@RequestMapping(value = "/deletarUsuario")
	public String deletarUsuario(Integer id) {
		us.deletar(id);

		return "redirect:/cadastrarUsuario";
	}
	
	@GetMapping(value = "/perfilUsuario/{id}")
	public ResponseEntity<Usuario> perfilUsuario(@PathVariable Integer id){
		Usuario usuario = us.findById(id);
		
		return ResponseEntity.ok().body(usuario);
	}
	
	@GetMapping(value = "/editarPerfil/{id}")
	public ModelAndView editarPerfil(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("usuario/editarPerfil");
		mv.addObject("usuario", us.findById(id));
		
		return mv;
	}
	
	@PostMapping(value = "/editarPerfil/{id}")
	public String editarUsuario(@PathVariable Integer id, Usuario usuario){
		us.editar(id, usuario);
		return "redirect:/listaUsuarios";
	}
	

}
