package com.tucompraonline.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tucompraonline.data.ClienteDao;
import com.tucompraonline.domain.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteDao clienteDao;
	
	
	public Cliente getCliente (int idUsuario){
		return clienteDao.getCliente(idUsuario);
	}
	public Cliente insertarCliente (Cliente cliente){
		return clienteDao.insertarCliente(cliente);
	}
	
	public boolean actualizaCliente (Cliente cliente){
		return clienteDao.actualizaCliente(cliente);
	}
	
	public boolean eliminaCliente( int idUsuario){
		return clienteDao.eliminaCliente(idUsuario);
	}
}
