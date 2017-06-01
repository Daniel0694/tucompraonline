package com.tucompraonline.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tucompraonline.data.EmpleadoDao;
import com.tucompraonline.domain.Empleado;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoDao empleadoDao;
	
	public Empleado insertarEmpleado (Empleado empleado){
		return empleadoDao.insertarEmpleado(empleado);
	}
	public boolean actualizaEmpleado (Empleado empleado){
		return empleadoDao.actualizaEmpleado(empleado);
	}
	
	public boolean eliminaEmpleado( int idUsuario){
		return empleadoDao.eliminaEmpleado(idUsuario);
	}
	
	public Empleado getEmpleado (String user, String pass){
		return empleadoDao.getEmpleado(user, pass);
	}
}
