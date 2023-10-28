package Examen;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "hora", "velocidad" })
public class Multas {
	private String matricula;
	private Date hora;
	private int velocidad;

	public Multas() {

	}

	public Multas(String matricula, Date hora, int velocidad) {
		this.matricula = matricula;
		this.hora = hora;
		this.velocidad = velocidad;
	}

	@XmlAttribute
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@XmlElement
	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	@XmlElement
	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	@Override
	public String toString() {
		return "Multas [matricula=" + matricula + ", hora=" + hora + ", velocidad=" + velocidad + "]";
	}

}
