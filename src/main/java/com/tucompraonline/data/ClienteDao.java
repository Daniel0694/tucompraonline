package com.tucompraonline.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

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
		this.SJDBCActualizarCliente = new SimpleJdbcCall(dataSource).withProcedureName("actualzarCliente");
		this.SJDBCEliminarCliente = new SimpleJdbcCall(dataSource).withProcedureName("deleteCliente");
	}
	
	
	
	
}
