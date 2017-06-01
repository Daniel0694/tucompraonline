package com.tucompraonline.domain;

import java.util.LinkedList;
import java.util.List;

 
public class Orden {

	private int numeroOrden;
	private String fecha;
	private float total;
	private String direccionEnvio;
	private List<Producto> productos;
	private Cliente cliente;
	
	
	public Orden(){
		this.cliente = new Cliente();
		this.productos = new LinkedList<>();
	}


	public int getNumeroOrden() {
		return numeroOrden;
	}


	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public float getTotal() {
		return total;
	}


	public void setTotal(float total) {
		this.total = total;
	}


	public String getDireccionEnvio() {
		return direccionEnvio;
	}


	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
