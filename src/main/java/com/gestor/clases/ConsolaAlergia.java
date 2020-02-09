package com.gestor.clases;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

public class ConsolaAlergia {
	private static EntityManager manager;
	private static EntityManagerFactory enf;
	private static Scanner sc = new Scanner(System.in);
	private static int respuesta;
	private static String DNIAlumno;
	private static String alergiaAlumno;

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
				insertarAlergia();
				break;
			case 4:
				eliminarAlergia();
				break;
			case 5:
				retroceder();
				break;

			}
		}
		return respuesta;
	}

	public static void mostrarTodasAlergias() {
		manager = enf.createEntityManager();
		List<Alergia> lista = manager.createQuery("FROM Alergia").getResultList();
		if (lista.isEmpty()) {
			System.out.println("�No hay alergias!");
		} else {
			for (Alergia al : lista) {
				System.out.println(al);
			}
		}
		manager.close();

	}

	public static void buscarAlergiasPorAlumno() {

	}

	public static void insertarAlergia() {
		manager = enf.createEntityManager();
		System.out.println("Introduzca el DNI del alumno que tiene la alergia:");
		DNIAlumno = sc.next();
		System.out.println("Introduzca la alergia:");
		sc.nextLine();
		alergiaAlumno = sc.next();
		Alumno alumno = manager.find(Alumno.class, DNIAlumno);
		//Alergia alergia = new Alergia(alergiaAlumno, DNIAlumno);
		// Falta comprobar si ya existe la clave primaria
		manager.getTransaction().begin();
		//manager.persist(alergia);
		manager.getTransaction().commit();
		System.out.println("Alergia introducida.");
		manager.close();
	}

	public static void eliminarAlergia() {

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
