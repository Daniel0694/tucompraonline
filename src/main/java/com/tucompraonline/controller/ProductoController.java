package com.tucompraonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tucompraonline.business.ProductoService;
import com.tucompraonline.domain.Producto;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	private List<Producto> productos;
	
	@RequestMapping(value = "/registroProducto", method = RequestMethod.GET)
	public String registrarProducto(Model model) {

		return "registroProducto";

	}
	
	
	@RequestMapping(value = "/mantenimientoProductos", method = RequestMethod.GET)
	public String mantenimientoProducto(Model model) {
		
		
		productos = productoService.getProductos();
		model.addAttribute("productos", productos);

		return "mantenimientoProductos";

	}

}
