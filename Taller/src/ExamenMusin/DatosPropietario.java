package ExamenMusin;

public class DatosPropietario {
	private String nif;
	private String nombre;
	private String telefono;

	public DatosPropietario() {
	}

	public DatosPropietario(String nif, String nombre, String telefono) {
		this.nif = nif;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "DatosPropietario [nif=" + nif + ", nombre=" + nombre + ", telefono=" + telefono + "]";
	}

}
