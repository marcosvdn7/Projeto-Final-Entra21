package com.entra21.findmeajob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.entra21.findmeajob.models.Post;
import com.entra21.findmeajob.services.PostService;

@Controller
public class PostController {
	
	@Autowired
	public PostService ps;
	
	@GetMapping(value = "/publicarPost")
	public String publicarPost() {
		return "post/publicarPost";
	}
	
	@PostMapping(value = "/publicarPost")
	public String criarPublicacao(Post post) {
		ps.criarPublicacao(post);
		
		return "redirect:/publicarPost";
	}
	
	@GetMapping(value = "/listaPosts")
	public ResponseEntity<List<Post>> listaPost() {
		List<Post> posts = ps.listaPosts();
		
		return ResponseEntity.ok().body(posts);		
	}
	
//	

}
