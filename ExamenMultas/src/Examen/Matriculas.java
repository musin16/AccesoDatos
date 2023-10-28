package Examen;

import java.util.Date;

public class Matriculas {
	private String matricula; // 7 car 24 bite
	private int numMultas;
	private float importeRecaudado; // 4 bite
	private long fecha; // 8

	public Matriculas() {

	}

	public Matriculas(String matricula, int numMultas, float importeRecaudado, long fecha) {
		this.matricula = matricula;
		this.numMultas = numMultas;
		this.importeRecaudado = importeRecaudado;
		this.fecha = fecha;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getNumMultas() {
		return numMultas;
	}

	public void setNumMultas(int numMultas) {
		this.numMultas = numMultas;
	}

	public float getImporteRecaudado() {
		return importeRecaudado;
	}

	public void setImporteRecaudado(float importeRecaudado) {
		this.importeRecaudado = importeRecaudado;
	}

	public long getFecha() {
		return fecha;
	}

	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Matriculas [matricula=" + matricula + ", numMultas=" + numMultas + ", importeRecaudado="
				+ importeRecaudado + ", fecha=" + fecha + "]";
	}
	
	/*
	 * Matrícula: Texto de 7 caracteres.
	 * 
	 *  Nº de multas: Nº entero.  Importe recaudado: Nº float.  Fecha de la
	 * última actualización: Fecha en formato long. El procedimiento es el
	 * siguiente:  Se pedirá por teclado el nombre del fichero xml que se va a
	 * cargar.  Para cada una de las multas de ese fichero, se buscará en el
	 * fichero multas.bin la matrícula. o Si no se encuentra la matrícula, se
	 * añadirá la matrícula en cuestión al fichero con nº de multas y el importe
	 * recaudado con esa multa. o Si se encuentra, se incrementará en 1 el nº de
	 * multas, se acumulará el importe de esa multa al importe que hay en el fichero
	 * y se modificará la fecha de la última actualización.
	 * 
	 *  Una vez cargado todos los datos se debe mostrar el fichero multas.bin.
	 */
}
