package fBinario;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);

	// Declaramos el objeto modelo que accede a los datos
	public static Modelo ad = new Modelo("asignaturas.bin");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta Asignatura");
			System.out.println("2-Mostrar Asignaturas");
			System.out.println("3-Baja Asignatura");
			System.out.println("4-Borrar Asignatura");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1:
				altaAsignatura();
				break;
			case 2:
				mostrarAsignatura();
				break;
			case 3:
				bajaAsignatura();
				break;
			case 4:
				borrarAsignatura();
				break;
			}

		} while (opcion != 0);
	}

	private static void borrarAsignatura() {
		boolean validar=ad.borrarAsigPorId(pedirid());
	}
	private static int pedirid() {
		System.out.print("Introduce el id de la asignatura que quieres dar de baja:");
		int id2=t.nextInt();t.nextLine();
		return id2;
	}
	private static void bajaAsignatura() {
		boolean validar=ad.darBajaAsignatura(pedirid());		
		if(!validar) {
			System.out.println("Nose ha encontrado el alumno");
		}else {
			System.out.println("Se ha dado de baja correctamente");
		}
	
		
	}

	private static void mostrarAsignatura() {
		ArrayList<Asignatura> as = ad.obtenerAsignatura();
		for (Asignatura a : as) {
			System.out.println(a.toString());
		}
	}

	private static void altaAsignatura() {
		// TODO Auto-generated method stub
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			Asignatura as = new Asignatura();
			System.out.println("Nombre:");
			as.setNombre(t.nextLine());
			System.out.println("fechaRD (dd/mm/yyyy):");
			as.setFechaRD(formatoFecha.parse(t.nextLine()));
			System.out.println("Créditos:");
			as.setCreditos(t.nextFloat());
			t.nextLine();
			as.setActiva(true);
			as.setId(ad.obtenerId());
			if (ad.crearAsigna(as)) {
				System.out.println("Asignatura creada");
			} else {
				System.out.println("Error,asignarura no creada");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, formato de fecha incorrecto");
		}

	}

}
