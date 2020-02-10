package com.gestor.clases;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;

public class Consola {
	private static Scanner sc = new Scanner(System.in);
	private static EntityManagerFactory enf;

	Consola(EntityManagerFactory enf) {
		this.enf = enf;
	}

	public void mostrarConsola() {
		int opcion;
		int respuesta = 9;
		while (respuesta == 9) {
			System.out.println("Seleccione el apartado que desea administrar.\n1: Alumnos. \n2: Monitores");
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
			}
		}
		System.out.println("Programa finalizado.");

	}

}
