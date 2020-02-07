package com.gestor.clases;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

public class ConsolaAlumno {
	private static EntityManager manager;
	private static EntityManagerFactory enf;
	private static Scanner sc = new Scanner(System.in);
	static List<Tutor> listaTutores;
	private static Alumno alumnoTemporal;
	private static int respuesta;
	private static String dniAlumno;
	private static String nombreAlumno;
	private static String primerApellidoAlumno;
	private static String segundoApellidoAlumno;
	private static String dniTutor;
	private static int diaFechaNacimiento;
	private static int mesFechaNacimiento;
	private static int anioFechaNacimiento;

	ConsolaAlumno(EntityManagerFactory enf) {
		this.enf = enf;
	}

	public int mostrarConsolaAlumno() {
		respuesta = 10;
		while (respuesta != 0 && respuesta != 9) {
			System.out.println(
					"1: Dar de alta a un alumno. \n2: Dar de baja a un alumno. \n3: Mostrar todos los alumnos. \n4: Modificar los datos de un alumno \n5: Mostrar alumnos por curso elegido. \n6: B�squeda de alumno por DNI. \n7: Volver atr�s."
							+ "\n0: Finalizar el programa");
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				finalizarPrograma();
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
				retroceder();
			}
		}
		return respuesta;
	}

	// Aqui empieza la funci�n insertar
	public static void altaAlumno() {
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
		// Si existe a�adimos el alumno a la database y si no existe le pedimos que
		// seleccione un tutor existente
		if (tutor2 == null) {
			System.out.println("No hemos encontrado a ningun tutor con ese DNI, por favor eliga un tutor existente.");
		} else {
			alumnoTemporal = new Alumno(dniAlumno, nombreAlumno, "", "", LocalDate.now(), tutor2, true, true, "");
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

	// Funci�n eliminar
	public static void bajaAlumno() {
		manager = enf.createEntityManager();
		System.out.println("Escribe el dni del alumno que desea borrar.");
		String dniAlumno = sc.next();
		sc.nextLine();
		alumnoTemporal = manager.find(Alumno.class, dniAlumno);
		if (!(alumnoTemporal == null)) {
			System.out.println("�Estas seguro que quieres eliminar a " + alumnoTemporal.getNombre() + " ?(S/N)");
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
		for (Alumno al : lista) {
			System.out.println(al);
		}
		if(lista.isEmpty()) {
			System.out.println("�No hay alumnos!");
		}
		manager.close();
	}

	public static void modificarAlumno() {
		String seguirModificando;
		boolean bucle = true;
		boolean seguir = true;
		String entregado;
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
						"Seleccione qu� desea modificar:\n0:Dni.\n1:Nombre.\n2:Primer apellido.\n3:Segundo apellido.\n4:Fecha de nacimiento.\n5:Entrega de la ficha.\n6:Entrega de la foto.\n7:Curso");
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
					System.out.println("Escriba el d�a.");
					sc.nextLine();
					diaFechaNacimiento = sc.nextInt();
					System.out.println("Escriba el mes.");
					sc.nextLine();
					mesFechaNacimiento = sc.nextInt();
					System.out.println("Escriba el a�o.");
					sc.nextLine();
					anioFechaNacimiento = sc.nextInt();
					alumnoTemporal
							.setFechaNac(LocalDate.of(anioFechaNacimiento, mesFechaNacimiento, diaFechaNacimiento));
					manager.getTransaction().begin();
					manager.persist(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Fecha de nacimiento modificada.");
					break;
				case 5:
					System.out.println("�Tiene la ficha? S/N");
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
					System.out.println("�Tiene la foto? S/N");
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
					System.out.println(
							"Seleccione el nuevo curso.\n1: P�os. \n2: Devotos. \n3: Fan�ticos. \n4: Iluminados");
					sc.nextLine();
					int cursoSeleccionado = sc.nextInt();
					switch (cursoSeleccionado) {
					case 1:
						alumnoTemporal.setCurso("P�os");
						manager.getTransaction().begin();
						manager.persist(alumnoTemporal);
						manager.getTransaction().commit();
						System.out.println("Curso modificado.");
						break;
					case 2:
						alumnoTemporal.setCurso("Devotos");
						manager.getTransaction().begin();
						manager.persist(alumnoTemporal);
						manager.getTransaction().commit();
						System.out.println("Curso modificado.");
						break;
					case 3:
						alumnoTemporal.setCurso("Fan�ticos");
						manager.getTransaction().begin();
						manager.persist(alumnoTemporal);
						manager.getTransaction().commit();
						System.out.println("Curso modificado.");
						break;
					case 4:
						alumnoTemporal.setCurso("Iluminados");
						manager.getTransaction().begin();
						manager.persist(alumnoTemporal);
						manager.getTransaction().commit();
						System.out.println("Curso modificado.");
						break;
					}
					break;
				}
				System.out.println("�Quieres seguir modificando este Alumno? s/n");
				seguirModificando = sc.next();
				if (seguirModificando.toLowerCase().equals("n")) {
					seguir = false;
				}
			}

		}
		manager.close();
	}

	public static void mostrarAlumnosPorCurso() {
		int contadorAlumnos = 0;
		manager = enf.createEntityManager();
		List<Alumno> lista = manager.createQuery("FROM Alumno").getResultList();
		System.out.println(
				"Seleccione el curso que quiere mostrar: \n1: P�os. \n2: Devotos. \n3: Fan�ticos. \n4: Iluminados");
		int curso = sc.nextInt();
		switch (curso) {
		case 1:
			for (Alumno al : lista) {
				if (al.getCurso().toLowerCase().equals("pios") || al.getCurso().toLowerCase().equals("p�os")) {
					System.out.println(al);
					contadorAlumnos++;
				}
			}
			break;
		case 2:
			for (Alumno al : lista) {
				if (al.getCurso().toLowerCase().equals("devotos")) {
					System.out.println(al);
					contadorAlumnos++;
				}
			}
			break;
		case 3:
			for (Alumno al : lista) {
				if (al.getCurso().toLowerCase().equals("fanaticos")
						|| al.getCurso().toLowerCase().equals("fan�ticos")) {
					System.out.println(al);
					contadorAlumnos++;
				}
			}
			break;
		case 4:
			for (Alumno al : lista) {
				if (al.getCurso().toLowerCase().equals("iluminados")) {
					System.out.println(al);
					contadorAlumnos++;
				}
			}
			break;
		}
		if (contadorAlumnos == 0) {
			System.out.println("No se ha encontrado ning�n alumno asignado a ese curso.");
		}
		manager.close();
	}

	public static void buscarAlumnoPorDni() {
		System.out.println("Introduzca el DNI del alumno que desea buscar:");
		dniAlumno = sc.next();
		manager = enf.createEntityManager();
		alumnoTemporal = manager.find(Alumno.class, dniAlumno);
		if (!(alumnoTemporal == null)) {
			System.out.println(alumnoTemporal);
		} else {
			System.out.println("Alumno no encontrado.");
		}
		manager.close();

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
