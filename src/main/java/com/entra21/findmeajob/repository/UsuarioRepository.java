package com.entra21.findmeajob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entra21.findmeajob.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	
}
