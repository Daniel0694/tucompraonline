package com.tucompraonline.controller;

import java.sql.SQLException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tucompraonline.business.ClienteService;
import com.tucompraonline.business.EmpleadoService;
import com.tucompraonline.domain.Cliente;
import com.tucompraonline.domain.Empleado;
import com.tucompraonline.domain.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	ClienteService clienteService;
	@Autowired
	EmpleadoService empleadoService;
	
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
	
	@RequestMapping(value = "/registro/login", method = RequestMethod.POST)
	public String LoginUsuario(Usuario usuario) {
		
		Usuario usuarioDatos = new Usuario();
		BeanUtils.copyProperties(usuario, usuarioDatos);
		
		Cliente sesionCliente = clienteService.getCliente(usuarioDatos.getNombreUsuario(), usuarioDatos.getPassword()); 
		Empleado sesionEmpleado = empleadoService.getEmpleado(usuarioDatos.getNombreUsuario(), usuarioDatos.getPassword());
		
		if(sesionCliente != null){
			return "paginaPrincipalCliente";
		}else if (sesionEmpleado != null){
			return "paginaPrincipalEmpleado";
		}
		
		return "inicio";
			
		
	}
	

}
