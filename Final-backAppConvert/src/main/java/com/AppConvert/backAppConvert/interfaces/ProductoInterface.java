package com.AppConvert.backAppConvert.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.AppConvert.backAppConvert.modelo.Producto;

public interface ProductoInterface {

	int edit(Producto p);
	List<Map<String, Object>> listar();
	int add(Producto p);
	List<Map<String, Object>> listarId(int id_usuario);
    Optional<Producto> findById(Long id);
	long delete(long id_producto);

}
