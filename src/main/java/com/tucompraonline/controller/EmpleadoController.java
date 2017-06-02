package com.tucompraonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmpleadoController {
	
	
	

	@RequestMapping(value = "/actualizarEmpleado", method = RequestMethod.GET)
	public String showActualizarEmpleado() {
		
		return "actualizarEmpleado";
			
		
	}
	
	
	@RequestMapping(value = "/actualizarEmpleado/actualizar", method = RequestMethod.GET)
	public String actualizarEmpleado() {
		
		return "success";
			
		
	}
}
