package taller;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ClavePiezaReparacion {
	@ManyToOne
	// Campo de la tabla reparacion que tiene la PK(clave primaria)
	@JoinColumn(name = "reparacion", referencedColumnName = "id")
	private Reparacion reparacion;
	@ManyToOne
	// Campo de la tabla pieza que tiene la PK(clave primaria)
	@JoinColumn(name = "pieza", referencedColumnName = "id")
	private Pieza pieza;

	public ClavePiezaReparacion() {
	}

	public ClavePiezaReparacion(Reparacion reparacion, Pieza pieza) {
		super();
		this.reparacion = reparacion;
		this.pieza = pieza;
	}

	public Reparacion getReparacion() {
		return reparacion;
	}

	public void setReparacion(Reparacion reparacion) {
		this.reparacion = reparacion;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	@Override
	public String toString() {
		return "ClavePiezaReparacion [reparacion=" + reparacion + ", pieza=" + pieza + "]";
	}

}
