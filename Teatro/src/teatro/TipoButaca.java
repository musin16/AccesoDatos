package teatro;

public class TipoButaca {
	private int fila;
	private int columna;

	public TipoButaca(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	// Getters y Setters
	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	// Método toString
	@Override
	public String toString() {
		return "TipoButaca{" + "fila=" + fila + ", columna=" + columna + '}';
	}
}