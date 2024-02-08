package Alquileres;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	public static Scanner sc = new Scanner(System.in);
	public static Modelo ad = new Modelo();
	public static SimpleDateFormat formato = new SimpleDateFormat("ddMMyyHHmm");

	public static void main(String[] args) {
		if (ad.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Inmueble");
				System.out.println("2-Añadir Extras");
				System.out.println("3-Crear Alquiler");
				System.out.println("4-BorrarInmueble)");
				opcion = sc.nextInt();
				sc.nextLine();
				switch (opcion) {
				case 1:
					crearInmueble();
					break;
				case 2:
					aniadirExtra();
					break;
				case 3:
					crearAlquiler();
					break;
				case 4:
					borrarInmueble();
					break;
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}

	private static void crearInmueble() {
		Inmueble in = new Inmueble();
		System.out.println("Introduce el calle: ");
		in.setDir(new Direccion());
		in.getDir().setCalle(sc.nextLine());
		System.out.println("Inroduce el numero: ");
		int num = sc.nextInt();
		sc.nextLine();
		in.getDir().setNumero(num);
		System.out.println("Intorduce el codigo postal: ");
		int cp = sc.nextInt();
		sc.nextLine();
		in.getDir().setCp(cp);
		System.out.println("Inroduce el numero de habitaciones: ");
		in.setNumeroHabitaciones(sc.nextInt());
		sc.nextLine();
		System.out.println("Inroduce el numero de banios: ");
		in.setNumeroBanios(sc.nextInt());
		sc.nextLine();
		if (ad.crearInmueble(in)) {
			System.out.println("El inmueble se ha creado correctamente");
			mostrarTodosInmuebles();
		} else {
			System.out.println("Error al crear el inmueble");
		}
	}

	private static void borrarInmueble() {

	}

	private static void mostrarTodosInmuebles() {
		ArrayList<Inmueble> in = ad.obtenerInmueles();
		for (Inmueble inmueble : in) {
			System.out.println(inmueble);
		}

	}

	private static void crearAlquiler() {
		mostrarTodosInmuebles();
		System.out.println("Introduce el id de un inmueble: ");
		int id = sc.nextInt();
		sc.nextLine();
		Inmueble in = ad.obtenerInmuele(id);
		if (in != null) {
			
		}else {
			System.out.println("Inmueble no existe");
		}
	}

	private static void aniadirExtra() {
		mostrarTodosInmuebles();
		System.out.println("Introduce el id de un inmueble: ");
		int id = sc.nextInt();
		sc.nextLine();
		Inmueble in = ad.obtenerInmuele(id);
		if (in != null) {
			System.out.println("Añade un extra: ");
			in.getExtras().add(sc.nextLine());
			if (ad.modificaInmueble(in)) {
				System.out.println("SE HA MODIFICADO CORRECTAMENTE ");
				System.out.println(in);
			} else {
				System.out.println("ERROR AL MODIFICAR");
			}
		} else {
			System.out.println("Inmueble no existe");
		}
	}
}
