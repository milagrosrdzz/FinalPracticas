package com.AppConvert.backAppConvert.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppConvert.backAppConvert.interfaces.UsuarioInterface;
import com.AppConvert.backAppConvert.modelo.Usuario;
import com.AppConvert.backAppConvert.modeloDAO.ModeloDAO;


@Service
public class UsuarioService implements UsuarioInterface {
	@Autowired
	ModeloDAO dao;
	
	@Override
	public List<Map<String, Object>> listar() {		
		return dao.listar();
	}

	@Override
	public List<Map<String, Object>> listarId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Usuario u) {		
		return dao.add(u);
	}

	@Override
	public int edit(Usuario u) {
		// TODO Auto-generated method stub
		return dao.edit(u);
	}

	@Override
	public int delete(int id_usuario) {
		// TODO Auto-generated method stub
		return dao.delete(id_usuario);
	}

}
