package Examen;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "fecha", "mensaje" })
public class MensajesXML implements Serializable {

	private Date fecha;
	private int idMensaje;
	private String mensaje;
	private boolean leido;

	public MensajesXML() {
	}

	public MensajesXML(Date fecha, int idMensaje, String mensaje, boolean leido) {
		this.fecha = fecha;
		this.idMensaje = idMensaje;
		this.mensaje = mensaje;
		this.leido = leido;
	}

	@XmlElement(name = "fecha")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@XmlAttribute(name = "id")
	public int getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(int idMensaje) {
		this.idMensaje = idMensaje;
	}

	@XmlElement(name = "mensaje")
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@XmlAttribute(name = "leido")
	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	@Override
	public String toString() {
		SimpleDateFormat mf = new SimpleDateFormat("dd:MM:yyyy");
		String fechaForm = mf.format(fecha);
		return "MensajesXML [fecha=" + fechaForm + ", idMensaje=" + idMensaje + ", mensaje=" + mensaje
				+ ", leido=" + leido + "]";
	}

}
