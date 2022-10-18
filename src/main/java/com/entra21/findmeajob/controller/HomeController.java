package com.entra21.findmeajob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entra21.findmeajob.models.Post;
import com.entra21.findmeajob.services.PostService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PostService postService;
	
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
		List<Post> publicacoesRecentes = postService.publicacoesRecentes();
		mv.addObject("publicacoesRecentes", publicacoesRecentes);			
		return mv;
	}
}
