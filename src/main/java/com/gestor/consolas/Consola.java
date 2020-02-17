
package com.gestor.consolas;

import java.util.Scanner;
import javax.persistence.EntityManagerFactory;


public class Consola {
	private static Scanner sc = new Scanner(System.in);
	private static EntityManagerFactory enf;

	public Consola(EntityManagerFactory enf) {
		Consola.enf = enf;
	}

	public void mostrarConsola() {
		int opcion;
		int respuesta = 9;
		while (respuesta == 9) {
			System.out.println("Seleccione el apartado que desea administrar.\n"
					+ "1: Alumnos. \n"
					+ "2: Monitores. \n"
					+ "3: Lista de espera (No hay nada hecho)\n"					
					+ "4: Alergias (algoS hecho) \n"
					+ "5: Vacantes"
					);
			opcion = sc.nextInt();
			
			switch (opcion) {
			case 1:
				ConsolaAlumno ca = new ConsolaAlumno(enf);
				respuesta = ca.mostrarConsolaAlumno();// Devuelve 0 para finalizar el programa o 9 para volver a esta
														// consola.
				break;
			case 2:
				ConsolaMonitor cm = new ConsolaMonitor(enf);
				respuesta = cm.mostrarConsolaMonitor();
				break;
			default:
				System.out.println("solo 1 y 2 y 6");
				break;
			case 3:
				ConsolaListaDeEspera clde = new ConsolaListaDeEspera(enf);
				respuesta = clde.mostrarConsolaLista();
				break;
			case 4:
				ConsolaAlergias cAl = new ConsolaAlergias(enf);
				respuesta = cAl.mostrarConsolaLista();
				break;
			case 5:
				ConsolaVacantes cv = new ConsolaVacantes(enf);
				respuesta = cv.mostrarConsolaVacante();
				break;
			}
		}
		System.out.println("Programa finalizado.");
	
}
}

