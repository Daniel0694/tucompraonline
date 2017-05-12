package com.tucompraonline.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tucompraonline.domain.Categoria;

@Repository
public class CategoriaDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Categoria> getCategorias() throws SQLException{
		List<Categoria> categorias = new ArrayList<>();

		String selectSql = "CALL obtenerCategorias();";
		jdbcTemplate
				.query(selectSql, new Object[] {}, (rs, row) -> new Categoria(rs.getInt("id_categoria"),
						rs.getString("nombre"), rs.getString("descripcion")))
				.forEach(entry -> categorias.add(entry));

		return categorias;
	}
}
