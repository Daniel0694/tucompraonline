package com.tucompraonline.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tucompraonline.data.ProductoDao;
import com.tucompraonline.domain.Producto;

@Service
public class ProductoService {

	@Autowired
	private ProductoDao productoDao;
	
	public List<Producto> getProductos() throws SQLException {
		return productoDao.getProductos();
	}
	public List<Producto> getProductosPorCategoria(int idCategoria) throws SQLException {
		return productoDao.getProductosPorCategoria(idCategoria);
	}
	public List<Producto> getProductosPorRangoDePrecio(float minimo, float maximo) throws SQLException {
		return productoDao.getProductosPorRangoDePrecio(minimo, maximo);
	}
	public List<Producto> getProductosPorInicial(char inicial) throws SQLException {
		return productoDao.getProductosPorInicial(inicial);
	}
	public List<Producto> getProductosPorDisponibilidad() throws SQLException {
		return productoDao.getProductosPorDisponibilidad();
	}
}
