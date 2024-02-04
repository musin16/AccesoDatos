package ExamenUD5Herencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.print.DocFlavor.READER;

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
				System.out.println("1-");
				System.out.println("2-");
				System.out.println("3-");
				System.out.println("4-");
				System.out.println("5-");
				opcion = sc.nextInt();
				sc.nextLine();
				switch (opcion) {
				case 1:
//					crear();
					break;
				case 2:
//					modificar();
					break;
				case 3:
//					crearConsulta();
					break;
				case 4:
//					consultar();
					break;
				case 5:
//					borrar();
					break;
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}
}