package com.tucompraonline.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tucompraonline.business.CategoriaService;
import com.tucompraonline.domain.Categoria;


@Controller
public class CategoriaController {


	@Autowired
	private CategoriaService categoriaService;
	private List<Categoria> categorias;
	private Categoria categoriaActual;

	@RequestMapping(value = "/registroCategoria", method = RequestMethod.GET)
	public String showRegistrarCategoria(Model model) {

	
		
		return "registroCategoria";

	}


	@RequestMapping(value = "/registroCategoria/registrar", method = RequestMethod.POST)
	public String registrarCategoria(@RequestParam Map<String, String> requestParams,Model model) {


		String nombreCat = requestParams.get("nombre");
		String descripcionCat =requestParams.get("descripcion");

		Categoria categoria = new Categoria(0,nombreCat,descripcionCat);	

		try {
			categoriaService.insertarCategoria(categoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		try {
			model.addAttribute("categorias", categoriaService.getCategorias());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "actualizarCategoria";

	}


	@RequestMapping(value = "/actualizarCategoria/actualizar", method = RequestMethod.POST)
	public String actualizarCategoria(@RequestParam Map<String, String> requestParams,Model model) {

		int id =Integer.parseInt( requestParams.get("codigo"));
		String nombreCat = requestParams.get("nombre");
		String descripcionCat =requestParams.get("descripcion");

		Categoria categoria = new Categoria(id,nombreCat,descripcionCat);	

		categoriaService.actualizaCategoria(categoria);

		return "success";

	}



	@RequestMapping(value ="/eliminarCategoria/{id}/**", method = RequestMethod.GET)
	public String   showeliminarCategoria(@PathVariable String id, HttpServletRequest request, Model model) {

		String var = (new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI()));
		int idCat = Integer.parseInt(var);

		categoriaActual = categoriaService.getCategoria(idCat);
		
		categoriaService.eliminarCategoria(categoriaActual.getIdCategoria());
		
		 return "success";

	}


}
