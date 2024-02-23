package com.AppConvert.backAppConvert.interfaces;

import java.util.List;
import java.util.Map;

import com.AppConvert.backAppConvert.modelo.Usuario;



public interface UsuarioInterface {
	
	public List<Map<String, Object>>listar();
	public List<Map<String, Object>>listarId(int id_usuario);
	public int add(Usuario u);
	public int edit(Usuario u);
	public int delete(int id_usuario);

}
