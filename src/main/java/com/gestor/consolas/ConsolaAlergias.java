package com.gestor.consolas;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.gestor.clases.Alergia;
import com.gestor.clases.Alumno;
import com.gestor.enums.Alergeno;

public class ConsolaAlergias {

	private static EntityManager manager;
	private static EntityManagerFactory emf;
	Alergeno[] alergenos = Alergeno.values();

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
				// finalizarPrograma(respuesta);
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
				// retroceder(respuesta);
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
				if (!alumno.getAlergias().contains(manager.find(Alergia.class, alergenos[i]))) {
					System.out.println("\t" + (i + 1) + ". " + alergenos[i]);
				}
			}
			System.out.println("\t" + (alergenos.length + 1) + ". Finalizar");

			selecAlergia = sc.nextInt() - 1;
			// System.out.println(selecAlergia + "sel -- len " + alergenos.length);
			if (selecAlergia >= 0 && selecAlergia < alergenos.length) {
				alumno.addAlergia(manager.find(Alergia.class, alergenos[selecAlergia]));
			} else if (selecAlergia == (alergenos.length + 1)) {

			} else {
				System.out.println("introduzca un numero valido");
			}
			// De algun modo sale del while bien pero siguen sin salirme las cuentas
		} while (selecAlergia != alergenos.length);

		
		manager.persist(alumno);
		manager.getTransaction().commit();

		manager.close();

	}

	public void showAlergias() {
		manager = emf.createEntityManager();
		Alergia alergia;

		for (int i = 0; i < alergenos.length; i++) {
			alergia = manager.find(Alergia.class, alergenos[i]);

			System.out.println(alergia.getAlumnosAlergicos());
			for (Alumno al : alergia.getAlumnosAlergicos()) {
				System.out.println(
						"Dni: " + al.getDNI() + " -- Nombre: " + al.getNombre() + " -- Rama: " + al.getCurso());
			}
		}

		manager.close();
	}

}// ConsolaAlergias
