package com.tucompraonline.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tucompraonline.data.CategoriaDao;
import com.tucompraonline.domain.Categoria;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDao categoriaDao;
	
	public List<Categoria> getCategorias() throws SQLException {
		return categoriaDao.getCategorias();
	}
	public Categoria insertarCategoria (Categoria categoria) throws SQLException {
		return categoriaDao.insertarCategoria(categoria);
	}
	public boolean eliminarCategoria(int idCategoria) {
		return categoriaDao.eliminarCategoria(idCategoria);
	}
	public boolean actualizaCategoria(Categoria categoria) {
		return categoriaDao.actualizaCategoria(categoria);
	}
	public Categoria getCategoria(int idCategoria) {
		return categoriaDao.getCategoria(idCategoria);
	}
	
}
