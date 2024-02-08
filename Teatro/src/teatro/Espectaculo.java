package teatro;

import java.util.List;

public class Espectaculo {
	private int id;
	private String titulo;
	private List<Double> precios;

	public Espectaculo() {
		// TODO Auto-generated constructor stub
	}

	public Espectaculo(int id, String titulo, List<Double> precios) {
		this.id = id;
		this.titulo = titulo;
		this.precios = precios;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Double> getPrecios() {
		return precios;
	}

	public void setPrecios(List<Double> precios) {
		this.precios = precios;
	}

	// Método toString
	@Override
	public String toString() {
		return "Espectaculo{" + "id=" + id + ", titulo='" + titulo + '\'' + ", precios=" + precios + '}';
	}
}