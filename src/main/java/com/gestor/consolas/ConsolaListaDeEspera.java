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

public class ConsolaListaDeEspera {
	private static EntityManager manager;
	private static EntityManagerFactory emf;

	public ConsolaListaDeEspera(EntityManagerFactory enf) {
		ConsolaListaDeEspera.emf = enf;
	}

	// Metodos
	public int mostrarConsolaLista() {
		Scanner sc = new Scanner(System.in);
		int respuesta;

		respuesta = 10;
		while (respuesta != 0 && respuesta != 9) {
			System.out.println("\n" + "1: Dar de alta a un alumno. \n" + "2: Dar de baja a un alumno. \n"
					+ "3: Mostrar todos los alumnos. \n" + "4: Modificar los datos de un alumno. \n"
					+ "5: Mostrar alumnos por curso elegido. \n" + "6: Busqueda de alumno por DNI. \n"
					+ "7: Actualizar vacantes. \n" + "8: Mostrar alumnos con posibilidad de entrada.\n"
					+ "9: Volver atras. \n" + "0: Finalizar el programa." + "\n");
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				respuesta = finalizarPrograma();
				break;
			case 1:
				insertarAlumno(sc);
				break;
			case 2:
				bajaAlumno(sc);
				break;
			case 3:
				mostrarAlumnosLista();
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
				actualizarVacantes();
				break;
			case 8:
				mostrarVacantes();
				break;
			case 9:
				respuesta = retroceder();
				break;
			}
		}
		return respuesta;
	}

	public void insertarAlumno(Scanner sc) {
		AlumnoEnEspera aee;

		String dni;
		LocalDate fechaNac;
		String nombre;
		String dniTutor;
		String nombreTutor;
		String telContacto;
		String emailContacto;
		String infoAdicional;

		// Abrimos manager
		manager = emf.createEntityManager();

		System.out.println("Introduzca los datos del Alumno");

		System.out.println("Introduzca su DNI.");
		sc.nextLine();
		dni = sc.nextLine();
		AlumnoEnEspera alumno = manager.find(AlumnoEnEspera.class, dni);
		// Comprobamos que no exista ningun otro alumno con el mismo dni
		if (alumno != null) {
			System.out.println("Ya existe otro alumno con el mismo DNI.");
		} else {
			System.out.println("Introduzca su nombre.");
			nombre = sc.nextLine();
			System.out.println("Introduzca el dni de su tutor.");
			dniTutor = sc.nextLine();
			System.out.println("Introduzca el nombre de su tutor.");
			nombreTutor = sc.nextLine();
			System.out.println("Introduzca un telefono de contacto.");
			telContacto = sc.nextLine();
			System.out.println("Introduzca un email de contacto.");
			emailContacto = sc.nextLine();
			System.out.println("Introduzca datos adicionales.");
			infoAdicional = sc.nextLine();
			System.out.println("Introduzca su fecha de nacimiento.");
			System.out.println("Escriba el dia.");
			int diaFechaNacimiento = sc.nextInt();
			System.out.println("Escriba el mes. ");
			sc.nextLine();
			int mesFechaNacimiento = sc.nextInt();
			System.out.println("Escriba el anyo. ");
			sc.nextLine();
			int anyoFechaNacimiento = sc.nextInt();

			fechaNac = LocalDate.of(anyoFechaNacimiento, mesFechaNacimiento, diaFechaNacimiento);

			// Introducimos los datos en un objeto AEE
			aee = new AlumnoEnEspera(dni, nombre, fechaNac, dniTutor, nombreTutor, telContacto, emailContacto,
					infoAdicional);

			manager.getTransaction().begin();
			manager.persist(aee);
			manager.getTransaction().commit();
			System.out.println("Alumno insertado con éxito.");
		}
		manager.close();
	}

	public static void mostrarAlumnosLista() {
		manager = emf.createEntityManager();
		List<AlumnoEnEspera> lista = manager.createQuery("FROM AlumnoEnEspera order by curso").getResultList();
		if (lista.isEmpty()) {
			System.out.println("No hay alumnos!");
		} else {
			if (lista.size() == 1) {
				System.out.println("Hay " + lista.size() + " alumno:");
				System.out.println(lista.get(0));
			} else {
				System.out.println("Hay " + lista.size() + " alumnos:");
				for (AlumnoEnEspera al : lista) {
					System.out.println(al);
				}
			}
		}
		manager.close();
	}

	public void bajaAlumno(Scanner sc) {
		AlumnoEnEspera alumnoTemporal;
		manager = emf.createEntityManager();
		System.out.println("Escribe el dni del alumno que desea borrar.");
		String dniAlumno = sc.next();
		sc.nextLine();
		alumnoTemporal = manager.find(AlumnoEnEspera.class, dniAlumno);
		if (!(alumnoTemporal == null)) {
			System.out.println("Estas seguro que quieres eliminar a " + alumnoTemporal.getNombre() + " ?(S/N)");
			String confirmacion = sc.nextLine();
			if (confirmacion.toLowerCase().equals("s")) {
				// Borramos
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

	public void modificarAlumno() {
		Scanner sc = new Scanner(System.in);
		final NombreCurso[] cursos = NombreCurso.values();
		String seguirModificando;
		boolean seguir = true;
		int diaFechaNacimiento;
		int mesFechaNacimiento;
		int anyoFechaNacimiento;
		String nombreAlumno;
		String DNItutor;
		String nombreTutor;
		String telefonoTutor;
		String emailTutor;
		String infoAdicional;

		manager = emf.createEntityManager();
		System.out.println("Introduzca el DNI del alumno que desea modificar");
		String dniAlumno = sc.next();
		sc.nextLine();
		AlumnoEnEspera alumnoTemporal = manager.find(AlumnoEnEspera.class, dniAlumno);
		// Vemos si existe el alumno buscado
		if (alumnoTemporal == null) {
			System.out.println("No se ha encontrado el alumno introducido.");
		} else {
			System.out.println("Alumno encontrado!");
			while (seguir) {
				System.out.println("Alumno a modificar: " + alumnoTemporal);
				System.out.println(
						"Seleccione que desea modificar:\n0:Dni.\n1:Nombre.\n2:Fecha de Nacimiento.\n3:DNI del tutor.\n4:Nombre del tutor.\n5:Telefono de Contacto.\n6:Email.\n7:Curso.\n8:Informacion adicional.");
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
					alumnoTemporal.setDni(dniAlumno);
					;
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
				case 3:
					System.out.println("Escriba el nuevo DNI.");
					sc.nextLine();
					DNItutor = sc.next();
					alumnoTemporal.setDniTutor(DNItutor);
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("DNI modificado.");
					break;
				case 4:
					System.out.println("Escriba el nuevo nombre.");
					sc.nextLine();
					nombreTutor = sc.next();
					alumnoTemporal.setNombreTutor(nombreTutor);
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Nombre modificado.");
					break;

				case 5:
					System.out.println("Escriba el nuevo telefono de contacto.");
					sc.nextLine();
					telefonoTutor = sc.next();
					alumnoTemporal.setTelContacto(telefonoTutor);
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Telefono modificado.");
					break;
				case 6:
					System.out.println("Escriba el nuevo email.");
					sc.nextLine();
					emailTutor = sc.next();
					alumnoTemporal.setEmailContacto(emailTutor);
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Email modificado.");
					break;
				case 7:
					System.out.println("Seleccione el nuevo curso.");

					for (int i = 0; i < cursos.length; i++) {
						System.out.println((i + 1) + ". " + cursos[i]);
					}
					sc.nextLine();
					int cursoSeleccionado = sc.nextInt();

					alumnoTemporal.setCurso(cursos[cursoSeleccionado - 1]);
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Curso modificado.");
					break;
				case 8:
					System.out.println("Escriba la nueva informacion adicional.");
					sc.nextLine();
					infoAdicional = sc.next();
					alumnoTemporal.setInfoAdicional(infoAdicional);
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Informacion modificada.");
					break;
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

	public void mostrarAlumnosPorCurso() {
		Scanner sc = new Scanner(System.in);
		manager = emf.createEntityManager();
		final NombreCurso[] cursos = NombreCurso.values();
		List<AlumnoEnEspera> todos;
		List<AlumnoEnEspera> ninyosDelCurso = new ArrayList<AlumnoEnEspera>();

		// Peticion de curso por consola
		System.out.println("Seleccione el curso que quiere mostrar:");
		for (int i = 0; i < cursos.length; i++) {
			System.out.println((i + 1) + ". " + cursos[i]);
		}

		int curso = sc.nextInt();

		// query a hibernate
		String hql = "FROM AlumnoEnEspera";
		todos = manager.createQuery(hql).getResultList();

		// Recorremos los resultados con opcion de que no haya nada

		for (AlumnoEnEspera al : todos) {
			if (al.getCurso() == cursos[curso - 1]) {
				ninyosDelCurso.add(al);
			}
		}

		if (ninyosDelCurso.isEmpty()) {
			System.out.println("No se ha encontrado ningun alumno asignado a ese curso.");
		} else {
			if (ninyosDelCurso.size() == 1) {
				System.out.println("Hay solo " + ninyosDelCurso.size()
						+ " chaval esperando para inscribirse en el curso: " + cursos[curso - 1] + ": ");
				for (AlumnoEnEspera al : ninyosDelCurso) {
					System.out.println(al);
				}
			} else {
				System.out.println("Hay " + ninyosDelCurso.size() + " chavales esprando para inscribirse en el curso: "
						+ cursos[curso - 1] + ": ");
				for (AlumnoEnEspera al : ninyosDelCurso) {
					System.out.println(al);
				}
			}
		}

		manager.close();
	}

	public void buscarAlumnoPorDni() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el DNI del alumno que desea buscar:");
		String dniAlumno = sc.next();
		manager = emf.createEntityManager();
		AlumnoEnEspera alumnoTemporal = manager.find(AlumnoEnEspera.class, dniAlumno);
		if (!(alumnoTemporal == null)) {
			System.out.println(alumnoTemporal);
		} else {
			System.out.println("Alumno no encontrado.");
		}
		manager.close();
	}

	public void mostrarVacantes() {
		List<Vacantes> cursosVacantes;
		List<AlumnoEnEspera> alumnosEnEspera;
		manager = emf.createEntityManager();
		cursosVacantes = manager.createQuery("FROM Vacantes").getResultList();
		alumnosEnEspera = manager.createQuery("FROM AlumnoEnEspera order by fechaEntrada").getResultList();
		Tutor tutor;
		Alumno alumno;
		int contador = 0;
		for (Vacantes v : cursosVacantes) {
			// Comprobamos si hay plazas disponibles en cada curso
			if (v.getPlazas() < v.getLimite()) {
				// Si la hay, buscamos alumnos en la lista de espera que estén esperando para
				// ese curso
				for (AlumnoEnEspera aee : alumnosEnEspera) {
					if (v.getPlazas() < v.getLimite()) {
						if(aee.getCurso() == v.getCurso()) {
							contador++;
						System.out.println("Alumno " + aee.getNombre() + " puede ser inscrito en el curso: " + v.getCurso() + ".\nLlamar a su tutor para confirmación: " + aee.getTelContacto());
						}
					}
				}
			}
		}
		if(contador == 0) {
			System.out.println("No hay plazas u alumnos disponibles.");
		}
	}

	// Usado para ver hay plazas disponibles para los alumnos en espera
	public void actualizarVacantes() {
		List<Vacantes> cursosVacantes;
		List<AlumnoEnEspera> alumnosEnEspera;
		manager = emf.createEntityManager();
		cursosVacantes = manager.createQuery("FROM Vacantes").getResultList();
		alumnosEnEspera = manager.createQuery("FROM AlumnoEnEspera order by fechaEntrada").getResultList();
		Tutor tutor;
		Alumno alumno;
		int contador = 0;
		for (Vacantes v : cursosVacantes) {
			// Comprobamos si hay plazas disponibles en cada curso
			if (v.getPlazas() < v.getLimite()) {
				// Si la hay, buscamos alumnos en la lista de espera que estén esperando para
				// ese curso
				for (AlumnoEnEspera aee : alumnosEnEspera) {
					if (v.getPlazas() < v.getLimite()) {
						if (aee.getCurso() == v.getCurso()) {
							contador++;
							// Borramos ese alumno de la lista de espera y lo movemos a la tabla Alumno
							alumno = new Alumno();
							alumno.setDNI(aee.getDni());
							alumno.setNombre(aee.getNombre());
							// Añadimos el tutor si no existe
							tutor = manager.find(Tutor.class, aee.getDniTutor());
							if (tutor == null) {
								tutor = new Tutor();
								tutor.setDNI(aee.getDniTutor());
								tutor.setNombre(aee.getNombreTutor());
								tutor.setEmail(aee.getEmailContacto());
								tutor.setTelMovil(aee.getTelContacto());
							}
							alumno.setTutor(tutor);
							alumno.setFechaNac(aee.getFechaNac());
							alumno.setCursoAlumnos(aee.getCurso());
							v.anyadePlazaOcupada();
							manager.getTransaction().begin();
							manager.persist(alumno);
							manager.persist(tutor);
							manager.merge(v);
							manager.remove(aee);
							manager.getTransaction().commit();
							System.out.println(
									"Alumno " + alumno.getNombre() + " inscrito en el curso " + alumno.getCurso());
							System.out.println("Llamar al tutor para preguntar los datos que falten.");
						}
					}
				}
			}
		}
		manager.close();
		if(contador == 0) {
			System.out.println("No hay curso con plazas libres u alumno disponible.");
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
