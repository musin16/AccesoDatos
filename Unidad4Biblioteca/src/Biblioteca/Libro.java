package Biblioteca;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Libro {
	@Id
	private String isbn;
	@Column(nullable = false)
	private int numEjemplares;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private String autor;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "libro")
	private List<Prestamo> prestamos = new ArrayList<>();

	public Libro() {
		// TODO Auto-generated constructor stub
	}

	public Libro(String isbn, int numEjemplares, String titulo, String autor) {
		super();
		this.isbn = isbn;
		this.numEjemplares = numEjemplares;
		this.titulo = titulo;
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getNumEjemplares() {
		return numEjemplares;
	}

	public void setNumEjemplares(int numEjemplares) {
		this.numEjemplares = numEjemplares;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", numeroEjemplares=" + numEjemplares + ", titulo=" + titulo + ", autor=" + autor
				+ ", prestamos=" + prestamos + "]";
	}

}
