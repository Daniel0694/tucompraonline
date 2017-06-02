package com.tucompraonline.domain;

public class Cliente extends Usuario{

	private String pais;
	private String estado;
	private String ciudad;
	private String codigoPostal;
	private String direccion;
	
	
	public Cliente() {
		super();	
		this.pais = "---"; 
		this.estado = "---";
		this.ciudad = "---";
		this.codigoPostal = "---";
		this.direccion = "---";
	}
	
	public Cliente(int idUsuario, String cedula, 
			String nombre, String correoElectronico,
			String telefono,String nombreUsuario,
			String password, boolean activo,
			String pais, String estado, String ciudad,
			String codigoPostal, String direccion) {
		super(idUsuario, cedula, nombre, correoElectronico, telefono, nombreUsuario, password, activo);
		
		this.pais = pais; 
		this.estado = estado;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
		this.direccion = direccion;
	}




	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
}
