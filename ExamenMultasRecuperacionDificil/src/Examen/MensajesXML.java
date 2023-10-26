package Examen;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MensajesXML implements Serializable{
	
	private Date fecha;
	//elemento
	private int idEmpleado;
	//atributo
	private int idMensaje;
	//ATRIBUTO
	private ArrayList<Mensaje> mensaje;
	//elemento
	private String nombre;
	//atributo
	private boolean activo;

	public MensajesXML() {

	}

	public MensajesXML(Date fecha, int idEmpleado, String nombre, String mensaje, boolean activo) {
		this.fecha = fecha;
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.mensaje = mensaje;
		this.activo = activo;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Mensaje [fecha=" + new SimpleDateFormat("dd:MM:yyyy hh:mm").format(fecha) + ", idEmpleado=" + idEmpleado
				+ ", nombre=" + nombre + ", mensaje=" + mensaje + ", activo=" + activo + "]";
	}

}
