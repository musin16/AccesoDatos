package teatro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Principal {

	public static Scanner sc = new Scanner(System.in);
	public static Modelo ad = new Modelo();
	public static SimpleDateFormat formato = new SimpleDateFormat("ddMMyyHHmm");

	public static void main(String[] args) {
		if (ad.getConexion() != null) {
			int opcion = 0;
			do {
				System.out.println("Menu:");
				System.out.println("1. Crear espectáculo");
				System.out.println("2. Modificar precio de espectáculo");
				System.out.println("3. Vender entrada");
				System.out.println("4. Salir");
				System.out.print("Seleccione una opción: ");
				opcion = sc.nextInt();
				sc.nextLine();
				switch (opcion) {
				case 1:
					crearEspectaculo();
					break;
				case 2:
					modificarPrecio();
					break;
				case 3:
					venderEntrada();
					break;
				case 4:
					System.out.println("Saliendo del programa...");
					return;
				default:
					System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
				}
			} while (opcion != 0);
		} else {
			System.out.println("Error de conexión");
		}
	}

	private static void venderEntrada() {
	/*  Se mostrarán los espectáculos y se pedirá el id del espectáculo. Se debe comprobar que el id introducido
		existe.
		Se pedirá por teclado la fila y la columna de la butaca que se va a vender.
		
		El precio de la butaca será el último precio que tiene definido el espectáculo en su array de precio.
		
		Una vez registrada la entrada, se mostrarán todas las entradas vendidas para ese espectáculo.
		*/
		mostrarEspectaculos();
		
	}

	private static void modificarPrecio() {
		/*
		 * Se mostrarán los espectáculos y se pedirá el id del espectáculo al que se va
		 * a modificar el precio. Se debe comprobar que el id introducido existe. La
		 * modificación del precio consiste en añadir un precio al espectáculo. Por
		 * tanto, se pedirá el nuevo precio por teclado y se añadirá al array de precios
		 * del espectáculo. Una vez modificado, se mostrará dicho espectáculo.
		 */
		mostrarEspectaculos();
		System.out.println("Inroduce el numero: ");
		int num = sc.nextInt();
		sc.nextLine();
		Espectaculo es = ad.obtenerEspectaculo(num);
		if (es != null) {
			if (es.getPrecios().size() == 0) {
				es.setPrecios(new ArrayList());
			} else {
				System.out.println("precio inicial");
				es.getPrecios().add(sc.nextDouble());
				sc.nextLine();
				if(ad.modificarEspectaculo(es)) {
					System.out.println("Se ha modificado correctamnente");
					System.out.println(es);
				}else
					System.out.println("Error al modificar");
			}
		} else {
			System.out.println("Error no existe");
		}
	}

	private static void crearEspectaculo() {
//		Se pedirá el título del espectáculo a crear. Se comprobará que no existe otro espectáculo con el mismo
//		nombre y se informará del error en caso de que sí lo haya.
//		Se pedirá el precio inicial que van a tener las entradas que se vendan para ese espectáculo.
//		Una vez creado el espectáculo, se mostrarán los datos de todos los espectáculos existentes, mostrando
//		primero los que se han creado más recientemente. Se deben mostrar todos los datos de cada espectáculo,
//		incluidos los precios que tiene.
		System.out.println("Intorduce el TITULO DEL ESPECTACULO: ");
		String title = sc.nextLine();
		Espectaculo es = ad.obtenerEspectaculo(title);
		if (es == null) {
			es = new Espectaculo();
			es.setTitulo(title);
			System.out.println("precio inicial");
			es.setPrecios(new ArrayList());
			es.getPrecios().add(sc.nextDouble());
			sc.nextLine();
			if (ad.crearEspectaculo(es)) {
				System.out.println("Espectaculo creado correcvtame");
				mostrarEspectaculos();
			} else {
				System.out.println("Error al introducir especat");
			}
		} else {
			System.out.println("YA EXISTE ESPECTACULO");
		}
	}

	private static void mostrarEspectaculos() {
		ArrayList<Espectaculo> espec = ad.obtenerEspectaculos();
		for (Espectaculo te : espec) {
			System.out.println(te);
		}

	}

}
