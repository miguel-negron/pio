package com.gestor.consolas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.gestor.clases.Alumno;
import com.gestor.clases.AlumnoEnEspera;
import com.gestor.clases.Tutor;
import com.gestor.clases.Vacantes;
import com.gestor.enums.NombreCurso;

public class ConsolaAlumno {
	// Abrimos managers
	private static EntityManager manager;
	private static EntityManagerFactory enf;

	// Globales
	private static Scanner sc = new Scanner(System.in);
	static List<Tutor> listaTutores;
	private static Alumno alumnoTemporal;

	// Constructor
	public ConsolaAlumno(EntityManagerFactory enf) {
		ConsolaAlumno.enf = enf;
	}

	// Metodo ciclico principal
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
		String primerApellido;
		String segundoApellido;
		boolean fichaEntregada = false;
		boolean fotoEntregada = false;
		boolean bucle = true;
		String temporal;
		int diaFechaNacimiento;
		int mesFechaNacimiento;
		int anyoFechaNacimiento;

		manager = enf.createEntityManager();
		System.out.println("Insertamos un alumno por consola.");
		System.out.println("Introduzca su DNI.");
		dniAlumno = sc.next();
		sc.nextLine();
		Alumno alumnoRepetido = manager.find(Alumno.class, dniAlumno);
		// Comprobamos que no exista ningun otro alumno con el mismo dni
		if (alumnoRepetido != null) {
			System.out.println("Ya existe otro alumno con el mismo DNI.");
		} else {
			System.out.println("Introduzca su nombre.");
			nombreAlumno = sc.nextLine();
			System.out.println("Tutores existentes (por dni):");
			//Tutores
			List<Tutor> todos;
			String hql = "FROM Tutor";
			todos = manager.createQuery(hql).getResultList();
			
			for (Tutor tutor : todos) {
				System.out.println("\t" + tutor.getDNI() + ": " + tutor.getNombre());
			}
			System.out.println("\nIntroduzca un nuevo dni para crear otro tutor");

			dniTutor = sc.nextLine();
			// Tenemos que comprobar si existe el tutor en nuestra base de datos
			Tutor tutor = manager.find(Tutor.class, dniTutor);
			// Si existe anyadimos el alumno a la database y si no existe le pedimos que
			// seleccione un tutor existente
			if (tutor == null) {
				System.out.println("No hemos encontrado a ningun tutor con ese DNI.");
				tutor = crearTutor(sc, dniTutor);
				System.out.println(" - - Tutor creado - -");
			} 
				System.out.println("Introduzca su primer apellido del ninyo.");
				primerApellido = sc.next();
				System.out.println("Introduzca su segundo apellido del ninyo.");
				segundoApellido = sc.next();
				System.out.println("Ha entregado su ficha?S/N");
				while (bucle) {
					temporal = sc.next();
					if (temporal.toLowerCase().equals("s")) {
						fichaEntregada = true;
						bucle = false;
					} else if (temporal.toLowerCase().equals("n")) {
						fichaEntregada = false;
						bucle = false;
					} else {
						System.out.println("Entrada incorrecta, por favor escriba S o N.");
					}
				}
				bucle = true;
				System.out.println("Ha entregado su foto?S/N");
				while (bucle) {
					temporal = sc.next();
					if (temporal.toLowerCase().equals("s")) {
						fotoEntregada = true;
						bucle = false;
					} else if (temporal.toLowerCase().equals("n")) {
						fotoEntregada = false;
						bucle = false;
					} else {
						System.out.println("Entrada incorrecta, por favor escriba S o N.");
					}
				}
				System.out.println("Escriba el dia de nacimiento");
				diaFechaNacimiento = sc.nextInt();
				System.out.println("Escriba el mes de nacimiento");
				mesFechaNacimiento = sc.nextInt();
				System.out.println("Escriba el anio de nacimiento");
				anyoFechaNacimiento = sc.nextInt();

				alumnoTemporal = new Alumno(dniAlumno, nombreAlumno, primerApellido, segundoApellido,
						LocalDate.of(anyoFechaNacimiento, mesFechaNacimiento, diaFechaNacimiento), tutor,
						fichaEntregada, fotoEntregada);
				Vacantes vacante = manager.find(Vacantes.class, alumnoTemporal.getCurso());
				if (vacante.anyadePlazaOcupada() == true) {
					// Como se ha alcanzado el cupo de alumnos en este curso, lo introducimos en la
					// lista de espera
					System.out.println("Todas las plazas de este curso están ocupadas, alumno inscrito en la lista de espera.");
					AlumnoEnEspera alumnoEspera = new AlumnoEnEspera(dniAlumno, nombreAlumno, LocalDate.of(anyoFechaNacimiento, mesFechaNacimiento, diaFechaNacimiento), tutor.getDNI(), tutor.getNombre(), tutor.getTelMovil(), tutor.getEmail(), "");
					manager.getTransaction().begin();
					manager.persist(tutor);
					manager.flush();
					manager.persist(alumnoEspera);
					manager.getTransaction().commit();
				} else {
					System.out.println("Alumno dado de alta con éxito.");
					manager.getTransaction().begin();
					manager.persist(tutor);
					manager.flush();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
				}

			
		}
		manager.close();
	}
	
	public static Tutor crearTutor(Scanner sc, String dni) {
		Tutor t;
		
		System.out.println("Introduzca el nombre del tutor");
		String nombre = sc.next();
		
		System.out.println("Introduzca el primer apellido del tutor");
		String apellido1 = sc.next();
		
		System.out.println("Introduzca el segundo apellido del tutor");
		String apellido2 = sc.next();
		
		t = new Tutor(dni, nombre, apellido1, apellido2, null, null, null, null, null, null);
		
		return t;
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
				//Lo eliminamos del curso
				Vacantes vacante = manager.find(Vacantes.class, alumnoTemporal.getCurso());
				vacante.reducePlazaOcupada();
				manager.getTransaction().begin();
				manager.merge(alumnoTemporal);
				manager.remove(alumnoTemporal);
				manager.getTransaction().commit();
				System.out.println("Alumno " + alumnoTemporal.getNombre() + " borrado");
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
		final NombreCurso[] cursos = NombreCurso.values();

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

					alumnoTemporal.setCursoAlumnos(cursos[cursoSeleccionado - 1]);
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
		final NombreCurso[] cursos = NombreCurso.values();
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
			if (al.getCurso() == cursos[curso - 1]) {
				ninyosDelCurso.add(al);
			}
		}

		if (ninyosDelCurso.isEmpty()) {
			System.out.println("No se ha encontrado ningun alumno asignado a ese curso.");
		} else {
			if (ninyosDelCurso.size() == 1) {
				System.out.println("Hay solo " + ninyosDelCurso.size() + " chaval en " + cursos[curso - 1] + ": ");
				for (Alumno al : ninyosDelCurso) {
					System.out.println(al);
				}
			} else {
				System.out.println("Hay " + ninyosDelCurso.size() + " chavales en " + cursos[curso - 1] + ": ");
				for (Alumno al : ninyosDelCurso) {
					System.out.println(al);
				}
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
