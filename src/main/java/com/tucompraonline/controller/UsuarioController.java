package com.tucompraonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuarioController {
	
	
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String showRegistroUsuario() {
		
		return "signUp";
			
		
	}
	
	
	@RequestMapping(value = "/registro/salvar", method = RequestMethod.GET)
	public String salvarUsuario() {
		
		return "signUp";
			
		
	}
	
	
	
	@RequestMapping(value = "/registro/login", method = RequestMethod.GET)
	public String LoginUsuario() {
		//si  el login esta bien hecho debe retornar success por el momento 
		return "success";
			
		
	}
	

}
