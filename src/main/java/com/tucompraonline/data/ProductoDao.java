package com.tucompraonline.data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tucompraonline.domain.Categoria;
import com.tucompraonline.domain.Producto;

@Repository
public class ProductoDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Producto> getProductos() throws SQLException {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL obtenerProducto();";
		jdbcTemplate
				.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"), rs.getString("nombre"),
								rs.getString("descripcion"), rs.getFloat("precio"), rs.getInt("cantidad_disponible")))
				.forEach(entry -> productos.add(entry));
		// TODO recuperar imagen y categorias
		return productos;
	}
	
	public List<Producto> getProductosPorCategoria(int idCategoria) throws SQLException {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL productosPorCategoria("+idCategoria+");";
		jdbcTemplate
				.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"), rs.getString("nombre"),
								rs.getString("descripcion"), rs.getFloat("precio"), rs.getInt("cantidad_disponible")))
				.forEach(entry -> productos.add(entry));
		// TODO recuperar imagen y categorias
		return productos;
	}
	
	public List<Producto> getProductosPorRangoDePrecio(float minimo, float maximo) throws SQLException {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL productosPorPrecio("+minimo+","+maximo+");";
		jdbcTemplate
				.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"), rs.getString("nombre"),
								rs.getString("descripcion"), rs.getFloat("precio"), rs.getInt("cantidad_disponible")))
				.forEach(entry -> productos.add(entry));
		// TODO recuperar imagen y categorias
		return productos;
	}
	
	public List<Producto> getProductosPorInicial(char inicial) throws SQLException {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL productosPorIncial('"+inicial+"');";
		jdbcTemplate
				.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"), rs.getString("nombre"),
								rs.getString("descripcion"), rs.getFloat("precio"), rs.getInt("cantidad_disponible")))
				.forEach(entry -> productos.add(entry));
		// TODO recuperar imagen y categorias
		return productos;
	}
	
	public List<Producto> getProductosPorDisponibilidad() throws SQLException {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL productosPorDisponibilidad();";
		jdbcTemplate
				.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"), rs.getString("nombre"),
								rs.getString("descripcion"), rs.getFloat("precio"), rs.getInt("cantidad_disponible")))
				.forEach(entry -> productos.add(entry));
		// TODO recuperar imagen y categorias
		return productos;
	}
	

}
