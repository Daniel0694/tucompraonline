package com.tucompraonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tucompraonline.business.ReporteVentaBusiness;
import com.tucompraonline.domain.ReporteVenta;

@Controller
public class ReporteVentaController {

	@Autowired
	private ReporteVentaBusiness reporteVentaService;
	private List<ReporteVenta> reporteVentas;
	

	@RequestMapping(value = "/topVenta", method = RequestMethod.GET)
	public String showActualizarEmpleado() {
		
//		reporteVentas = reporteVentaService.getReporteVentas();
//		model.addAttribute("reporteVentas", reporteVentas);
		return "topVentas";
			
		
	}
	
	
}
