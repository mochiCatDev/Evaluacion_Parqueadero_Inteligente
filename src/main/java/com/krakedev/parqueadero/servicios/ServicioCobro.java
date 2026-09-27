package com.krakedev.parqueadero.servicios;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.krakedev.parqueadero.modelo.TicketCobro;
import com.krakedev.parqueadero.modelo.Vehiculo;

@Service
public class ServicioCobro {
	private ArrayList<TicketCobro> historicoTickets = new ArrayList<>();
	
	private final ServicioVehiculos servicioVehiculos;
	
	public ServicioCobro(ServicioVehiculos servicioVehiculos) {
		this.servicioVehiculos = servicioVehiculos;
	}
	
	private String generarCodigo() {
		int numeroAleatorio = (int) (Math.random() * 900) + 100;
		return "TCK-" + numeroAleatorio;
	}
	
	public TicketCobro procesarSalida(String placa, int horas) {
		Vehiculo vehiculoSalio = servicioVehiculos.retirarVehiculo(placa);
		
		if (vehiculoSalio == null) {
			return null;
		}
		
		double total = vehiculoSalio.calcularTarifa(horas);
		String codigoAleatorio = generarCodigo();
		LocalDate fechaSalida = LocalDate.now();
		TicketCobro ticket = new TicketCobro(codigoAleatorio, vehiculoSalio, horas, total, fechaSalida);
		
		historicoTickets.add(ticket);
		return ticket;
	}
	
	public double calcularTotalRecaudado() {
		double totalRecaudado = 0;
		
		for (TicketCobro t : historicoTickets) {
			if (t != null) {
				totalRecaudado += t.getTotalPagar();
			}
		}
		
		return totalRecaudado;
	}
	
	public ArrayList<TicketCobro> listarTickets() {
		return historicoTickets;
	}
}
