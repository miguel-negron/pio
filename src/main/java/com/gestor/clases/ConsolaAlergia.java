package com.gestor.clases;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ConsolaAlergia {
	private static EntityManager manager;
	private static EntityManagerFactory enf;
	private static Scanner sc = new Scanner(System.in);
	private static int respuesta;
	
	ConsolaAlergia(EntityManagerFactory enf) {
		this.enf = enf;
	}
	
	public int mostrarConsolaAlergia() {
		respuesta = 10;
		while (respuesta != 0 && respuesta != 9) {
			System.out.println(
					"1: Mostrar alergias por alumno y curso. \n2: Mostrar alergias de un alumno espec�fico. \n3: A�adir alergia a un alumno espec�fico. \n4: Eliminar alergia de un alumno espec�fico.\n5: Volver atr�s.\n0: Finalizar el programa");
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				finalizarPrograma();
				break;
			case 1:
				mostrarTodasAlergias();
				break;
			case 2:
				buscarAlergiasPorAlumno();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				retroceder();
				break;

			
			}
		}
		return respuesta;
	}
	
	public static void mostrarTodasAlergias() {
		
	}
	
	public static void buscarAlergiasPorAlumno() {
		
	}
	
	// Funci�n para salir del programa
	public static void finalizarPrograma() {
		respuesta = 0;
	}

	// Funci�n retroceder
	// Nos permite volver al anterior men�
	public static void retroceder() {
		respuesta = 9;
	}
}
