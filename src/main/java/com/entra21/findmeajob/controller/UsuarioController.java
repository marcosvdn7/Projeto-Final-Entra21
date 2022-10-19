package com.entra21.findmeajob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entra21.findmeajob.models.Usuario;
import com.entra21.findmeajob.security.DetalheUsuario;
import com.entra21.findmeajob.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	
	private Usuario usuario = new Usuario();

	@Autowired
	private UsuarioService us;

	@GetMapping("/signUp")
	public String signUp() {
		return "usuario/cadastro";
	}

	@PostMapping("/signUp")
	public String signUp(Usuario usuario, Model model) {
		if (us.findByEmail(usuario.getEmail()) != null) {
			model.addAttribute("emailCadastrado", "O email informado ja está cadastrado!");
			return "redirect:/usuario/cadastro";
		}

		us.cadastrar(usuario);

		return "redirect:/home";
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
	public ModelAndView perfilUsuario(@AuthenticationPrincipal DetalheUsuario detalheUsuario,
			@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("usuario/perfilUsuario");
		Usuario usuario = us.findById(id);
		mv.addObject("usuario", usuario);
		mv.addObject("usuarioLogado", this.usuario);
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return mv;
	}

	@GetMapping(value = "/editarPerfil/{id}")
	public ModelAndView editarPerfil(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("usuario/editarPerfil");
		mv.addObject("usuario", us.findById(id));
		mv.addObject("usuarioLogado", this.usuario);

		return mv;
	}

	@PostMapping(value = "/editarPerfil/{id}")
	public String editarUsuario(@PathVariable Integer id, Usuario usuario) {
		us.editar(id, usuario);
		return "redirect:/listaUsuarios";
	}

	@GetMapping("/index")
	public String index(@CurrentSecurityContext(expression = "authentication.name") String email) {

		// Em seguida, iniciamos um usuario para buscar o email do mesmo no banco
		this.usuario = us.findByEmail(email);

		// String vazia para inserir a url que ele será levado, baseado no seu papel
		String redirectURL = "";
		if (us.temAutorizacao(this.usuario, "USUARIO")) {
			redirectURL = "/home";
		} else if (us.temAutorizacao(this.usuario, "ADMIN")) {
			redirectURL = "admin/listaUsuariosAdmin";
		}
		return redirectURL;
	}

}
