package Examen;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RadarMovil {
	private String puntoKM;
	private int km;
	private String matricula;
	private Date hora;
	private int velocidadCaptada;

	public RadarMovil() {

	}

	public RadarMovil(String puntoKM, int km, String matricula, Date hora, int velocidadCaptada) {
		this.puntoKM = puntoKM;
		this.km = km;
		this.matricula = matricula;
		this.hora = hora;
		this.velocidadCaptada = velocidadCaptada;
	}

	public String getPuntoKM() {
		return puntoKM;
	}

	public void setPuntoKM(String puntoKM) {
		this.puntoKM = puntoKM;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date date) {
		this.hora = date;
	}

	public int getVelocidadCaptada() {
		return velocidadCaptada;
	}

	public void setVelocidadCaptada(int velocidadCaptada) {
		this.velocidadCaptada = velocidadCaptada;
	}

	@Override
	public String toString() {
		return "Vehiculo [puntoKM=" + puntoKM + ", km=" + km + ", matricula=" + matricula + ", hora="
				+ new SimpleDateFormat("hh:mm").format(hora) + ", velocidadCaptada=" + velocidadCaptada + "]";
	}

}
