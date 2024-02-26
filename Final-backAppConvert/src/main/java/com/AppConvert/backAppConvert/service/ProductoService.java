package com.AppConvert.backAppConvert.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AppConvert.backAppConvert.interfaces.ProductoInterface;
import com.AppConvert.backAppConvert.modelo.Producto;
import com.AppConvert.backAppConvert.modeloDAO.ProductoDAO;

@Service
public class ProductoService implements ProductoInterface{
	
	 @Autowired
	 private ProductoDAO productoDao;
	    

	 @Transactional
	 public Producto sumarCantidadYActualizarTotal(Long idProducto, int cantidad) {
	     System.out.println("Entrando en sumarCantidadYActualizarTotal");

	     Producto producto = productoDao.obtenerPorId(idProducto);
	     
	     System.out.println("Producto antes de la actualización: " + producto.getCantidad_total() + ", cantidad a agregar: " + cantidad);

	     producto.setCantidad_total(producto.getCantidad_total() + cantidad);
	     System.out.println("Cantidad total después de la suma: " + producto.getCantidad_total());

	     // Guarda el producto actualizado
	     Producto productoActualizado = productoDao.save(producto);

	     System.out.println("Saliendo de sumarCantidadYActualizarTotal");
	     return productoActualizado;
	 }
	 @Transactional
	 public Producto restarCantidadYActualizarTotal(Long idProducto, int cantidad) {
	     System.out.println("Entrando en restarCantidadYActualizarTotal");

	     Producto producto = productoDao.obtenerPorId(idProducto);
	     
	     System.out.println("Producto antes de la actualización: " + producto.getCantidad_total() + ", cantidad a agregar: " + cantidad);

	     producto.setCantidad_total(producto.getCantidad_total() - cantidad);
	     System.out.println("Cantidad total después de la resta: " + producto.getCantidad_total());

	     // Guarda el producto actualizado
	     Producto productoActualizado = productoDao.save(producto);

	     System.out.println("Saliendo de restarCantidadYActualizarTotal");
	     return productoActualizado;
	 }


	public List<Map<String, Object>> listar() {
		return productoDao.listar();
	}

	public int add(Producto p) {
		return productoDao.add(p);
	}

	public int edit(Producto p) {
		return productoDao.edit(p);
	}

	public long delete(long id_producto) {
		return productoDao.delete(id_producto);
	}

	@Override
	public List<Map<String, Object>> listarId(int id_usuario) {
		// TODO Auto-generated method stub
		return productoDao.listarId(id_usuario);
	}

	@Override
	public Optional<Producto> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	public Producto obtenerPorId(Long id_usuario) {
		return productoDao.obtenerPorId(id_usuario);
	}

	
	public void actualizarCantidadTotal(long id_producto, int cantidadAgregada) {
	    // Recuperar el producto por su ID
	    Optional<Producto> optionalProducto = productoDao.findById(id_producto);
	    
	    System.out.println("Actualizando cantidad total para ID: " + id_producto);
        System.out.println("Cantidad a agregar: " + cantidadAgregada);

	    if (optionalProducto.isPresent()) {
	        Producto producto = optionalProducto.get();

	        // Obtener la cantidad total actual
	        int cantidadTotalActual = producto.getCantidad_total();

	        // Calcular la nueva cantidad total sumándole la cantidad agregada a la actual
	        int nuevaCantidadTotal = cantidadTotalActual + cantidadAgregada;

	        // Establecer la nueva cantidad total en el producto
	        producto.setCantidad_total(nuevaCantidadTotal);

	        // Guardar los cambios en el repositorio
	        productoDao.save(producto);
	    }
	}
	

	

}
