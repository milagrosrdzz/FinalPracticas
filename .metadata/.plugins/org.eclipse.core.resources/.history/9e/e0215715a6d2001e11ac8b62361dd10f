package com.AppConvert.backAppConvert.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AppConvert.backAppConvert.modelo.Usuario;
import com.AppConvert.backAppConvert.service.UsuarioService;





@RestController
@RequestMapping(path ="/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService service;
	
	@GetMapping("/listar")
	public List<Map<String, Object>> listar() {
		return service.listar();
	}
	
		
	@PostMapping("/agregar")
	public String save(@RequestBody Usuario u,Model model) {
		int id_usuario=service.add(u);
		if(id_usuario==0) {
			return "No se pudo Regsitrar!";
		}
		return "Se registró con éxito!";
	}
    
	
	@PostMapping("/actualizar/{id}")
	public String save(@RequestBody Usuario u,@PathVariable int id_usuario,Model model) {
		u.setId_usuario(id_usuario);
		int r=service.edit(u);
		if(r==0) {
			return "No se pudo actualizar el id!";
		}
		return "Se actualizó con éxito!";
	}
	@PostMapping("/eliminar/{id_usuario}")
	public String delete(@PathVariable int id_usuario,Model model) {
		int r=service.delete(id_usuario);
		if(r==0) {
			return "Registro No Eliminado!";
		}
		return "Registro Eliminado!";
	}

}
