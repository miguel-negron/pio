package com.gestor.consolas;

import java.time.LocalDate;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ConsolaListaDeEspera {
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public ConsolaListaDeEspera(EntityManagerFactory enf) {
		ConsolaListaDeEspera.emf = enf;
	}
	
	//Metodos
	public int mostrarConsolaAlumno() {
		Scanner sc = new Scanner(System.in);
		int respuesta;

		respuesta = 10;
		while (respuesta != 0 && respuesta != 9) {
			System.out.println(
					"\n\n\n"
					+ "1: Dar de alta a un alumno. \n"
					+ "2: Dar de baja a un alumno. \n"
					+ "3: Mostrar todos los alumnos. \n"
					+ "4: Modificar los datos de un alumno \n"
					+ "5: Mostrar alumnos por curso elegido. \n"
					+ "6: Busqueda de alumno por DNI. \n"
					+ "7: Volver atras \n"
					+ "0: Finalizar el programa"
					+ "\n\n\n");
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				//finalizarPrograma(respuesta);
				break;
			case 1:
				//altaAlumno();
				break;
			case 2:
				//bajaAlumno();
				break;
			case 3:
				//mostrarAlumnos();
				break;
			case 4:
				//modificarAlumno();
				break;
			case 5:
				//mostrarAlumnosPorCurso();
				break;
			case 6:
				//buscarAlumnoPorDni();
				break;
			case 7:
				//retroceder(respuesta);
			}
		}
		sc.close();
		return respuesta;
	}
	/*
	public void insertarAlumno(Scanner sc) {
		String dniAlumno;
		String nombreAlumno;
		String dniTutor;
		
		manager = emf.createEntityManager();
		System.out.println("Insertamos un alumno por consola.");
		System.out.println("Introduzca su DNI.");
		dniAlumno = sc.next();
		System.out.println(dniAlumno);
		sc.nextLine();
		System.out.println("Introduzca su nombre.");
		nombreAlumno = sc.nextLine();
		System.out.println("Introduzca el dni de su tutor.");
		dniTutor = sc.nextLine();
		// Tenemos que comprobar si existe el tutor en nuestra base de datos
		Tutor tutor2 = manager.find(Tutor.class, dniTutor);
		// Si existe anyadimos el alumno a la database y si no existe le pedimos que
		// seleccione un tutor existente
		if (tutor2 == null) {
			System.out.println("No hemos encontrado a ningun tutor con ese DNI, por favor eliga un tutor existente.");
		} else {
			alumnoTemporal = new Alumno(dniAlumno, nombreAlumno, "", "", LocalDate.now(), tutor2, true, true);
			try {
				manager.getTransaction().begin();
				manager.persist(alumnoTemporal);
				manager.getTransaction().commit();
			} catch (RollbackException e) {
				System.out.println("No se pueden dar de alta alumnos con el mismo Dni.");
			}
		}
		manager.close();
	}
	}
*/
}
