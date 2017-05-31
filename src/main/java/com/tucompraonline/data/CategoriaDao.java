package com.tucompraonline.data;

import java.sql.SQLException;
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

import com.tucompraonline.domain.Categoria;

@Repository
public class CategoriaDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall SJDBCInsertarCategoria;
	private SimpleJdbcCall SJDBCActualizarCategoria;
	private SimpleJdbcCall SJDBCEliminarCategoria;
	
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.SJDBCInsertarCategoria = new SimpleJdbcCall(dataSource).withProcedureName("insertarCategoria");
		this.SJDBCActualizarCategoria = new SimpleJdbcCall(dataSource).withProcedureName("updateCategoria");
		this.SJDBCEliminarCategoria = new SimpleJdbcCall(dataSource).withProcedureName("deleteCategoria");
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
	
	@Transactional
	public Categoria insertarCategoria (Categoria categoria) throws SQLException {
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("nombre_categoria", categoria.getNombre())
				.addValue("descripcion_categoria", categoria.getDescripcion());
		Map<String, Object> outParameters = SJDBCInsertarCategoria.execute(parameterSource);
		categoria.setIdCategoria(Integer.parseInt(outParameters.get("id_categoria").toString()));
		return categoria;
	}
	
	
	@Transactional
	public boolean eliminarCategoria(int idCategoria) {
		try {
			SqlParameterSource procedimientoEliminaCategoria = new MapSqlParameterSource().addValue("_id_categoria",
					idCategoria);
			SJDBCEliminarCategoria.execute(procedimientoEliminaCategoria);
			return true;
		} catch (Error e) {
			return false;
		}
	}
	
	@Transactional
	public boolean actualizaCategoria(Categoria categoria) {
		try {
			SqlParameterSource procedimientoActualizarCategoria = new MapSqlParameterSource()
					.addValue("_id_categoria", categoria.getIdCategoria())
					.addValue("_nombre", categoria.getNombre())
					.addValue("_descripcion", categoria.getDescripcion());
			SJDBCActualizarCategoria.execute(procedimientoActualizarCategoria);

		} catch (Error e) {
			return false;
		}
		return true;

	}
	
	
	
}
