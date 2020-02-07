package com.gestor.clases;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

public class ConsolaMonitor {
	
	private static int respuesta;
	private static EntityManagerFactory enf;
	private static Scanner sc = new Scanner(System.in);
	
	ConsolaMonitor(EntityManagerFactory enf){
		this.enf = enf;
	}
	
	public int mostrarConsolaMonitor() {
		respuesta = 10;
		while (respuesta != 0  && respuesta != 9) {
			System.out.println(
					"1: Dar de alta a un alumno. \n2: Dar de baja a un alumno. \n3: Mostrar todos los alumnos. \n4: Modificar los datos de un alumno \n5: Mostrar alumnos por curso elegido. \n6: B�squeda de alumno por DNI. \n7: Volver atr�s."
					+ "\n0: Finalizar el programa");
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
	
	
	// Funci�n para salir del programa
	public static void finalizarPrograma() {
		respuesta = 0;
	}
	
	//Funci�n retroceder
	//Nos permite volver al anterior men�
	public static void retroceder() {
		respuesta = 9;
	}

}
