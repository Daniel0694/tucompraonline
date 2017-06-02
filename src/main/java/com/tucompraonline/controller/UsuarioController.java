package com.tucompraonline.controller;

import java.sql.SQLException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tucompraonline.business.ClienteService;
import com.tucompraonline.domain.Cliente;
import com.tucompraonline.domain.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String showRegistroUsuario(Usuario usuarioForm) {
		
		return "signUp";
			
		
	}
	
	
	@RequestMapping(value = "/registro/salvar", method = RequestMethod.POST)
	public String salvarUsuario(Usuario usuario) {
		
		
		
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(usuario, cliente);
		cliente.setActivo(true);
		
		
		
		try {
			clienteService.insertarCliente(cliente);
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		return "success"; 
		
			
		
	}
	
	
	
	@RequestMapping(value = "/registro/login", method = RequestMethod.GET)
	public String LoginUsuario() {
		//si  el login esta bien hecho debe retornar success por el momento 
		return "success";
			
		
	}
	

}
