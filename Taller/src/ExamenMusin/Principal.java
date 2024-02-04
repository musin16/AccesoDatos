package ExamenMusin;

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
				System.out.println("1-Crear Revisión");
				System.out.println("2-Cerrar Revision");
				System.out.println("3-Mostrar consulta");
				System.out.println("4-Borrar Vehiculo");
				System.out.println("5-Borrar Paciente/Médico");
				opcion = sc.nextInt();
				sc.nextLine();
				switch (opcion) {
				case 1:
					crearRevision();
					break;
				case 2:
					cerrarRevision();
					break;
				case 3:
					mostrarRevisionVehiculo();
					break;
				case 4:
					borrarVehiculo();
					break;
				case 5:

					break;
				}

			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}

	private static void borrarVehiculo() {
		mostrarVehiculos();
		System.out.println("Introduce la matricula del vehiculo: ");
		String matri = sc.nextLine();
		Vehiculo veh = bd.obtenerVehiculo(matri);
		if (veh != null) {
			ArrayList<Revision> revisiones = bd.obtenerRevision(veh.getMatricula());
			if (!revisiones.isEmpty()) {
				System.out.println("¿Quieres borrar vehiculo?(si/no)");
				if (sc.nextLine().equalsIgnoreCase("Si")) {
					if (bd.borrarVehiculo(veh, revisiones)) {
						System.out.println("Se ha borrado correctamente");
					} else {
						System.out.println("Error al intentar borrar el vehiculo");
					}
				}
				System.out.println(revisiones.toString());
			} else {
				if (bd.borrarVehiculo(veh)) {
					System.out.println("Se ha borrado correctamente");
				} else {
					System.out.println("Error al borrar el vehiculo");
				}
			}
		} else {
			System.out.println("El vehiculo no existe");
		}
	}

	private static void mostrarRevisionVehiculo() {
		mostrarVehiculos();
		System.out.println("Introduce la matricula del vehiculo: ");
		String matri = sc.nextLine();
		Vehiculo veh = bd.obtenerVehiculo(matri);
		if (veh != null) {
			ArrayList<Revision> rev = bd.obtenerRevision(veh.getMatricula());
			if (rev != null) {
				System.out.println(rev.toString());
			}
		} else {
			System.out.println("El vehiculo no existe");
		}
	}

	private static void cerrarRevision() {
		mostrarRevisiones();
		System.out.println("Introduce el codigo de revision: ");
		int id = sc.nextInt();
		sc.nextLine();
		Revision revision = bd.obtenerRevision(id);
		if (revision != null) {
			while (true) {
				System.out.println("Introduce las actuaciones: ");
				revision.getActuaciones().add(sc.nextLine());
				System.out.println("¿Quieres seguir añadiendo actuaciones?(1)=>Si o 2=>No");
				if (!sc.nextLine().equalsIgnoreCase("1")) {
					break;
				}
			}
			System.out.println("Introduce el numeor de horas que duro la revision: ");
			int horas = sc.nextInt();
			sc.nextLine();
			revision.setHoras(horas);
			if (bd.modificarRevision(revision)) {
				Revision reviModificada = bd.obtenerRevision(id);
				System.out.println(reviModificada.toString());
				System.out.println("----***----***----***");
				System.out.println("La revision se ha modificado correctamete");
			} else {
				System.out.println("Error al modificar la revision");
			}

		} else {
			System.out.println("Revision no existe");
		}
	}

	private static void mostrarRevisiones() {
		ArrayList<Revision> revisiones = bd.obtenerRevisiones();
		for (Revision revision : revisiones) {
			System.out.println("Id: " + revision.getId() + " Matrícula : " + revision.getVehiculo() + " Fecha: "
					+ formato.format(revision.getFecha()));
		}

	}

	private static void crearRevision() {
//		1. Crear revisión: Mostrará los vehículos y pedirá la matrícula del vehículo; 
//		se comprobará que existe el vehículo introducido. 
//		La fecha de la revisión será la fecha del día, el número de horas
//		inicialmente es 0 y la lista de actuaciones se crea vacía.
		mostrarVehiculos();
		System.out.println("Introduce el el id del vehiculo: ");
		String id = sc.nextLine();
		Vehiculo veh = bd.obtenerVehiculo(id);
		if (veh != null) {
			Revision revision = new Revision();
			revision.setFecha(new Date());
			revision.setActuaciones(new ArrayList<String>());
			revision.setVehiculo(veh.getMatricula());
			if (bd.crearRevision(revision)) {
				System.out.println("Revision creada correctamente");
			} else {
				System.out.println("Error al crear la revision");
			}
		} else {
			System.out.println("No existe el vehiculo");
		}
	}

	private static void mostrarVehiculos() {
		ArrayList<Vehiculo> vehiculos = bd.obtenerVehiculos();
		for (Vehiculo vehiculo : vehiculos) {
			System.out.println(vehiculo);
		}
	}

}
