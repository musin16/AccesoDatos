package U5_ExamenHerbolario.src;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	public static Scanner sc = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	public static SimpleDateFormat formato = new SimpleDateFormat("ddMMyyHHmm");

	public static void main(String[] args) {
		if (bd.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-E1-Alta Producto");
				System.out.println("2-Crear Precio");
				System.out.println("3-Crear Venta");
				System.out.println("4-Estadística Venta)");
				opcion = sc.nextInt();
				sc.nextLine();
				switch (opcion) {
				case 1:
					crearProducto();
					break;
				case 2:
					crearPrecio();
					break;
				case 3:
					crearVenta();
					break;
				case 4:
					estadisticasVentas();
					break;
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}

	private static void estadisticasVentas() {
		ArrayList<Venta> v = bd.estadisticasVenta();
		for (Venta venta : v) {
			System.out.print("Codigo producto: " + venta.getProducto() + " ");
			System.out.println("Cantidad: " + venta.getCantidad());
		}
	}

	private static void crearVenta() {
		mostrarProductos();
		System.out.println("Introduce un codigo de producto: ");
		Producto pr = bd.obtenerProducto(sc.nextInt());
		sc.nextLine();
		if (pr != null) {
			Venta venta = new Venta();
			System.out.println("Introduce la cantidad del producto: ");
			int cantidad = sc.nextInt();
			sc.nextLine();
			venta.setCantidad(cantidad);
			venta.setFecha(new Date());
			int posicionFinal = 0;
			venta.setPrecio(pr.getPrecios().get(posicionFinal) * cantidad);
			venta.setProducto(pr.getCodigo());
			if (bd.crearVenta(venta)) {
				System.out.println("Venta creada correctamente");
			} else {
				System.out.println("Error al crear la venta ");
			}
		} else {
			System.out.println("No existe el producto");
		}

	}

	private static void crearPrecio() {
		// TODO Auto-generated method stub
		mostrarProductos();
		System.out.println("Introduce el id para cambiar precio: ");
		Producto p = bd.obtenerProducto(sc.nextInt());
		sc.nextLine();
		if (p != null) {
			System.out.println("introduce nuevo precio");
			int nuevo = sc.nextInt();
			sc.nextLine();
			if (bd.crearPrecio(p, nuevo)) {
				mostrarProductos();
			} else {
				System.out.println("Error al crear el precio");
			}
		} else {
			System.out.println("Error, no existe producto");
		}
	}

	private static void crearProducto() {
		// TODO Auto-generated method stub
		Producto p = new Producto();
		System.out.println("Nombre:");
		p.setNombre(sc.nextLine());
		p.setDatosNutricion(new Info());
		System.out.println("KiloCalorías:");
		p.getDatosNutricion().setKcal(sc.nextInt());
		sc.nextLine();
		System.out.println("Grasas:");
		p.getDatosNutricion().setGrasas(sc.nextInt());
		sc.nextLine();
		System.out.println("Hidratos:");
		p.getDatosNutricion().setHidratos(sc.nextInt());
		sc.nextLine();
		if (bd.crearProducto(p)) {
			mostrarProductos();
		} else {
			System.out.println("Erorr, al crear el producto");
		}

	}

	private static void mostrarProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> productos = bd.obtenerProductos();
		for (Producto p : productos) {
			System.out.println(p);
		}
	}

}
