package com.entra21.findmeajob.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

	public void publicar(Post post, Integer idUsuario, ArrayList<Long> idCategorias) {
		Optional<Usuario> usuario = ur.findById(idUsuario);
		adicionarCategoria(post, idCategorias);
		post.setDataPublicacao(Instant.now());
		post.setUsuario(usuario.get());

		pr.save(post);
	}

	public Post findById(Long postId) {
		Optional<Post> obj = pr.findById(postId);

		return obj.get();
	}

	public ArrayList<Post> listar() {
		return (ArrayList<Post>) pr.findAll();
	}

	public ArrayList<Post> listarPorCategoria(Long idCategoria) {
		ArrayList<Post> todasPublicacoes = (ArrayList<Post>) pr.findAll();
		ArrayList<Post> publicacoesCategoria = new ArrayList<>();
		Optional<Categoria> categoria = cr.findById(idCategoria);
		for (Post post : todasPublicacoes) {
			if (post.getCategorias().contains(categoria.get())) {
				publicacoesCategoria.add(post);
			}
		}
		if (publicacoesCategoria.isEmpty()) {
			throw new NoSuchElementException();
		}
		return publicacoesCategoria;
	}
	
	public void deletar(Long postId) {
		Optional<Post> post = pr.findById(postId);
		pr.delete(post.get());
	}

	public Post editar(Long idPost, Integer idUsuario, Post postEditado, ArrayList<Long> idCategorias) {
		Optional<Post> optPost = pr.findById(idPost);
		atualizarDados(optPost.get(), idUsuario, postEditado);
		editarCategoria(optPost.get(), idCategorias);
		return pr.save(optPost.get());
	}

	private void atualizarDados(Post post, Integer idUsuario, Post postEditado) {
		// BUSCA AS CATEGORIAS E O USUARIO QUE FEZ A PUBLICACAO
		Optional<Usuario> optUsuario = ur.findById(idUsuario);

		// ATUALIZA OS DADOS EDITADOS
		post.setTitulo(postEditado.getTitulo());
		post.setConteudo(postEditado.getConteudo());
		post.setUsuario(optUsuario.get());
	}

	private Post editarCategoria(Post post, ArrayList<Long> idCategorias) {
		Integer count = 0;
		List<Categoria> categorias = new ArrayList<>();
		for (int i = 0; i < idCategorias.size(); i++) {
			Optional<Categoria> optCategoria = cr.findById(idCategorias.get(i));
			categorias.add(optCategoria.get());
		}
		for (int i = 0; i < post.getCategorias().size(); i++) {
			if (post.getCategorias().contains(categorias.get(i))) {
				count++;
			}
			if (count == 2) {
				return post;
			} else {
				post.getCategorias().clear();
			}
		}
		for (Categoria categoria : categorias) {
			post.getCategorias().add(categoria);
		}
		return post;
	}

	private Post adicionarCategoria(Post post, ArrayList<Long> idCategorias) {
		for (Long id : idCategorias) {
			Optional<Categoria> objCategoria = cr.findById(id);
			post.getCategorias().add(objCategoria.get());
		}
		return post;
	}
	
	public List<Post> listarPorUsuario(Integer idUsuario){
		Optional<Usuario> optUsuario = ur.findById(idUsuario);
		List<Post> posts = pr.findByUsuario(optUsuario.get());
		
		return posts;		
	}

}
