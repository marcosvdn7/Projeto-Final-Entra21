package com.entra21.findmeajob.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PUBLICACAO")
	private Long idPublicacao;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "DATA_PUBLICACAO")
	private Date dataPublicacao;
	
	@Column(name = "CONTEUDO")
	private String conteudo;
	
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

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public String toString() {
		return "Publicacao [idPublicacao=" + idPublicacao + ", titulo=" + titulo + ", dataPublicacao=" + dataPublicacao
				+ ", conteudo=" + conteudo + "]";
	}
	
		
}
