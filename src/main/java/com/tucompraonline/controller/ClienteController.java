package com.tucompraonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {
	
	

	@RequestMapping(value = "/actualizarCliente", method = RequestMethod.GET)
	public String showActualizarCliente() {
		
		return "actualizarCliente ";
			
		
	}
	
	
	@RequestMapping(value = "/actualizarCliente/actualizar", method = RequestMethod.GET)
	public String actualizarCliente() {
		
		return "success";
			
		
	}

}
