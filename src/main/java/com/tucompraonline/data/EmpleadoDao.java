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
import com.tucompraonline.domain.Empleado;

@Repository
public class EmpleadoDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall SJDBCAgregarEmpleado;
	private SimpleJdbcCall SJDBCEliminarEmpleado;
	private SimpleJdbcCall SJDBCActualizarEmpleado;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.SJDBCAgregarEmpleado = new SimpleJdbcCall(dataSource).withProcedureName("insertarEmpleado");
		this.SJDBCActualizarEmpleado = new SimpleJdbcCall(dataSource).withProcedureName("actualizarEmpleado");
		this.SJDBCEliminarEmpleado = new SimpleJdbcCall(dataSource).withProcedureName("deleteEmpleado");
	}
	
	public Empleado getEmpleado (String user, String pass){
		
		List<Empleado> empleados = new ArrayList<>();

		String selectSql = "CALL getEmpleado("+ user+","+pass+");";
		jdbcTemplate.query(selectSql, new Object[] {},
						(rs, row) -> new Empleado(rs.getInt("id_usuario"),
								rs.getString("cedula"),
								rs.getString("nombre"),
								rs.getString("correo_electronico"), 
								rs.getString("telefono"),
								rs.getString("nombre_usuario"),
								rs.getString("password"),
								rs.getBoolean("activo")))
				.forEach(entry -> empleados.add(entry));
		
		return empleados.get(0);
		
	}
	
	@Transactional
	public Empleado insertarEmpleado (Empleado empleado){
		
		SqlParameterSource parameterSourceCliente = new MapSqlParameterSource()
				.addValue("_cedula", empleado.getCedula())
				.addValue("_nombre", empleado.getNombre())
				.addValue("_correo_electronico", empleado.getCorreoElectronico())
				.addValue("_telefono", empleado.getTelefono())
				.addValue("_nombre_usuario", empleado.getNombreUsuario())
				.addValue("_password", empleado.getPassword())
				.addValue("_activo", empleado.isActivo());
		Map<String, Object> outParameters = SJDBCAgregarEmpleado.execute(parameterSourceCliente);
		
		empleado.setIdUsuario(Integer.parseInt(outParameters.get("_id_usuario").toString()));
		
		
		return empleado;
	}
	
	
	@Transactional
	public boolean actualizaEmpleado (Empleado empleado){
		
		try {
			SqlParameterSource parameterSourceEmpleado = new MapSqlParameterSource()
					.addValue("id_usuario", empleado.getIdUsuario())
					.addValue("_cedula", empleado.getCedula())
					.addValue("_nombre", empleado.getNombre())
					.addValue("_correo_electronico", empleado.getCorreoElectronico())
					.addValue("_telefono", empleado.getTelefono())
					.addValue("_nombre_usuario", empleado.getNombreUsuario())
					.addValue("_password", empleado.getPassword());
			SJDBCActualizarEmpleado.execute(parameterSourceEmpleado);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean eliminaEmpleado( int idUsuario){
		try {
			SqlParameterSource procedimientoEliminarEmpleado = new MapSqlParameterSource()
					.addValue("_id_usuario",idUsuario);
			SJDBCEliminarEmpleado.execute(procedimientoEliminarEmpleado);
			return true;
		} catch (Error e) {
			return false;
		}
	}
	
}
