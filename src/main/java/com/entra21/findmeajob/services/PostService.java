package com.entra21.findmeajob.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entra21.findmeajob.models.Post;
import com.entra21.findmeajob.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository pr;
	
	public void criarPublicacao(Post post) {
		LocalDate localDate = LocalDate.now();
		java.util.Date dataCadastro = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date dataSql = new java.sql.Date(dataCadastro.getTime());
		post.setDataPublicacao(dataSql);
		pr.save(post);
	}
	
	public List<Post> listaPosts(){
		return pr.findAll();
	}

}
