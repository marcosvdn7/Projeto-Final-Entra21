package com.entra21.findmeajob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.entra21.findmeajob.models.Categoria;
import com.entra21.findmeajob.services.CategoriaService;

@Controller
public class CategoriaController {
	
	@Autowired
	private CategoriaService cs;
	
	@GetMapping(value = "/categorias/{idCategoria}")
	public ResponseEntity<Categoria> findbyId(@PathVariable Long idCategoria) {
		Categoria categoria = cs.findById(idCategoria);
		
		return ResponseEntity.ok().body(categoria);
	}
	
	@GetMapping(value = "/categorias")
	public ResponseEntity<List<Categoria>> listarCategorias(){
		List<Categoria> categorias = cs.listar();
		
		return ResponseEntity.ok().body(categorias);
	}
	
	

}
