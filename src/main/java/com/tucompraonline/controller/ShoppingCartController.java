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
public class ShoppingCartController {

	@Autowired
	private ProductoService productoService;

	private List<Producto> productos;

	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String showInicio(Model model) {
		// model.addAttribute("productos", productos);

		productos = productoService.getProductos();
		model.addAttribute("productos", productos);

		return "paginaPrincipal";

	}
	
	
	@RequestMapping(value = "/inicioEmpleado", method = RequestMethod.GET)
	public String showInicioEmpleado(Model model) {
		// model.addAttribute("productos", productos);

		productos = productoService.getProductos();
		model.addAttribute("productos", productos);

		return "paginaPrincipalEmpleado";

	}
	
	@RequestMapping(value = "/inicioCliente", method = RequestMethod.GET)
	public String showInicioCliente(Model model) {
		// model.addAttribute("productos", productos);

		productos = productoService.getProductos();
		model.addAttribute("productos", productos);

		return "paginaPrincipalCliente";

	}

}
