package com.tucompraonline.domain;

public class Usuario {

	private int idUsuario;
	private String cedula;
	private String nombre;
	private String correoElectronico;
	private String telefono;
	private String nombreUsuario;
	private String password;
	private boolean activo;
	
	public Usuario(){
		
	}
	
	

	public Usuario(int idUsuario, String cedula, String nombre, String correoElectronico, String telefono,
			String nombreUsuario, String password, boolean activo) {
		super();
		this.idUsuario = idUsuario;
		this.cedula = cedula;
		this.nombre = nombre;
		this.correoElectronico = correoElectronico;
		this.telefono = telefono;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.activo = activo;
	}



	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
}
