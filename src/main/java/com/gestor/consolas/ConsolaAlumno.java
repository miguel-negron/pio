package com.gestor.consolas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import org.hibernate.query.Query;

import com.gestor.clases.Alumno;
import com.gestor.clases.CursoAlumnos;
import com.gestor.clases.Tutor;
import com.gestor.enums.Curso;

public class ConsolaAlumno {
	private static EntityManager manager;
	private static EntityManagerFactory enf;

	private static Scanner sc = new Scanner(System.in);
	static List<Tutor> listaTutores;
	private static Alumno alumnoTemporal;

	public ConsolaAlumno(EntityManagerFactory enf) {
		ConsolaAlumno.enf = enf;
	}

	public int mostrarConsolaAlumno() {
		int respuesta;

		respuesta = 10;
		while (respuesta != 0 && respuesta != 9) {
			System.out.println("\n\n\n" + "1: Dar de alta a un alumno. \n" + "2: Dar de baja a un alumno. \n"
					+ "3: Mostrar todos los alumnos. \n" + "4: Modificar los datos de un alumno \n"
					+ "5: Mostrar alumnos por curso elegido. \n" + "6: Busqueda de alumno por DNI. \n"
					+ "7: Volver atras \n" + "0: Finalizar el programa" + "\n\n\n");
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				respuesta = finalizarPrograma();
				break;
			case 1:
				altaAlumno();
				break;
			case 2:
				bajaAlumno();
				break;
			case 3:
				mostrarAlumnos();
				break;
			case 4:
				modificarAlumno();
				break;
			case 5:
				mostrarAlumnosPorCurso();
				break;
			case 6:
				buscarAlumnoPorDni();
				break;
			case 7:
				respuesta = retroceder();
				break;
			}
		}
		return respuesta;
	}

	// Aqui empieza la funcion insertar
	public static void altaAlumno() {
		String dniAlumno;
		String nombreAlumno;
		String dniTutor;

		manager = enf.createEntityManager();
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
			Alumno alumnoRepetido = manager.find(Alumno.class, alumnoTemporal.getDNI());
			//Comprobamos que no exista ningun otro alumno con el mismo dni
			if(alumnoRepetido != null) {
				System.out.println("Ya existe otro alumno con el mismo DNI.");
			} else {
				manager.getTransaction().begin();
				manager.persist(alumnoTemporal);
				manager.getTransaction().commit();
			}

		}
		manager.close();
	}

	// Funcion eliminar
	public static void bajaAlumno() {
		manager = enf.createEntityManager();
		System.out.println("Escribe el dni del alumno que desea borrar.");
		String dniAlumno = sc.next();
		sc.nextLine();
		alumnoTemporal = manager.find(Alumno.class, dniAlumno);
		if (!(alumnoTemporal == null)) {
			System.out.println("Estas seguro que quieres eliminar a " + alumnoTemporal.getNombre() + " ?(S/N)");
			String confirmacion = sc.nextLine();
			if (confirmacion.toLowerCase().equals("s")) {
				// Borramos
				manager.getTransaction().begin();
				manager.merge(alumnoTemporal);
				manager.remove(alumnoTemporal);
				manager.getTransaction().commit();
				System.out.println("Alumno " + alumnoTemporal + " borrado");
			} else {
				System.out.println("Alumno no borrado.");
			}
		} else {
			System.out.println("Alumno no encontrado.");
		}
		manager.close();
	}

	public static void mostrarAlumnos() {
		manager = enf.createEntityManager();
		List<Alumno> lista = manager.createQuery("FROM Alumno").getResultList();

		if (lista.isEmpty()) {
			System.out.println("No hay alumnos!");
		} else {
			System.out.println("Hay " + lista.size() + " alumnos:");
			for (Alumno al : lista) {
				System.out.println(al);
			}
		}
		manager.close();
	}

	public static void modificarAlumno() {
		final Curso[] cursos = Curso.values();

		String seguirModificando;
		boolean bucle = true;
		boolean seguir = true;
		String entregado;
		int diaFechaNacimiento;
		int mesFechaNacimiento;
		int anyoFechaNacimiento;
		String nombreAlumno;
		String primerApellidoAlumno;
		String segundoApellidoAlumno;

		manager = enf.createEntityManager();
		System.out.println("Introduzca el DNI del alumno que desea modificar");
		String dniAlumno = sc.next();
		sc.nextLine();
		alumnoTemporal = manager.find(Alumno.class, dniAlumno);
		// Vemos si existe el alumno buscado
		if (alumnoTemporal == null) {
			System.out.println("No se ha encontrado el alumno introducido.");
		} else {
			System.out.println("Alumno encontrado!");
			while (seguir) {
				System.out.println("Alumno a modificar: " + alumnoTemporal);
				System.out.println(
						"Seleccione que desea modificar:\n0:Dni.\n1:Nombre.\n2:Primer apellido.\n3:Segundo apellido.\n4:Fecha de nacimiento.\n5:Entrega de la ficha.\n6:Entrega de la foto.\n7:Curso");
				int opcion = sc.nextInt();

				switch (opcion) {
				case 0:
					System.out.println("Escriba el nuevo Dni.");
					sc.nextLine();
					dniAlumno = sc.next();
					// Borramos el alumno y lo creamos de nuevo con el nuevo DNI (no se pueden
					// cambiar claves primarias)
					manager.getTransaction().begin();
					manager.merge(alumnoTemporal);
					manager.remove(alumnoTemporal);
					manager.getTransaction().commit();
					alumnoTemporal.setDNI(dniAlumno);
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Dni modificado.");
					break;
				case 1:
					System.out.println("Escriba el nuevo nombre.");
					sc.nextLine();
					nombreAlumno = sc.next();
					alumnoTemporal.setNombre(nombreAlumno);
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Nombre modificado.");
					break;
				case 2:
					System.out.println("Escriba el primer apellido.");
					sc.nextLine();
					primerApellidoAlumno = sc.next();
					alumnoTemporal.setApellido1(primerApellidoAlumno);
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Apellido modificado.");
					break;
				case 3:
					System.out.println("Escriba el segundo apellido.");
					sc.nextLine();
					segundoApellidoAlumno = sc.next();
					alumnoTemporal.setApellido2(segundoApellidoAlumno);
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Apellido modificado.");
					break;
				case 4:
					System.out.println("Escriba el dia.");
					sc.nextLine();
					diaFechaNacimiento = sc.nextInt();
					System.out.println("Escriba el mes.");
					sc.nextLine();
					mesFechaNacimiento = sc.nextInt();
					System.out.println("Escriba el anio.");
					sc.nextLine();
					anyoFechaNacimiento = sc.nextInt();
					alumnoTemporal
							.setFechaNac(LocalDate.of(anyoFechaNacimiento, mesFechaNacimiento, diaFechaNacimiento));
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Fecha de nacimiento modificada.");
					break;
				case 5:
					System.out.println("Tiene la ficha? S/N");
					while (bucle) {
						sc.nextLine();
						entregado = sc.next();
						if (entregado.toLowerCase().equals("s")) {
							alumnoTemporal.setFichaEntregada(true);
							bucle = false;
						} else if (entregado.toLowerCase().equals("n")) {
							alumnoTemporal.setFichaEntregada(false);
							bucle = false;
						} else {
							System.out.println("Entrada incorrecta, por favor escriba S o N.");

						}
					}
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Entrega modificada.");
					break;
				case 6:
					System.out.println("Tiene la foto? S/N");
					while (bucle) {
						sc.nextLine();
						entregado = sc.next();
						if (entregado.toLowerCase().equals("s")) {
							alumnoTemporal.setFotoEntregada(true);
							bucle = false;
						} else if (entregado.toLowerCase().equals("n")) {
							alumnoTemporal.setFotoEntregada(false);
							bucle = false;
						} else {
							System.out.println("Entrada incorrecta, por favor escriba S o N.");

						}
					}
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Entrega modificada.");
					break;
				case 7:
					System.out.println("Seleccione el nuevo curso.");

					for (int i = 0; i < cursos.length; i++) {
						System.out.println((i + 1) + ". " + cursos[i]);
					}
					sc.nextLine();
					int cursoSeleccionado = sc.nextInt();

					alumnoTemporal.setCursoAlumnos(manager.find(CursoAlumnos.class, cursos[cursoSeleccionado - 1]));
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Curso modificado.");

				}
				System.out.println("Quieres seguir modificando este Alumno? s/n");
				seguirModificando = sc.next();
				if (seguirModificando.toLowerCase().equals("n")) {
					seguir = false;
				}
			}

		}
		manager.close();
	}

	public static void mostrarAlumnosPorCurso() {
		manager = enf.createEntityManager();
		final Curso[] cursos = Curso.values();
		List<Alumno> todos;
		List<Alumno> ninyosDelCurso = new ArrayList<Alumno>();

		// Peticion de curso por consola
		System.out.println("Seleccione el curso que quiere mostrar:");
		for (int i = 0; i < cursos.length; i++) {
			System.out.println((i + 1) + ". " + cursos[i]);
		}

		int curso = sc.nextInt();

		// query a hibernate
		String hql = "FROM Alumno";
		todos = manager.createQuery(hql).getResultList();

		// Recorremos los resultados con opcion de que no haya nada

		for (Alumno al : todos) {
			if (al.getCurso() == manager.find(CursoAlumnos.class, cursos[curso - 1])) {
				ninyosDelCurso.add(al);
			}
		}

		if (ninyosDelCurso.isEmpty()) {
			System.out.println("No se ha encontrado ningun alumno asignado a ese curso.");
		} else {
			System.out.println("Hay " + ninyosDelCurso.size() + " chavales en " + cursos[curso - 1] + ": ");
			for (Alumno al : ninyosDelCurso) {
				System.out.println(al);
			}
		}

		manager.close();
	}

	public static void buscarAlumnoPorDni() {
		System.out.println("Introduzca el DNI del alumno que desea buscar:");
		String dniAlumno = sc.next();
		manager = enf.createEntityManager();
		alumnoTemporal = manager.find(Alumno.class, dniAlumno);
		if (!(alumnoTemporal == null)) {
			System.out.println(alumnoTemporal);
		} else {
			System.out.println("Alumno no encontrado.");
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

}
