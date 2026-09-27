package com.krakedev.parqueadero.controladores;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.parqueadero.modelo.Auto;
import com.krakedev.parqueadero.modelo.Motocicleta;
import com.krakedev.parqueadero.modelo.Vehiculo;
import com.krakedev.parqueadero.servicios.ServicioVehiculos;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {	
	private final ServicioVehiculos servicioVehiculos;

	public VehiculoController(ServicioVehiculos servicioVehiculos) {
		this.servicioVehiculos = servicioVehiculos;
	}

	@PostMapping("/auto")
	public ResponseEntity<String> ingresarAuto(@RequestBody Auto auto) {
		boolean agregado = servicioVehiculos.ingresarVehiculo(auto);
		
		if (agregado) {
			return new ResponseEntity<>("¡Auto ingresado con éxito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se pudo ingresar el auto", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/moto")
	public ResponseEntity<String> ingresarMoto(@RequestBody Motocicleta moto) {
		boolean agregado = servicioVehiculos.ingresarVehiculo(moto);
		
		if (agregado) {
			return new ResponseEntity<>("¡Motocicleta ingresada con éxito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se pudo ingresar la motocicleta", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ArrayList<Vehiculo> listarVehiculos() {
		return servicioVehiculos.listarVehiculos();
	}

	@GetMapping("/{placa}")
	public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable String placa) {
		Vehiculo vehiculoEncontrado = servicioVehiculos.buscarPorPlaca(placa);
		
		if (vehiculoEncontrado != null) {
			return new ResponseEntity<>(vehiculoEncontrado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}