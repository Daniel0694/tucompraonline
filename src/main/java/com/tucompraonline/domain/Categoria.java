package com.tucompraonline.domain;

import java.util.LinkedList;
import java.util.List;

public class Categoria {

	private int idCategoria;
	private String nombre;
	private String descripcion;
	private List<Producto> productos;
	
	public Categoria(){
		this.productos = new LinkedList<>();
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
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

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
}
