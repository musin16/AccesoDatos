package taller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	public static Usuario u = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (bd.getConexion() != null) {
			System.out.println("Todo ok");
//			bd.cerrar();
		} else {
			System.out.println("Error, no hay conexión con la BD");
		}
	}

//	private static void login() {
//		// TODO Auto-generated method stub
//		System.out.println("Introduce usuario:");
//		String us = t.nextLine();
//		System.out.println("Introduce contraseña:");
//		String ps = t.nextLine();
//		u = bd.obtenerUsuario(us, ps);
//		if (u != null) {
//			// Cargar el menú correspondiente
//			switch (u.getPerfil()) {
//			case "A":
////				menuAdministrativo();
//				break;
//			case "M":
////				menuMecanico();
//				break;
//			case "C":
////				menuCliente();
//				break;
//			}
//		} else {
//			System.out.println("Error, usuario incorrecto");
//		}
//	}

}
