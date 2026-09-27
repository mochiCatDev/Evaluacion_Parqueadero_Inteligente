package com.krakedev.parqueadero.modelo;

public class Auto extends Vehiculo{
	private int numeroPuertas;
	
	public int getNumeroPuertas() {
		return numeroPuertas;
	}

	public void setNumeroPuertas(int numeroPuertas) {
		this.numeroPuertas = numeroPuertas;
	}
	
	public Auto(String placa, String propietario, int numeroPuertas) {
		super(placa, propietario);
		this.setNumeroPuertas(numeroPuertas);
	}

	@Override
	public double calcularTarifa(int horasPermanencia) {
		double tarifaBase = 1.5;
		double total = horasPermanencia * tarifaBase;
		
		if (horasPermanencia > 4) {
			total += 2;
		}
		
		return total;
	}
}
