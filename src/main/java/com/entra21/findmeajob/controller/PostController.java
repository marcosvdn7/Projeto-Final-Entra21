package com.entra21.findmeajob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entra21.findmeajob.models.Post;
import com.entra21.findmeajob.services.PostService;

@Controller
public class PostController {
	
	@Autowired
	public PostService ps;
	
	//REDIRECIONA PARA A PAGINA DE PUBLICAÇÂO DE POST
	@GetMapping(value = "/{idUsuario}/publicarPost")
	public String publicarPost(@PathVariable Integer idUsuario) {
		return "post/publicarPost";
	}
	
	//FAZ A PUBLICAÇÂO
	@PostMapping(value = "/{idUsuario}/publicarPost")
	public String criarPublicacao(Post post, @PathVariable Integer idUsuario, Long idCategoria) {
		ps.publicar(post, idUsuario, idCategoria);
		
		return "redirect:/" +idUsuario+ "/publicarPost";
	}
	
	//MOSTRA UMA LISTA COM TODAS AS PUBLICAÇÕES CADASTRADAS
	//NO BANCO DE DADOS
	@GetMapping(value = "/listaPosts")
	public ResponseEntity<List<Post>> listaPost() {
		List<Post> posts = ps.listar();
		
		return ResponseEntity.ok().body(posts);		
	}
	
	//EXCLUI UMA PUBLICAÇÃO
	@PostMapping(value = "/deletarPost")
	public String deletarPost(Long id) {
		ps.deletar(id);
		
		return "redirect:/listaPosts";
	}
	
	//MOSTRA UMA PUBLICAÇÃO
	@GetMapping(value = "/publicacoes/{id}")
	public ResponseEntity<Post> mostrarPost(@PathVariable Long id){
		Post post = ps.findById(id);
		
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(value = "/editarPublicacao/{idUser}/{idPost}")
	public ModelAndView editarPublicacao(@PathVariable Integer idUser, @PathVariable Long idPost, Post post) {
		ModelAndView mv = new ModelAndView("post/editarPublicacao");
		post = ps.findById(idPost);
		mv.addObject("post", post);
		
		return mv;
		}
	
	@PostMapping(value = "/editarPublicacao/{idUser}/{idPost}")
	public String editarPublicacaoPost(@PathVariable Integer idUser, @PathVariable Long idPost, Post post) {
		ps.editar(idPost, idUser, post);
		return "redirect:/listaPosts";
	}
}