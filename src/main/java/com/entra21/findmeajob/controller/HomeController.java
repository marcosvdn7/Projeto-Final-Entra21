package com.entra21.findmeajob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entra21.findmeajob.models.Post;
import com.entra21.findmeajob.models.Usuario;
import com.entra21.findmeajob.security.DetalheUsuario;
import com.entra21.findmeajob.services.PostService;
import com.entra21.findmeajob.services.UsuarioService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UsuarioService usuarioService;
	
//	@GetMapping(value = "/publicacoesRecentes")
//	public ModelAndView listarRecentes(){
//		ModelAndView mv = new ModelAndView("usuario/home");
//		List<Post> publicacoesRecentes = postService.publicacoesRecentes();
//		mv.addObject("publicacoesRecentes", publicacoesRecentes);			
//		return mv;
//	}
//	
//	@GetMapping(value = "/{idEndereco}")
//	public ResponseEntity<List<Post>> listarPorCidade(@PathVariable Long idEndereco){
//		List<Post> posts = postService.buscarPorEndereco(idEndereco);
//		return ResponseEntity.ok().body(posts);
//	}

	@GetMapping
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("usuario/home");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Post> publicacoesRecentes = postService.publicacoesRecentes();
		
		
		if (principal instanceof DetalheUsuario) {
			DetalheUsuario detalheUsuario = (DetalheUsuario) principal;
			Usuario usuario = usuarioService.findById(detalheUsuario.getIdUsuarioLogado());
			List<Post> publicacoesProximas = postService.buscarPorEndereco(usuario.getEndereco().getId());
			System.out.println(usuario.getFotoPerfil());
			mv.addObject("usuario", usuario);
			mv.addObject("publicacoesProximas", publicacoesProximas);
		}
		
		mv.addObject("publicacoesRecentes", publicacoesRecentes);			
		return mv;
	}
}
