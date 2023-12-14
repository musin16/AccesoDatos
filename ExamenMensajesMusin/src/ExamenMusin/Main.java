package ExamenMusin;

import java.util.Scanner;

public class Main {
	public static Modelo ad = new Modelo();
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Introduce tu numero de empleados: ");
		int numEmp = sc.nextInt();
		sc.nextLine();
		System.out.println("Dni de empleados: ");
		String dni = sc.nextLine();
		logueado(numEmp, dni);

	}

	private static void logueado(int numEmp, String dni) {
		int codicion = ad.getEmpleado(numEmp, dni);
		switch (codicion) {
		case 0:
			System.out.println("El empleado no existe o la contrasenia es incorrecta");
			break;
		case 1:
			System.out.println("El empleado dentro");
			menuEmpleado();
			break;
		case 2:
			System.out.println("El empleado ha sido registrado");
			break;
		}

	}

	private static void menuEmpleado() {
		int op = 0;
		do {
			System.out.println("1. Enviar mensaje\r\n" + "2. Leer mensajes\r\n" + "3. Estadística mensajes");
			op = sc.nextInt();
			sc.nextLine();
			switch (op) {
			case 1:
				enviarMensaje();
				break;
			case 2:
				leerMensaje();
				break;
			case 3:
				estadisticasMensajes();
			default:
				break;
			}
		} while (op != 0);
		System.out.println("Elige una opcion: ");

	}

	private static void estadisticasMensajes() {

	}

	private static void leerMensaje() {

	}

	private static void enviarMensaje() {

	}
}