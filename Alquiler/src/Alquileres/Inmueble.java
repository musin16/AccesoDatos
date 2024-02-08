package Alquileres;

import java.util.ArrayList;

public class Inmueble {
	private int codigo;
	private Direccion dir;
	private int numeroHabitaciones;
	private int numeroBanios;
	private ArrayList<String> extras = new ArrayList<String>();

	public Inmueble() {
		// TODO Auto-generated constructor stub
	}

	public Inmueble(int codigo, Direccion dir, int numeroHabitaciones, int numeroBanios, ArrayList<String> extras) {
		this.codigo = codigo;
		this.dir = dir;
		this.numeroHabitaciones = numeroHabitaciones;
		this.numeroBanios = numeroBanios;
		this.extras = extras;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Direccion getDir() {
		return dir;
	}

	public void setDir(Direccion dir) {
		this.dir = dir;
	}

	public int getNumeroHabitaciones() {
		return numeroHabitaciones;
	}

	public void setNumeroHabitaciones(int numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	public int getNumeroBanios() {
		return numeroBanios;
	}

	public void setNumeroBanios(int numeroBanios) {
		this.numeroBanios = numeroBanios;
	}

	public ArrayList<String> getExtras() {
		return extras;
	}

	public void setExtras(ArrayList<String> extras) {
		this.extras = extras;
	}

	@Override
	public String toString() {
		return "Inmueble [codigo=" + codigo + ", dir=" + dir + ", numeroHabitaciones=" + numeroHabitaciones
				+ ", numeroBanios=" + numeroBanios + ", extras=" + extras + "]";
	}

}
