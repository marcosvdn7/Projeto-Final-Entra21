package com.entra21.findmeajob.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entra21.findmeajob.models.Categoria;
import com.entra21.findmeajob.models.Post;
import com.entra21.findmeajob.models.Usuario;
import com.entra21.findmeajob.repository.CategoriaRepository;
import com.entra21.findmeajob.repository.PostRepository;
import com.entra21.findmeajob.repository.UsuarioRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository pr;
	
	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private CategoriaRepository cr;
	
	public void publicar(Post post, Integer idPublicacao, Long idCategoria) {
		Optional<Categoria> obj = cr.findById(idCategoria);
		Optional<Usuario> user = ur.findById(idPublicacao);
		post.setDataPublicacao(Instant.now());
		post.setUsuario(user.get());
		post.getCategorias().add(obj.get());
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
	
	public Post editar(Long idPost, Integer idUsuario, Post postEditado) {
		Optional<Post> optPost = pr.findById(idPost);
		atualizarDados(optPost.get(), idUsuario, postEditado);
		
		return pr.save(optPost.get());
	}
	
	private void atualizarDados(Post post, Integer idUsuario, Post postEditado) {
		post.setTitulo(postEditado.getTitulo());
		post.setConteudo(postEditado.getConteudo());
		Optional<Usuario> optUsuario = ur.findById(idUsuario);
		optUsuario.get().getPosts().add(postEditado);
		post.setUsuario(optUsuario.get());
		
	}

}
