package com.gestor.consolas;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.gestor.clases.Vacantes;
import com.gestor.enums.NombreCurso;

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
					"\n\n\n1: Mostrar cursos.\n2: Modificar limite de alumnos en un curso.\n3: Volver atras."
							+ "\n0: Finalizar el programa" + "\n\n\n");
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				respuesta = finalizarPrograma();
				break;
			case 1: 
				mostrarCursos();
				break;
			case 2:
				modificarCursos();
				break;
			case 3:
				respuesta = retroceder();
				break;
			}
		}
		return respuesta;
	}
	
	public void modificarCursos() {
		manager = enf.createEntityManager();
		int curso;
		int limiteMaximo;
		Vacantes vacante;
		System.out.println("Elige el curso a modificar: \n1: Colonia. \n2: Manada. \n3: Tropa. \n4: Esculta. \n5: Clan");
		curso = sc.nextInt();
		NombreCurso c = NombreCurso.values()[curso-1];
		vacante = manager.find(Vacantes.class, c);
		System.out.println("Escribe el nuevo limite maximo.");
		sc.nextLine();
		limiteMaximo = sc.nextInt();
		if(limiteMaximo < vacante.getPlazas()) {
			//Hemos puesto un limite menor al numero de alumnos inscritos, asi que pedimos un limite igual o mayor
			System.out.println("El limite escrito es menor que el numero de alumnos inscritos. Por favor, escriba un numero igual o mayor que "  + vacante.getLimite());
		} else {
			vacante.setLimite(limiteMaximo);
			manager.getTransaction().begin();
			manager.merge(vacante);
			manager.getTransaction().commit();
			System.out.println("Curso modificado");
		}
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
