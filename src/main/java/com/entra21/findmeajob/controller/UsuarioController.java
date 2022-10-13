package com.entra21.findmeajob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entra21.findmeajob.models.Usuario;
import com.entra21.findmeajob.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService us;

	@GetMapping("/cadastrarUsuario")
	public String cadastrarUsuario() {
		return "usuario/home";
	}

	@PostMapping("/cadastrarUsuario")
	public String cadastrarUsuario(Usuario usuario, Model model) {
		if (us.findByEmail(usuario.getEmail()) != null) {
			model.addAttribute("emailCadastrado", "O email informado ja está cadastrado!");
			return "usuario/home";
		}
		
		us.cadastrar(usuario);
		
		return "redirect:/usuario/editarPerfil";
	}
	
	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}

	@GetMapping(value = "/listaUsuarios")
	public ResponseEntity<List<Usuario>> listaUsuarios() {
		List<Usuario> usuarios = us.listaUsuarios();
		return ResponseEntity.ok().body(usuarios);
	}

	@RequestMapping(value = "/deletarUsuario")
	public String deletarUsuario(Integer id) {
		us.deletar(id);

		return "redirect:/usuario/home";
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
	
	@GetMapping("/index")
	public String index(@CurrentSecurityContext(expression = "authentication.name") String email) {
		Usuario usuario = us.encontrarPorEmail(email);
		
		String redirectURL = "";
		
		if (us.temAutorizacao(usuario, "USUARIO")){
			redirectURL = "usuario/home";
		}
		if (us.temAutorizacao(usuario, "ADMIN")) {
			redirectURL = "admin/listaUsuariosAdmin";
		}
		return redirectURL;
		
	}
	
}
