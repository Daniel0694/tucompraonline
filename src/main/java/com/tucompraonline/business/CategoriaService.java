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
	
}
