package Examen;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "idEmpleado", "nombre", "mensaje", "total" })
public class Chat {
	private int idEmpleado;
	private ArrayList<MensajesXML> mensaje;
	private String nombre;
	private int total;

	public Chat() {

	}

	public Chat(int idEmpleado, ArrayList<MensajesXML> mensaje, String nombre, int total) {
		this.idEmpleado = idEmpleado;
		this.mensaje = mensaje;
		this.nombre = nombre;
		this.total = total;
	}

	@XmlElement(name = "empleado")
	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	@XmlElementWrapper(name = "mensajes")
	@XmlElement(name = "mensaje")
	public ArrayList<MensajesXML> getMensaje() {
		return mensaje;
	}

	public void setMensaje(ArrayList<MensajesXML> mensaje) {
		this.mensaje = mensaje;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Chat [idEmpleado=" + idEmpleado + ", mensaje=" + mensaje + ", nombre=" + nombre + ", total=" + total
				+ "]";
	}

}
