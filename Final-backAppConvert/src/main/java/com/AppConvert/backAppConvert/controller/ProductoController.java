package com.AppConvert.backAppConvert.controller;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.AppConvert.backAppConvert.modelo.Producto;
import com.AppConvert.backAppConvert.service.ProductoService;

@RestController
@RequestMapping(path ="usuarios/productos/")
public class ProductoController {
	
	@Autowired
	ProductoService productoService;
    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

	
	@GetMapping("/listar")
	public List<Map<String, Object>> listar() {
		return productoService.listar();
	}
	
	
	@Transactional
	@PostMapping("/agregarNuevo")
	public String save(@RequestBody Producto p, Model model) {
	    // Agregar el nuevo producto
	    int id_producto = productoService.add(p);

	    if (id_producto == 0) {
	        return "No se pudo agregar el producto!";
	    }
	    
	    System.out.println("ID del producto agregado: " + id_producto);
	    System.out.println("Cantidad del nuevo producto: " + p.getCantidad());
	    // Obtener la cantidad del nuevo producto
	    int cantidadAgregada = p.getCantidad();
	    // Actualizar la cantidad total del producto en el servidor
	    productoService.actualizarCantidadTotal(id_producto, cantidadAgregada);

	    return "Se registró con éxito!";
	}
	
	   
	
	@PutMapping("/actualizar/{id_producto}")
	public String actualizar(@RequestBody Producto p,@PathVariable int id_producto,Model model) {
		p.setId_producto(id_producto);
		int r=productoService.edit(p);
		if(r==0) {
			return "No se pudo actualizar el id!";
		}
		return "Se actualizó con éxito!";
	}

	@PostMapping("/{id_producto}/sumar-cantidad/")
	public ResponseEntity<?> sumarCantidad(@PathVariable Long id_producto, @RequestBody Map<String, String> requestBody) {
	    try {
	        Producto producto = productoService.obtenerPorId(id_producto);
	        if (producto == null) {
	            return new ResponseEntity<>(Map.of("error", "Producto no encontrado", "id_producto", id_producto), HttpStatus.NOT_FOUND);
	        }

	        String cantidadString = requestBody.get("cantidad");
	        if (StringUtils.isNotBlank(cantidadString) && StringUtils.isNumeric(cantidadString)) {
	        	
	        } else {
	            return new ResponseEntity<>(Map.of("error", "La cantidad debe ser un número válido."), HttpStatus.BAD_REQUEST);
	        }

	        int cantidad = Integer.parseInt(cantidadString);

	        producto = productoService.sumarCantidadYActualizarTotal(id_producto, cantidad);
	        return ResponseEntity.ok(producto);
	    } catch (EntityNotFoundException e) {
	        return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/{id_producto}/restar-cantidad")
	public ResponseEntity<?> restarCantidad(@PathVariable Long id_producto, @RequestBody Map<String, String> requestBody) {
	    try {
	        Producto producto = productoService.obtenerPorId(id_producto);
	        if (producto == null) {
	            return new ResponseEntity<>(Map.of("error", "Producto no encontrado", "id_producto", id_producto), HttpStatus.NOT_FOUND);
	        }

	        String cantidadString = requestBody.get("cantidad");
	        if (StringUtils.isNotBlank(cantidadString) && StringUtils.isNumeric(cantidadString)) {
	        	
	        } else {
	            return new ResponseEntity<>(Map.of("error", "La cantidad debe ser un número válido."), HttpStatus.BAD_REQUEST);
	        }

	        int cantidad = Integer.parseInt(cantidadString);

	        producto = productoService.restarCantidadYActualizarTotal(id_producto, cantidad);
	        return ResponseEntity.ok(producto);
	    } catch (EntityNotFoundException e) {
	        return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
	    }
	}
	
    

	
	@DeleteMapping("/eliminar/{id_producto}")
	public String delete(@PathVariable long id_producto,Model model) {
		long r=productoService.delete(id_producto);
		if(r==0) {
			return "Registro No Eliminado!";
		}
		return "Registro Eliminado!";
	}

}
