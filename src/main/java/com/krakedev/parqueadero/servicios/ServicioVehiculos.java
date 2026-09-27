package com.krakedev.parqueadero.servicios;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.krakedev.parqueadero.modelo.Vehiculo;

@Service
public class ServicioVehiculos {
	private ArrayList<Vehiculo> parqueadero = new ArrayList<>();
	private final int CAPACIDAD_MAXIMA = 10;

	public Vehiculo buscarPorPlaca(String placa) {
		if (placa == null) {
			return null;
		}

		String placaTrim = placa.trim();

		for (Vehiculo v : parqueadero) {
			if (v != null && v.getPlaca() != null) {
				if (v.getPlaca().trim().equals(placaTrim)) {
					return v;
				}
			}
		}

		return null;
	}

	public boolean ingresarVehiculo(Vehiculo vehiculo) {
		if (parqueadero.size() >= CAPACIDAD_MAXIMA) {
			return false;
		}

		Vehiculo existe = buscarPorPlaca(vehiculo.getPlaca());

		if (existe == null) {
			parqueadero.add(vehiculo);
			return true;
		} else {
			return false;
		}
	}
	
	public Vehiculo retirarVehiculo(String placa) {
		Vehiculo existe = buscarPorPlaca(placa);
		
		if (existe != null) {
			parqueadero.remove(existe);
			return existe;
		} else {
			return null;
		}
	}
	
	public ArrayList<Vehiculo> listarVehiculos() {
		return parqueadero;	
	}
}
