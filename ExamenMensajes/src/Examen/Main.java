package Examen;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static Modelo ad = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-E1");
			System.out.println("2-E2");
			System.out.println("3-E3");
			System.out.println("4-E4");
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				ejer1();
				break;
			case 2:
				ejer2();
				break;
			case 3:
				ejer3();
				break;
			case 4:
				ejer4();
				break;
			}

		} while (opcion != 0);
	}

	private static void ejer4() {
		mostrarEmpleados();
		System.out.println("Introduce un id de empleado: ");
		int id = sc.nextInt();
		Empleados e = ad.obtenerEmpleado(id);
		if (e != null) {
			Chat c = new Chat();
			ArrayList<Mensaje> m = ad.obtenerMensajes(id);
			ArrayList<MensajesXML> m2 = new ArrayList();
			c.setIdEmpleado(e.getIdEmpleado());
			c.setNombre(e.getNombre());
			c.setTotal(m.size());
			for (int i = 0; i < m.size(); i++) {
				m2.add(new MensajesXML(m.get(i).getFecha(), i+1, m.get(i).getMensaje(), m.get(i).isActivo()));
			}
			c.setMensaje(m2);
			if (ad.marshall(c)) {

			} else {
			}
		} else {
			System.out.println("No existe el empleado ");
		}

	}

	private static void ejer3() {
		mostrarEmpleados();
		System.out.println("Introduce un id de empleado: ");
		int id = sc.nextInt();
		Empleados e = ad.obtenerEmpleado(id);
		if (e != null) {
			if (ad.borrarMensajes(id)) {
				System.out.println("Todos los mensajes han sido borrados");
				ArrayList<Mensaje> todoMensaje = ad.obtenerTodosMensajes();
				mostrarMensajes(todoMensaje);
			} else {
			}
		} else {
			System.out.println("No existe el empleado ");
		}
	}

	private static void ejer2() {
		mostrarEmpleados();
		System.out.println("Introduce un id de empleado: ");
		int id = sc.nextInt();
		Empleados e = ad.obtenerEmpleado(id);
		if (e != null) {
			// marcar como true los mensajes (leido)
			if (ad.marcarLeidoMensaje(id)) {
				ArrayList<Mensaje> mr = ad.obtenerMensajes(id);
				mostrarMensajes(mr);
			} else {
				System.out.println("Mensajes no se han leido");
			}
		} else {
			System.out.println("No existe el empleado ");
		}
	}

	private static void mostrarMensajes(ArrayList<Mensaje> mr) {
		for (Mensaje mensaje : mr) {
			System.out.println(mensaje);
		}

	}

	private static void ejer1() {
		boolean tipo = mostrarEmpleados();
		if (tipo) {
			System.out.println("Introduce un id de empleado: ");
			int id = sc.nextInt();
			sc.nextLine();
			Empleados e = ad.obtenerEmpleado(id);
			if (e != null) {
				if (e.isActivo()) {
					Mensaje m = new Mensaje();
					System.out.println("Introduce el mensaje:");
					String mensaje = sc.nextLine();
					m.setActivo(false);
					StringBuffer br = new StringBuffer(e.getNombre());
					br.setLength(100);
					m.setNombre(br.toString());
					br = new StringBuffer(mensaje);
					br.setLength(200);
					m.setMensaje(br.toString());
					m.setFecha(new Date());
					m.setIdEmpleado(e.getIdEmpleado());
					if (ad.escribirMensaje(m)) {
						System.out.println("Se ha añadido el mensaje correctamente");
					} else {
						System.out.println("Error,en el envio del mensaje");
					}
				} else {
					System.out.println("El empleado no esta activo");
				}
			} else {
				System.out.println("No existe el empleado ");
			}
		}

	}

	private static boolean mostrarEmpleados() {
		boolean opcion = false;
		ArrayList<Empleados> lisEmp = ad.obtenerEmpleados();
		for (Empleados empleados : lisEmp) {
			System.out.println(empleados);
			opcion = true;
		}
		return opcion;

	}
}
