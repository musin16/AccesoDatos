package Biblioteca;

import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (bd.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Selecciona una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Partido");
				System.out.println("2-Mostrar datos partido");
				System.out.println("3-Registrar datos partido");
				System.out.println("4-Borrar partido");
				opcion = t.nextInt();
				t.nextLine();

				switch (opcion) {
				case 1:
					crearPrestamo();
					break;
				case 2:
//					mostrarPartido();
					break;
				case 3:
//					registrarResutlados();
					break;
				case 4:
//					borrarPArtido();
					break;
				}

			} while (opcion != 0);
			bd.cerrar();
		} else {
			System.out.println("Error no hay conexión");
		}
	}

	private static void crearPrestamo() {

	}
}
