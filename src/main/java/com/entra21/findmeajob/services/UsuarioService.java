package com.entra21.findmeajob.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entra21.findmeajob.models.Usuario;
import com.entra21.findmeajob.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository ur;

	public void cadastrarUsuario(Usuario usuario) {
		LocalDate localDate = LocalDate.now();
		java.util.Date dataCadastro = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date dataSql = new java.sql.Date(dataCadastro.getTime());
		usuario.setDataCadastro(dataSql);
		ur.save(usuario);
	}
	
	public List<Usuario> listaUsuarios(){
		return ur.findAll();
	}

}
