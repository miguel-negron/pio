package com.gestor.clases;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

public class ConsolaMonitor {
	
	private static int respuesta;
	private static EntityManagerFactory enf;
	private static Scanner sc = new Scanner(System.in);
	
	public ConsolaMonitor(EntityManagerFactory enf){
		ConsolaMonitor.enf = enf;
	}
	
	public int mostrarConsolaMonitor() {
		respuesta = 10;
		while (respuesta != 0  && respuesta != 9) {
			System.out.println(
					"1: Dar de alta a un alumno. \n"
					+ "2: Dar de baja a un alumno. \n"
					+ "3: Mostrar todos los alumnos. \n"
					+ "4: Modificar los datos de un alumno \n"
					+ "5: Mostrar alumnos por curso elegido. \n"
					+ "6: Basqueda de alumno por DNI. \n"
					+ "7: Volver atras. \n"
					+ "0: Finalizar el programa"
					);
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				finalizarPrograma();
				break;

			case 7:
				retroceder();
			}
		}
		return respuesta;
	}
	
	
	// Funcion para salir del programa
	public static void finalizarPrograma() {
		respuesta = 0;
	}
	
	//Funcion retroceder
	//Nos permite volver al anterior menu
	public static void retroceder() {
		respuesta = 9;
	}

}
