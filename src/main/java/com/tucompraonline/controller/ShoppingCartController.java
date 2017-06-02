package com.tucompraonline.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tucompraonline.business.CategoriaService;
import com.tucompraonline.business.ProductoService;
import com.tucompraonline.domain.Producto;

@Controller
public class ShoppingCartController {

	@Autowired
	private ProductoService productoService;
	@Autowired
	private CategoriaService categoriaService;

	private List<Producto> productos;

	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String showInicio(Model model) {
		// model.addAttribute("productos", productos);

		productos = productoService.getProductos();
		model.addAttribute("productos", productos);
		try {
			model.addAttribute("categorias", categoriaService.getCategorias());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "paginaPrincipal";

	}
	
	@RequestMapping(value = "/buscarProducto", method = RequestMethod.POST)
	public String buscar(@RequestParam Map<String, String> requestParams, Model model) {
		
		int idCategoria = Integer.parseInt(requestParams.get("codigo"));
		String nombreProducto = requestParams.get("nombre");
		
		if(idCategoria == 0 && nombreProducto.trim() == ""){
			productos = productoService.getProductos();
		}else if(idCategoria == 0 && nombreProducto.trim() != ""){
			productos=productoService.getProductoNombre(nombreProducto);
		}
		else {
			if (idCategoria != 0 && nombreProducto.trim() == "") {
				try {
					productos = productoService.getProductosPorCategoria(idCategoria);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				productos = productoService.getProductosPorCategoriaYNombre(idCategoria,nombreProducto);
			}
		}
		
		model.addAttribute("productos", productos);
		try {
			model.addAttribute("categorias", categoriaService.getCategorias());
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
