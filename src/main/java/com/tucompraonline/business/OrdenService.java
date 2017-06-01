package com.tucompraonline.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tucompraonline.data.OrdenDao;
import com.tucompraonline.domain.Orden;

@Service
public class OrdenService {

	@Autowired
	private OrdenDao ordenDao;
	
	
	public Orden insertarOrden (Orden orden){
		return ordenDao.insertarOrden(orden);
	}
	public boolean eliminaOrden (int idOrden){
		return ordenDao.eliminaOrden(idOrden);
	}
	public Orden obtenerOrden(int idOrden) {
		return ordenDao.obtenerOrden(idOrden);
	}
	public List<Orden> obtenerOrdenes() {
		return ordenDao.obtenerOrdenes();
	}
	
}
