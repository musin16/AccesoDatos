package taller;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class PiezaReparacion {
	@EmbeddedId
	private ClavePiezaReparacion clave;
	@Column(nullable = false)
	private int cantidad;
	@Column(nullable = false)
	private float precio;

	public PiezaReparacion() {

	}

	public PiezaReparacion(ClavePiezaReparacion clave, int cantidad, float precio) {
		super();
		this.clave = clave;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public ClavePiezaReparacion getClave() {
		return clave;
	}

	public void setClave(ClavePiezaReparacion clave) {
		this.clave = clave;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "PiezaReparacion [clave=" + clave + ", cantidad=" + cantidad + ", precio=" + precio + "]";
	}

}
