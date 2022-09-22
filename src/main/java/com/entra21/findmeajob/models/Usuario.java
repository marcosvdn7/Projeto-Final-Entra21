package com.entra21.findmeajob.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "NOME_COMPLETO")
	private String nomeCompleto;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "DATA_NASCIMENTO")
	private Date dataNascimento;
	
	@Column(name = "SENHA")
	private String senha;
	
	@Column(name = "DATA_CADASTRO")
	private Date dataCadastro;
	
	public Usuario() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Usuario [userId=" + userId + ", nomeCompleto=" + nomeCompleto + ", email=" + email + ", dataNascimento="
				+ dataNascimento + ", senha=" + senha + ", dataCadastro=" + dataCadastro + "]";
	}

	
}
