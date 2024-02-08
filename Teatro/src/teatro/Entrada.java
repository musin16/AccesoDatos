package teatro;

public class Entrada {
	private int id;
	private TipoButaca butaca;
	private int idEspectaculo;
	private float precio;

	public Entrada(int id, TipoButaca butaca, int idEspectaculo, float precio) {
		this.id = id;
		this.butaca = butaca;
		this.idEspectaculo = idEspectaculo;
		this.precio = precio;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoButaca getButaca() {
		return butaca;
	}

	public void setButaca(TipoButaca butaca) {
		this.butaca = butaca;
	}

	public int getIdEspectaculo() {
		return idEspectaculo;
	}

	public void setIdEspectaculo(int idEspectaculo) {
		this.idEspectaculo = idEspectaculo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	// Método toString
	@Override
	public String toString() {
		return "Entrada{" + "id=" + id + ", butaca=" + butaca + ", idEspectaculo=" + idEspectaculo + ", precio="
				+ precio + '}';
	}
}
