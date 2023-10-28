package Examen;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "via","km","maximo","multas" })
public class Radar {
	private String via;
	private int km;
	private int maximo;
	private ArrayList<Multas> multas;

	public Radar() {

	}

	public Radar(String via, int km, int velMax, ArrayList<Multas> multas) {
		this.via = via;
		this.km = km;
		this.maximo = velMax;
		this.multas = multas;
	}
	@XmlElement
	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}
	@XmlElement
	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}
	@XmlElement
	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}
	@XmlElementWrapper
	@XmlElement(name = "multa")
	public ArrayList<Multas> getMultas() {
		return multas;
	}

	public void setMultas(ArrayList<Multas> multas) {
		this.multas = multas;
	}

	@Override
	public String toString() {
		return "Radar [via=" + via + ", km=" + km + ", maximo=" + maximo + ", multas=" + multas + "]";
	}



}
