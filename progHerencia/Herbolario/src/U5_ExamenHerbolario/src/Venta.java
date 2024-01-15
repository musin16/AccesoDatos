package U5_ExamenHerbolario.src;

import java.util.Date;

public class Venta {
	private int codigo;
	private Date fecha;
	private int producto;
	private int cantidad;
	private int precio;

	public Venta() {
	}

	public Venta(int codigo, Date fecha, int producto, int cantidad, int precio) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public Venta(int producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Venta [codigo=" + codigo + ", fecha=" + fecha + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", precio=" + precio + "]";
	}

}
