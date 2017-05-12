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
}
