package com.tucompraonline.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tucompraonline.domain.Cliente;
import com.tucompraonline.domain.Producto;

@Repository
public class ClienteDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall SJDBCAgregarCliente;
	private SimpleJdbcCall SJDBCEliminarCliente;
	private SimpleJdbcCall SJDBCActualizarCliente;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.SJDBCAgregarCliente = new SimpleJdbcCall(dataSource).withProcedureName("insertarCliente");
		this.SJDBCActualizarCliente = new SimpleJdbcCall(dataSource).withProcedureName("actualizarCliente");
		this.SJDBCEliminarCliente = new SimpleJdbcCall(dataSource).withProcedureName("deleteCliente");
	}
	
	public Cliente getCliente (int idUsuario){
		
		List<Cliente> clientes = new ArrayList<>();

		String selectSql = "CALL obtenerCliente("+ idUsuario+");";
		jdbcTemplate.query(selectSql, new Object[] {},
						(rs, row) -> new Cliente(rs.getInt("id_usuario"),
								rs.getString("cedula"),
								rs.getString("nombre"),
								rs.getString("correo_electronico"), 
								rs.getString("telefono"),
								rs.getString("nombre_usuario"),
								rs.getString("password"),
								rs.getBoolean("activo"),
								rs.getString("pais"),
								rs.getString("estado"),
								rs.getString("ciudad"),
								rs.getString("codigo_postal"),
								rs.getString("direccion")))
				.forEach(entry -> clientes.add(entry));
		
		return clientes.get(0);
		
	}
	
	@Transactional
	public Cliente insertarCliente (Cliente cliente){

		
		SqlParameterSource parameterSourceCliente = new MapSqlParameterSource()
				.addValue("_cedula", cliente.getCedula())
				.addValue("_nombre", cliente.getNombre())
				.addValue("_correo_electronico", cliente.getCorreoElectronico())
				.addValue("_telefono", cliente.getTelefono())
				.addValue("_nombre_usuario", cliente.getNombreUsuario())
				.addValue("_password", cliente.getPassword())
				.addValue("_activo", cliente.isActivo())
				.addValue("_pais", cliente.getPais())
				.addValue("_estado", cliente.getEstado())
				.addValue("_ciudad", cliente.getCedula())
				.addValue("_cod_postal", cliente.getCodigoPostal())
				.addValue("_direccion", cliente.getDireccion());
		Map<String, Object> outParameters = SJDBCAgregarCliente.execute(parameterSourceCliente);
		
		cliente.setIdUsuario(Integer.parseInt(outParameters.get("_id_usuario").toString()));
		
		
		return cliente;
	}
	
	@Transactional
	public boolean actualizaCliente (Cliente cliente){
		
		try {
			SqlParameterSource parameterSourceCliente = new MapSqlParameterSource()
					.addValue("id_usuario", cliente.getIdUsuario())
					.addValue("_cedula", cliente.getCedula())
					.addValue("_nombre", cliente.getNombre())
					.addValue("_correo_electronico", cliente.getCorreoElectronico())
					.addValue("_telefono", cliente.getTelefono())
					.addValue("_nombre_usuario", cliente.getNombreUsuario())
					.addValue("_password", cliente.getPassword())
					.addValue("_activo", cliente.isActivo())
					.addValue("_pais", cliente.getPais())
					.addValue("_estado", cliente.getEstado())
					.addValue("_ciudad", cliente.getCedula())
					.addValue("_cod_postal", cliente.getCodigoPostal())
					.addValue("_direccion", cliente.getDireccion());
			SJDBCAgregarCliente.execute(parameterSourceCliente);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean eliminaCliente( int idUsuario){
		try {
			SqlParameterSource procedimientoEliminarEmpleado = new MapSqlParameterSource()
					.addValue("_id_usuario",idUsuario);
			SJDBCEliminarCliente.execute(procedimientoEliminarEmpleado);
			return true;
		} catch (Error e) {
			return false;
		}
	}
	

	
	
}
