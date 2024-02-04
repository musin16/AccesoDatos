package ExamenMusin;

import java.util.ArrayList;
import java.util.Date;

public class Revision {
	private int id;
	private String vehiculo;
	private Date fecha;
	private int horas;
	private ArrayList<String> actuaciones;

	public Revision() {
	}

	public Revision(int id, String vehiculo, Date fecha, int horas, ArrayList<String> actuaciones) {
		this.id = id;
		this.vehiculo = vehiculo;
		this.fecha = fecha;
		this.horas = horas;
		this.actuaciones = actuaciones;
	}

	public Revision(int id, String vehiculo, Date fecha) {
		this.id = id;
		this.vehiculo = vehiculo;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public ArrayList<String> getActuaciones() {
		return actuaciones;
	}

	public void setActuaciones(ArrayList<String> actuaciones) {
		this.actuaciones = actuaciones;
	}

	@Override
	public String toString() {
		String actual = "";
		for (String actuacion : actuaciones) {
			actual += actuacion + ",";
		}
		return "Revision [id=" + id + ", vehiculo=" + vehiculo + ", fecha=" + fecha + ", horas=" + horas
				+ ", actuaciones=" + actual + "]";
	}

}
