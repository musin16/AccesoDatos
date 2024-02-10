package Biblioteca;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prestamo")
public class Prestamo {
	@Id
	@Column(nullable = false)
	private Date fechaP;
	@Column(nullable = false)
	private Date fechaDevolPrevista;
	@Column(nullable = false)
	private Date fechaDevolReal;
	@ManyToOne
	@JoinColumn(name = "socio", referencedColumnName = "id", nullable = false)
	private Socio socio;

	@ManyToOne
	@JoinColumn(name = "libro", referencedColumnName = "isbn", nullable = false)
	private Libro libro;

	public Prestamo() {

	}

	public Prestamo(Date fechaP, Date fechaDevolPrevista, Date fechaDevolReal, Socio socio, Libro libro) {
		super();
		this.fechaP = fechaP;
		this.fechaDevolPrevista = fechaDevolPrevista;
		this.fechaDevolReal = fechaDevolReal;
		this.socio = socio;
		this.libro = libro;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Date getFechaDevolPrevista() {
		return fechaDevolPrevista;
	}

	public void setFechaDevolPrevista(Date fechaDevolPrevista) {
		this.fechaDevolPrevista = fechaDevolPrevista;
	}

	public Date getFechaDevolReal() {
		return fechaDevolReal;
	}

	public void setFechaDevolReal(Date fechaDevolReal) {
		this.fechaDevolReal = fechaDevolReal;
	}

	public Date getFechaP() {
		return fechaP;
	}

	public void setFechaP(Date fechaP) {
		this.fechaP = fechaP;
	}

	@Override
	public String toString() {
		return "Prestamo [fechaP=" + fechaP + ", fechaDevolPrevista=" + fechaDevolPrevista + ", fechaDevolReal="
				+ fechaDevolReal + ", socio=" + socio + ", libro=" + libro + "]";
	}

}
