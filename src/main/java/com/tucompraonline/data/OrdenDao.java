package com.tucompraonline.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tucompraonline.domain.Orden;
import com.tucompraonline.domain.Producto;


@Repository
public class OrdenDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall SJDBCInsertaOrden;
	private SimpleJdbcCall SJDBCInsertaOrdenProducto;
	private SimpleJdbcCall SJDBCEliminaOrden;
	private SimpleJdbcCall SJDBCActualizaOrden;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.SJDBCInsertaOrden = new SimpleJdbcCall(dataSource).withProcedureName("insertarOrden");
		this.SJDBCInsertaOrdenProducto = new SimpleJdbcCall(dataSource).withProcedureName("insertarProductoOrden");
		this.SJDBCActualizaOrden = new SimpleJdbcCall(dataSource).withProcedureName("updateOrden");
		this.SJDBCEliminaOrden = new SimpleJdbcCall(dataSource).withProcedureName("deleteOrden");
	}
	
	
	@Transactional
	public Orden insertarOrden (Orden orden){
	
		SqlParameterSource parameterSourceOrden = new MapSqlParameterSource()
				.addValue("_fecha", orden.getFecha())
				.addValue("_total", orden.getTotal())
				.addValue("_direccion_envio", orden.getDireccionEnvio())
				.addValue("_id_usuario", orden.getCliente().getIdUsuario());
		Map<String, Object> outParameters = SJDBCInsertaOrden.execute(parameterSourceOrden);
		
		orden.setNumeroOrden(Integer.parseInt(outParameters.get("_numero_orden").toString()));
		
		for (int i = 0; i < orden.getProductos().size(); i++) {
			SqlParameterSource parameterSourceProductoOrden = new MapSqlParameterSource()
					.addValue("_id_producto", orden.getProductos().get(i).getIdProducto())
					.addValue("_numero_orden", orden.getNumeroOrden())
					.addValue("_cantidad",orden.getProductos().get(i).getCantidadComprados());
			
			Map outParametersForLibroAutor = SJDBCInsertaOrdenProducto.execute(parameterSourceProductoOrden);
		}
		
		return orden;
	}
	
	@Transactional
	public boolean eliminaOrden (int idOrden){
		try {
			SqlParameterSource procedimientoEliminarOrden = new MapSqlParameterSource()
					.addValue("_numero_orden",idOrden);
			SJDBCEliminaOrden.execute(procedimientoEliminarOrden);
			return true;
		} catch (Error e) {
			return false;
		}
	}
	
	
	public Orden obtenerOrden(int idOrden) {
		String sqlSelect = "CALL getOrden("+ idOrden+")";
		List<Orden> orden = jdbcTemplate.query(sqlSelect, new OrdenesExtractor());

		if (orden.size() != 0) {
			return orden.get(0);
		} else {
			return null;
		}

	}
	
	public List<Orden> obtenerOrdenes() {
		String sqlSelect = "CALL getOrdenes()";
		List<Orden> ordenes = jdbcTemplate.query(sqlSelect, new OrdenesExtractor());

		if (ordenes.size() != 0) {
			return ordenes;
		} else {
			return null;
		}

	}

	private static final class OrdenesExtractor implements ResultSetExtractor<List<Orden>> {

		@Override
		public List<Orden> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Integer, Orden> map = new HashMap<Integer, Orden>();
			Orden orden = null;
			while (rs.next()) {
				Integer id = rs.getInt("numero_orden");
				orden = map.get(id);
				if (orden == null) {
					orden = new Orden();
					orden.setNumeroOrden(id);
					orden.setFecha(rs.getString("fecha"));
					orden.setTotal(rs.getFloat("total"));
					orden.setDireccionEnvio(rs.getString("direccion_envio"));
					orden.getCliente().setIdUsuario(rs.getInt("id_usuario"));
					
					map.put(id, orden);

				}

				int idProducto = rs.getInt("");
				if (idProducto > 0) {
					Producto producto = new Producto();
					producto.setIdProducto(idProducto);
					producto.setNombreProducto(rs.getString("nombre"));
					producto.setDescripcion(rs.getString("descripcion"));
					producto.setPrecio(rs.getFloat("precio"));
					orden.getProductos().add(producto);
				}

			}

			return new ArrayList<Orden>(map.values());
		}

	}
	
}
