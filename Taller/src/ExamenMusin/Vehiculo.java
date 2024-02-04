package ExamenMusin;

public class Vehiculo {
	private String matricula;
	private DatosPropietario propietario;
	public Vehiculo() {
	}
	public Vehiculo(String matricula, DatosPropietario propietario) {
		this.matricula = matricula;
		this.propietario = propietario;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public DatosPropietario getPropietario() {
		return propietario;
	}
	public void setPropietario(DatosPropietario propietario) {
		this.propietario = propietario;
	}
	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", propietario=" + propietario + "]";
	}

	
	
}
