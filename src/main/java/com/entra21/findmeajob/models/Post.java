package com.entra21.findmeajob.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PUBLICACAO")
	private Long idPublicacao;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "DATA_PUBLICACAO")
	private Instant dataPublicacao;
	
	@Column(name = "CONTEUDO")
	private String conteudo;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	public Post() {
		
	}

	public Long getIdPublicacao() {
		return idPublicacao;
	}

	public void setIdPublicacao(Long idPublicacao) {
		this.idPublicacao = idPublicacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Instant getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Instant dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Publicacao [idPublicacao=" + idPublicacao + ", titulo=" + titulo + ", dataPublicacao=" + dataPublicacao
				+ ", conteudo=" + conteudo + "]";
	}
	
		
}
