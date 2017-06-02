package com.tucompraonline.data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tucompraonline.domain.Categoria;
import com.tucompraonline.domain.Producto;

@Repository
public class ProductoDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall SJDBCInsertaProducto;
	private SimpleJdbcCall SJDBCInsertaProductoCategoria;
	private SimpleJdbcCall SJDBCEliminaProducto;
	private SimpleJdbcCall SJDBCActualizaProducto;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.SJDBCInsertaProducto = new SimpleJdbcCall(dataSource).withProcedureName("insertarProducto");
		this.SJDBCInsertaProductoCategoria = new SimpleJdbcCall(dataSource).withProcedureName("insertarProductoCategoria");
		this.SJDBCActualizaProducto = new SimpleJdbcCall(dataSource).withProcedureName("updateProducto");
		this.SJDBCEliminaProducto = new SimpleJdbcCall(dataSource).withProcedureName("deleteProducto");
	}

	public List<Producto> getProductos() {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL obtenerProductos();";
		jdbcTemplate.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"), rs.getString("nombre"),
								rs.getString("descripcion"),
								rs.getFloat("precio"), 
								rs.getInt("cantidad_disponible"),
								rs.getString("imagen")))
				.forEach(entry -> productos.add(entry));
		// TODO recuperar categorias
		return productos;
	}
	
	public List<Producto> getProductosPorCategoria(int idCategoria) throws SQLException {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL productosPorCategoria("+idCategoria+");";
		jdbcTemplate.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"), 
								rs.getString("nombre"),
								rs.getString("descripcion"), 
								rs.getFloat("precio"), 
								rs.getInt("cantidad_disponible"),
								rs.getString("imagen")))
				.forEach(entry -> productos.add(entry));
		// TODO recuperar categorias
		return productos;
	}
	
	public List<Producto> getProductosPorRangoDePrecio(float minimo, float maximo) throws SQLException {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL productosPorPrecio("+minimo+","+maximo+");";
		jdbcTemplate.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"),
								rs.getString("nombre"),
								rs.getString("descripcion"), 
								rs.getFloat("precio"), 
								rs.getInt("cantidad_disponible"),
								rs.getString("imagen")))
				.forEach(entry -> productos.add(entry));
		// TODO recuperar  categorias
		return productos;
	}
	
	public List<Producto> getProductosPorInicial(char inicial) throws SQLException {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL productosPorIncial('"+inicial+"');";
		jdbcTemplate.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"), 
								rs.getString("nombre"),
								rs.getString("descripcion"),
								rs.getFloat("precio"),
								rs.getInt("cantidad_disponible"),
								rs.getString("imagen")))
				.forEach(entry -> productos.add(entry));
		// TODO recuperar  categorias
		return productos;
	}
	
	public List<Producto> getProductosPorDisponibilidad() throws SQLException {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL productosPorDisponibilidad();";
		jdbcTemplate.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"),
								rs.getString("nombre"),
								rs.getString("descripcion"), 
								rs.getFloat("precio"),
								rs.getInt("cantidad_disponible"),
								rs.getString("imagen")))
				.forEach(entry -> productos.add(entry));
		// TODO recuperar  categorias
		return productos;
	}
	
	@Transactional
	public Producto insertarProducto (Producto producto){
	
		SqlParameterSource parameterSourceProducto = new MapSqlParameterSource()
				.addValue("_nombre", producto.getNombreProducto())
				.addValue("_descripcion", producto.getDescripcion())
				.addValue("_precio", producto.getPrecio())
				.addValue("_cantidad_disponible", producto.getCantidadDisponible())
				.addValue("_imagen", producto.getRutaImagen());
		Map<String, Object> outParameters = SJDBCInsertaProducto.execute(parameterSourceProducto);
		
		producto.setIdProducto(Integer.parseInt(outParameters.get("_id_producto").toString()));
		
		for (int i = 0; i < producto.getCategorias().size(); i++) {
			SqlParameterSource parameterSourceProductoCategoria = new MapSqlParameterSource()
					.addValue("_id_producto", producto.getIdProducto())
					.addValue("_id_categoria", producto.getCategorias().get(i).getIdCategoria());
			
			Map outParametersForLibroAutor = SJDBCInsertaProductoCategoria.execute(parameterSourceProductoCategoria);
		}
		
		return producto;
	}
	
	@Transactional
	public boolean actualizaProducto (Producto producto){
		try {
			SqlParameterSource procedimientoActualizarProducto = new MapSqlParameterSource()
					.addValue("_id_producto", producto.getIdProducto())
					.addValue("_nombre", producto.getNombreProducto())
					.addValue("_descripcion", producto.getDescripcion())
					.addValue("_precio", producto.getPrecio())
					.addValue("_cantidad_disponible", producto.getCantidadDisponible())
					.addValue("_imagen", producto.getRutaImagen());
			SJDBCActualizaProducto.execute(procedimientoActualizarProducto);

		} catch (Error e) {
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean eliminaProducto (int idProducto){
		try {
			SqlParameterSource procedimientoEliminarProducto = new MapSqlParameterSource()
					.addValue("_id_producto",idProducto);
			SJDBCEliminaProducto.execute(procedimientoEliminarProducto);
			return true;
		} catch (Error e) {
			return false;
		}
	}
	
	public Producto getProducto(int idProducto) {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL obtenerProducto("+ idProducto+");";
		jdbcTemplate.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"), rs.getString("nombre"),
								rs.getString("descripcion"),
								rs.getFloat("precio"), 
								rs.getInt("cantidad_disponible"),
								rs.getString("imagen")))
				.forEach(entry -> productos.add(entry));
		return productos.get(0);
	}

	public List<Producto> getProductosPorCategoriaYNombre(int idCategoria, String nombreProducto) {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL productosPorCategoriaYNombre("+idCategoria+","+nombreProducto+");";
		jdbcTemplate.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"), 
								rs.getString("nombre"),
								rs.getString("descripcion"), 
								rs.getFloat("precio"), 
								rs.getInt("cantidad_disponible"),
								rs.getString("imagen")))
				.forEach(entry -> productos.add(entry));
		return productos;
	}

	public List<Producto> getProductoNombre(String nombreProducto) {
		List<Producto> productos = new ArrayList<>();

		String selectSql = "CALL productosPorNombre("+nombreProducto+");";
		jdbcTemplate.query(selectSql, new Object[] {},
						(rs, row) -> new Producto(rs.getInt("id_producto"), 
								rs.getString("nombre"),
								rs.getString("descripcion"), 
								rs.getFloat("precio"), 
								rs.getInt("cantidad_disponible"),
								rs.getString("imagen")))
				.forEach(entry -> productos.add(entry));
		return productos;
	}
	

}
