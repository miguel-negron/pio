package com.gestor.consolas;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.gestor.clases.Alumno;
import com.gestor.enums.Alergia;

public class ConsolaAlergias {

	private static EntityManager manager;
	private static EntityManagerFactory emf;
	Alergia[] alergenos = Alergia.values();

	public ConsolaAlergias(EntityManagerFactory enf) {
		ConsolaAlergias.emf = enf;
	}

	// Metodos
	public int mostrarConsolaLista() {
		Scanner sc = new Scanner(System.in);
		int respuesta;

		respuesta = 10;
		while (respuesta != 0 && respuesta != 9) {
			System.out.println("\n\n\n  - CONSOLA ALERGIAS - \n" + "1: Anyadir alergia por dni. \n"
					+ "2: Ver todas las alergias. \n" + "3: NoNoNoNo. \n" + "0: Finalizar el programa" + "\n\n\n");
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				respuesta = finalizarPrograma();
				break;
			case 1:
				anyadirAlergiaPorId(sc);
				break;
			case 2:
				showAlergias();
				break;
			case 3:
				// mostrarAlumnosLista();
				break;

			case 7:
				respuesta = retroceder();
			}
		}
		return respuesta;
	}// Mostrar consola

	public Alumno getAlumnoById(String dniAlumno) {
		Alumno alumno;

		//manager = emf.createEntityManager();

		alumno = manager.find(Alumno.class, dniAlumno);

		// manager.close();

		return alumno;
	}

	
	public void anyadirAlergiaPorId(Scanner sc) {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		
		int selecAlergia = 0;
		Alumno alumno;

		System.out.println("Introduzca el DNI del alumno que desea buscar:");
		String dniAlumno = sc.next();
		alumno = getAlumnoById(dniAlumno);
		System.out.println("Anyadiendo alergia a " + alumno.getNombre() + " con DNI: " + alumno.getDNI());

		if (alumno == null)
			return;

		do {
			System.out.println("\nSeleccione la alergia a anyadir:");

			// Ciclar por las alergias que no tiene el alumno
			for (int i = 0; i < alergenos.length; i++) {
				if (!alumno.getAlergias().contains(alergenos[i])) {
					System.out.println("\t" + (i + 1) + ". " + alergenos[i]);
				}
			}
			System.out.println("\t" + (alergenos.length + 1) + ". Finalizar");

			selecAlergia = sc.nextInt() - 1;
			
			// System.out.println(selecAlergia + "sel -- len " + alergenos.length);
			if (selecAlergia >= 0 && selecAlergia < alergenos.length) {
				alumno.addAlergia(alergenos[selecAlergia]);
			} else if (selecAlergia == (alergenos.length + 1)) {

			} else {
				System.out.println("introduzca un numero valido");
			}
			// De algun modo sale del while bien pero siguen sin salirme las cuentas
		} while (selecAlergia != alergenos.length);

		
		manager.flush();
		manager.getTransaction().commit();

		manager.close();

	}

	public void showAlergias() {
		manager = emf.createEntityManager();
		List<Alumno> todos;
		String hql = "FROM Alumno";
		todos = manager.createQuery(hql).getResultList();

		System.out.println(todos.size());
		for (int i = 0; i < alergenos.length; i++) {

			System.out.println(alergenos[i]);
			for (Alumno al : todos) {
				System.out.println(al.getAlergias().size());
				if (al.getAlergias().contains(alergenos[i])) {
					System.out.println(
							"Dni: " + al.getDNI() + " -- Nombre: " + al.getNombre() + " -- Rama: " + al.getCurso());

				}
			}
		}

		manager.close();
	}

	
	// Funcion para salir del programa
	public static int finalizarPrograma() {
		int respuesta = 0;
		return respuesta;
	}

	// Funcion retroceder
	// Nos permite volver al anterior men0
	public static int retroceder() {
		int respuesta = 9;
		return respuesta;
	}

}// ConsolaAlergias
