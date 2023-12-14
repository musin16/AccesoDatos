package ExamenMusin;

public class Para {
	private Mensajes mensajes;
	private Empleados menEmpleados;
	private int leido;

	public Para() {
	}

	public Para(Mensajes mensajes, Empleados menEmpleados, int leido) {
		this.mensajes = mensajes;
		this.menEmpleados = menEmpleados;
		this.leido = leido;
	}

	public Mensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(Mensajes mensajes) {
		this.mensajes = mensajes;
	}

	public Empleados getMenEmpleados() {
		return menEmpleados;
	}

	public void setMenEmpleados(Empleados menEmpleados) {
		this.menEmpleados = menEmpleados;
	}

	public int getLeido() {
		return leido;
	}

	public void setLeido(int leido) {
		this.leido = leido;
	}

	@Override
	public String toString() {
		return "Para [mensajes=" + mensajes + ", menEmpleados=" + menEmpleados + ", leido=" + leido + "]";
	}

}
