package com.tucompraonline.domain;

public class Empleado extends Usuario {

	public Empleado(){
		super();
		
	}

	public Empleado(int idUsuario, String cedula, String nombre, String correoElectronico, String telefono) {
		super(idUsuario, cedula, nombre, correoElectronico, telefono);
	}

	
	
}
