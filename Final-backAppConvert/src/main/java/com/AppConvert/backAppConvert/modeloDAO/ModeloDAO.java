package com.AppConvert.backAppConvert.modeloDAO;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.AppConvert.backAppConvert.interfaces.UsuarioInterface;
import com.AppConvert.backAppConvert.modelo.Usuario;



@Repository
@Transactional
public class ModeloDAO implements UsuarioInterface{
	
	@Autowired
	JdbcTemplate template;

	@Override
	public List<Map<String, Object>> listar() {
		List<Map<String, Object>> list = template.queryForList("select * from usuario");
		return list;
	}

	@Override
	public List<Map<String, Object>> listarId(int id_usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Usuario u) {
		String sql = "insert into usuario(name,email,password)values(?,?,?)";
		return template.update(sql, u.getName(), u.getEmail(),u.getPassword());
	}

	@Override
	public int edit(Usuario u) {
		String sql="update usuario set name=?, email=? where id_usuario=?";		
		return template.update(sql,u.getName(),u.getEmail(),u.getId_usuario());
	}

	@Override
	public int delete(int id_usuario) {
		String sql="delete from usuario where id_usuario=?";
		return template.update(sql,id_usuario);
	}


}
