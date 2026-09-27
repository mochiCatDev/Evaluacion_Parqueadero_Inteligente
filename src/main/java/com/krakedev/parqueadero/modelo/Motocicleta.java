package com.krakedev.parqueadero.modelo;

public class Motocicleta extends Vehiculo {
	private int cilindraje;

	public Motocicleta(String placa, String propietario) {
		super(placa, propietario);
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	@Override
	public double calcularTarifa(int horasPermanencia) {
		double tarifaBase = 0.75;

		if (cilindraje > 250) {
			tarifaBase = 1;
		}

		double total = horasPermanencia * tarifaBase;
		return total;
	}

}
