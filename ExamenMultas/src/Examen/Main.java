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
			}

		} while (opcion != 0);
	}

	private static void ejer1() {
		System.out.println("Introduce el fichero xml que quieres cargar: ");
		String xml = sc.nextLine();
		Radar r = ad.unmarshaller(xml);
		if (r != null) {
			int i = 0;
			for (Multas mult : r.getMultas()) {
				Matriculas mat = ad.obtenerMatricula(mult.getMatricula());
				Matriculas matOfi = new Matriculas();
				int cantMul = mult.getVelocidad() - r.getMaximo();
				int importeRec = 0;
				if (cantMul <= 10) {
					importeRec = 100;
				} else if (cantMul > 10 && cantMul <= 30) {
					importeRec = 300;
				} else if (cantMul > 30) {
					importeRec = 500;
				}

				if (mat != null) {
					matOfi.setImporteRecaudado(mat.getImporteRecaudado() + importeRec);
					matOfi.setNumMultas(mat.getNumMultas() + 1);
					/*
					 * long fecha1 = mat.getFecha(); long fecha2 = mult.getHora().getTime(); if
					 * (fecha1 > fecha2) { matOfi.setFecha(fecha1); } else {
					 */
					matOfi.setFecha(mat.getFecha());
					matOfi.setMatricula(mat.getMatricula());
					// }
					if (ad.modificarFichero(matOfi)) {
						System.out.println("Se ha modificado correctamente");
					} else {
						System.out.println("Error,al modificar la multas");
					}
				} else {
					mat = new Matriculas();
					mat.setFecha(new Date().getTime());
					mat.setImporteRecaudado(importeRec);
					mat.setNumMultas(1);
					mat.setMatricula(mult.getMatricula());
					if (ad.escribirFichero(mat)) {
						System.out.println("Se ha introducido correctamente");
					} else {
						System.out.println("Error,al introducir la matricula");
					}
				}
			}
			mostrarMultasBin();
		} else {
			System.out.println("No se ha podido obtener el objeto del fichero " + xml);
		}

	}

	private static void mostrarMultasBin() {
		ArrayList<Matriculas> m = ad.obtenerMatriculas();
		for (Matriculas matricul : m) {
			System.out.println(matricul);
		}

	}

	private static void ejer3() {
		ArrayList<RadarMovil> rmov = ad.obtenerMultasRadarMovil();
		if (!rmov.isEmpty()) {
			System.out.println("Introduce una matricula: ");
			String matricula = sc.nextLine();
			Matriculas mr = ad.obtenerMatricula(matricula);
			int cont = 0;
			int importeRec = 0;
			for (RadarMovil radarMovil : rmov) {
				if (radarMovil.getMatricula().equalsIgnoreCase(matricula)) {
					cont++;
					int cantMul = radarMovil.getVelocidadCaptada() - radarMovil.getKm();
					if (cantMul <= 10) {
						importeRec += 100;
					} else if (cantMul > 10 && cantMul <= 30) {
						importeRec += 300;
					} else if (cantMul > 30) {
						importeRec += 500;
					}
				}
			}
			System.out.println("Multas Vehiculo: " + matricula);
			if (mr != null) {
				System.out.print("Numero de multas en radares fijos : " + mr.getNumMultas() + "\t");
				System.out.println("Cantidad recaudada: " + mr.getImporteRecaudado());
			} else {
				System.out.print("Numero de multas en radares fijos : 0 \t");
				System.out.print("Cantidad recaudada: 0 \n");
			}
			System.out.print("Numero de multas en radares Moviles : " + cont + "\t");
			System.out.println("Cantidad recaudada: " + importeRec);
			System.out.println("Total : " + (importeRec + mr.getImporteRecaudado()));
		} else {
			System.out.println("No hay multas moviles");
		}
	}

	private static void ejer2() {
		System.out.println("Introduce una matricula: ");
		String mat = sc.nextLine();
		Matriculas m = ad.obtenerMatricula(mat);
		if (m != null) {
			if (ad.borrarMatricula(m)) {
				System.out.println("Matriculada borrada corectamente");
				mostrarMultasBin();
			} else {
				System.out.println("Error al intentar borrar la matricula");
			}
		} else {
			System.out.println("No existe dicho vehiculo amonestado");
		}
	}

}
