package com.tucompraonline.domain;

public class Empleado extends Usuario {

	public Empleado(){
		super();
		
	}

	public Empleado(int idUsuario, String cedula, String nombre, String correoElectronico, String telefono,
			String nombreUsuario, String password, boolean activo) {
		super(idUsuario, cedula, nombre, correoElectronico, telefono, nombreUsuario, password, activo);
	}

	
	
}
