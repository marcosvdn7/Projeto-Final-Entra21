package com.entra21.findmeajob.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entra21.findmeajob.models.Post;
import com.entra21.findmeajob.models.Usuario;
import com.entra21.findmeajob.repository.PostRepository;
import com.entra21.findmeajob.repository.UsuarioRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository pr;
	
	@Autowired
	private UsuarioRepository ur;
	
	public void publicar(Post post, Integer id) {
		post.setDataPublicacao(Instant.now());
		Optional<Usuario> user = ur.findById(id);
		post.setUsuario(user.get());
		pr.save(post);
	}
	
	public Post findById(Long postId) {
		Optional<Post> obj = pr.findById(postId);
		
		return obj.get();
	}
	
	public List<Post> listar(){
		return pr.findAll();
	}
	
	public void deletar(Long postId) {
		Optional<Post> post = pr.findById(postId);
		pr.delete(post.get());
	}
	
	public Post editar(Long id, Post postEditado) {
		Optional<Post> optPost = pr.findById(id);
		atualizarDados(optPost.get(), postEditado);
		
		return pr.save(optPost.get());
	}
	
	private void atualizarDados(Post post, Post postEditado) {
		post.setTitulo(postEditado.getTitulo());
		post.setConteudo(postEditado.getConteudo());
	}

}
