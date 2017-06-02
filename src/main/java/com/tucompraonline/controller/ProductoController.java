package com.tucompraonline.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tucompraonline.business.CategoriaService;
import com.tucompraonline.business.ProductoService;
import com.tucompraonline.data.ProductoDao;
import com.tucompraonline.domain.Categoria;
import com.tucompraonline.domain.Producto;
import com.tucompraonline.forms.ProductoForm;

@Controller
public class ProductoController {
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private ProductoService productoService;
	private List<Producto> productos;
	private List<Categoria> categorias;

	@RequestMapping(value = "/registroProducto", method = RequestMethod.GET)
	public String showRegistrarProducto(ProductoForm productoForm, Model model) {
		try {
			model.addAttribute("categorias", categoriaService.getCategorias());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "registroProducto";

	}

	@RequestMapping(value = "/registroProducto/salvar", method = RequestMethod.POST)
	public String registrarProducto(ProductoForm productoForm, Model model) {
		try {
			model.addAttribute("categorias", categoriaService.getCategorias());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Producto producto = new Producto();
		BeanUtils.copyProperties(productoForm, producto);

		for (int i = 0; i < productoForm.getCodCategorias().length; i++) {
			Categoria categoria = new Categoria();
			categoria.setIdCategoria(productoForm.getCodCategorias()[i]);
			producto.getCategorias().add(categoria);
		}

		try {
			productoService.insertarProducto(producto);
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";

	}

	@RequestMapping(value = "/mantenimientoProductos", method = RequestMethod.GET)
	public String showMantenimientoProducto(Model model) {

		productos = productoService.getProductos();
		model.addAttribute("productos", productos);

		return "mantenimientoProductos";

	}

	@RequestMapping(value = "/actualizarProductos", method = RequestMethod.GET)
	public String showActualizarProducto(Model model) {

		//// traer los datos del producto a actualizar

		return "actualizarProducto";

	}

	@RequestMapping(value = "/actualizarProductos/actualizar", method = RequestMethod.GET)
	public String actualizarProducto(Model model) {

		//// traer los datos del producto a actualizar

		return "success";

	}

	@RequestMapping(value = "/eliminarProducto", method = RequestMethod.GET)
	public String eliminarProducto(Model model) {

		return "success";

	}

}
