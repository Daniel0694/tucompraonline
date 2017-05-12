package com.tucompraonline.domain;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.util.LinkedList;
import java.util.List;

public class Producto {

	private int idProducto;
	private String nombreProducto;
	private String descripcion;
	private float precio;
	private int cantidadDisponible;
	private BufferedImage imagen;
	private List<Categoria> categorias;
	
	public Producto(){
		this.categorias = new LinkedList<>();
	}

	public Producto(int idProducto, String nombreProducto, String descripcion, float precio, int cantidadDisponible) {
			this.idProducto = idProducto;
			this.nombreProducto = nombreProducto;
			this.descripcion = descripcion;
			this.precio = precio;
			this.cantidadDisponible = cantidadDisponible;
			this.categorias = new LinkedList<>();
		
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public BufferedImage getImagen() {
		return imagen;
	}

	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	
}
