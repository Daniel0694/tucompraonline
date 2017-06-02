package com.tucompraonline.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tucompraonline.business.CategoriaService;
import com.tucompraonline.domain.Categoria;


@Controller
public class CategoriaController {
	
	
	@Autowired
	private CategoriaService categoriaService;
	private List<Categoria> categorias;
	
	@RequestMapping(value = "/registroCategoria", method = RequestMethod.GET)
	public String showRegistrarCategoria(Model model) {

		return "registroCategoria";

	}
	
	
	@RequestMapping(value = "/registroCategoria/registrar", method = RequestMethod.GET)
	public String registrarCategoria(Model model) {

		return "success";

	}
	
	
	@RequestMapping(value = "/mantenimientoCategoria", method = RequestMethod.GET)
	public String showMantenimientoProducto(Model model) throws SQLException {
		
		
		categorias = categoriaService.getCategorias();
		model.addAttribute("categorias", categorias);

		return "mantenimientoCategoria";

	}
	
	
	@RequestMapping(value = "/actualizarCategoria", method = RequestMethod.GET)
	public String showactualizarCategoria(Model model) {
		
		//// traer  los  datos  del producto a  actualizar
	
	return "actualizarCategoria";
	
	}
	
	
	@RequestMapping(value = "/actualizarCategoria/actualizar", method = RequestMethod.GET)
	public String actualizarCategoria(Model model) {
		
		//// traer  los  datos  del producto a  actualizar
	
	return "success";
	
	}
	
	
	@RequestMapping(value = "/eliminarCategoria", method = RequestMethod.GET)
	public String eliminarProducto(Model model) {
		
		
		
	
	return "success";
	
	}


}
