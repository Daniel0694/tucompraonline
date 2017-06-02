package com.tucompraonline.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tucompraonline.business.ClienteService;
import com.tucompraonline.domain.Cliente;
import com.tucompraonline.domain.Usuario;


@Controller
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;

	
	
	
	@RequestMapping(value = "/actualizarCliente", method = RequestMethod.GET)
	public String showActualizarCliente(Cliente cliente) {
		
		return "actualizarCliente";
			
		
	}
	
	
	@RequestMapping(value = "/actualizarCliente/actualizar", method = RequestMethod.POST)
	public String actualizarCliente(Cliente cliente) {
		
		
			boolean actualizado = clienteService.actualizaCliente(cliente);
			if (actualizado){
				return "paginaPrincipalCliente";
			}
			else{
				return "error";
			}
				
		
	}

}
