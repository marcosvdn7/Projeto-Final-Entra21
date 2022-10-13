package com.entra21.findmeajob.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entra21.findmeajob.models.Categoria;
import com.entra21.findmeajob.models.Post;
import com.entra21.findmeajob.repository.CategoriaRepository;
import com.entra21.findmeajob.services.PostService;

@Controller
@RequestMapping("/publicacoes")
public class PostController {
	
	@Autowired
	private PostService ps;
	
	@Autowired
	private CategoriaRepository cr;
	
	//REDIRECIONA PARA A PAGINA DE PUBLICAÇÂO DE POST
	@GetMapping(value = "/{idUsuario}/publicarPost")
	public ModelAndView publicarPost(@PathVariable Integer idUsuario) {
		ModelAndView mv = new ModelAndView("post/publicarPost");
		ArrayList<Categoria> categorias = (ArrayList<Categoria>)cr.findAll();
		mv.addObject("categorias", categorias);
		return mv;
	}
	
	//FAZ A PUBLICAÇÂO
	@PostMapping(value = "/{idUsuario}/publicarPost")
	public String criarPublicacao(Post post, @PathVariable Integer idUsuario,
								  @RequestParam("idCategorias") ArrayList<Long> idCategorias,
								  RedirectAttributes attributes) {
		ps.publicar(post, idUsuario, idCategorias);
		
		return "redirect:/" +idUsuario+ "/publicarPost";
	}
	
	//MOSTRA UMA LISTA COM TODAS AS PUBLICAÇÕES CADASTRADAS
	//NO BANCO DE DADOS
	@GetMapping(value = "/listaPosts")
	public ResponseEntity<ArrayList<Post>> listaPost() {
		ArrayList<Post> posts = ps.listar();
		
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
		List<Categoria> categorias = cr.findAll();
		ModelAndView mv = new ModelAndView("post/editarPublicacao");
		post = ps.findById(idPost);
		mv.addObject("categorias", categorias);
		mv.addObject("post", post);
		
		return mv;
		}
	
	@PostMapping(value = "/editarPublicacao/{idUser}/{idPost}")
	public String editarPost(@PathVariable Integer idUser, @PathVariable Long idPost, Post post, 
							 @RequestParam("idCategorias") ArrayList<Long> idCategorias) {
		ps.editar(idPost, idUser, post, idCategorias);
		
		return "redirect:/listaPosts";
	}
	
	@GetMapping(value = "/posts/{idCategoria}")
	public ResponseEntity<List<Post>> listarPorCategoria(@PathVariable Long idCategoria) {
		List<Post> posts = ps.listarPorCategoria(idCategoria);
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/perfil/{idUsuario}")
	public ResponseEntity<List<Post>> postsUsuario(@PathVariable Integer idUsuario){
		List<Post> posts = ps.listarPorUsuario(idUsuario);
		
		return ResponseEntity.ok().body(posts);
	}
	
}