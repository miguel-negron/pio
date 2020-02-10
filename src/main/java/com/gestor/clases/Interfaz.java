package com.gestor.clases;


import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.h2.engine.SysProperties;

import com.gestor.clases.*;

public class Interfaz {
	
	private static EntityManager manager;
	private static EntityManagerFactory enf = Persistence.createEntityManagerFactory("Persistencia");

	public void menuAlumno() {
		
		System.out.println("\tOpciones de la tabla alumno:\n");
		System.out.println("\t1. Dar de alta un alumno.\n");
		System.out.println("\t2. Dar de baja un alumno.\n");
		System.out.println("\t3. Modificar datos del alumno.\n");
		System.out.println("\t4. Ficha de alumno (Por DNI).\n");
		System.out.println("\t5. Listar alumnos por curso.\n");
		System.out.println("\t6. Lista completa de alumnos.");

		Scanner teclado = new Scanner(System.in);
		int key = teclado.nextInt();
		switch (key) {
		case 1:
			instertarAlumno();

			break;
		case 2:
			borrarAlumno();

			break;
		case 3:
			modificarAlumno();

			break;
		case 4:
			listarAlumnosDNI();

			break;
		case 5:
			listarAlumnosCurso();

			break;
		case 6:
			listarTodos();

			break;

		}
	}

	public void instertarAlumno() {

	}

	public void borrarAlumno() {
		manager = enf.createEntityManager();
		List<Alumno> lista = null;
		System.out.println("INTRODUCE EL DNI:");
		Scanner dni = new Scanner(System.in);
		lista.add((Alumno)manager.createQuery("FROM Alumno WHERE DNI = " + dni).getSingleResult());
		System.out.println(lista.isEmpty());
		

	}

	public void modificarAlumno() {

	}

	public void listarAlumnosDNI() {

	}
	public void listarAlumnosCurso() {
		
	}
	public void listarTodos() {
		
	}

}
