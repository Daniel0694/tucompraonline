package com.tucompraonline.domain;

public class ReporteVenta {
	
	private String nombre,descripcion, rutaImagen;
	private float precio;
	private int catidadDisponible,total;
	
	public ReporteVenta() {
	
		this.nombre = "";
		this.descripcion = "";
		this.rutaImagen = "";
		this.precio = 0;
		this.catidadDisponible = 0;
		this.total = 0;
	}
	
	
	public ReporteVenta(String nombre, String descripcion, String rutaImagen, float precio, int catidadDisponible,
			int total) {
	
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.rutaImagen = rutaImagen;
		this.precio = precio;
		this.catidadDisponible = catidadDisponible;
		this.total = total;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getCatidadDisponible() {
		return catidadDisponible;
	}
	public void setCatidadDisponible(int catidadDisponible) {
		this.catidadDisponible = catidadDisponible;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	

	

}
