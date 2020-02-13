package com.gestor.consolas;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.gestor.clases.Vacantes;

public class ConsolaVacantes {
	private static EntityManager manager;
	private static EntityManagerFactory enf;
	private static Scanner sc = new Scanner(System.in);
	
	ConsolaVacantes(EntityManagerFactory enf) {
		this.enf = enf;
	}
	
	public int mostrarConsolaVacante() {
		int respuesta;
		respuesta = 10;
		while (respuesta != 0 && respuesta != 9) {
			System.out.println(
					"1: Mostrar cursos con sus vacantes.\n3: Volver atras."
							+ "\n0: Finalizar el programa");
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				respuesta = finalizarPrograma();
				break;
			case 1: 
				mostrarCursos();
				break;
			case 3:
				respuesta = retroceder();
				break;
			}
		}
		return respuesta;
	}
	
	public void mostrarCursos() {
		manager = enf.createEntityManager();
		List<Vacantes> listaCursos;
		listaCursos = manager.createQuery("FROM Vacantes").getResultList();
		for(Vacantes v: listaCursos) {
			System.out.println(v);
		}
	}
	
	// Funcion para salir del programa
	public static int finalizarPrograma() {
		int respuesta = 0;
		return respuesta;
	}

	// Funcion retroceder
	// Nos permite volver al anterior menu
	public static int retroceder() {
		int respuesta = 9;
		return respuesta;
	}
}
