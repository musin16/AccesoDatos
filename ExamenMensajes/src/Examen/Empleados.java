package Examen;

public class Empleados {
	private int idEmpleado;
	private String nombre;
	private boolean activo;

	public Empleados() {

	}

	public Empleados(int idEmpleado, String nombre, boolean activo) {
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.activo = activo;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Empleados [idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", activo=" + activo + "]";
	}

}
