package com.gestor.consolas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.gestor.clases.Monitor;
import com.gestor.enums.Cargo;
import com.gestor.enums.Curso;

public class ConsolaMonitor {
	private static EntityManager manager;
	private static EntityManagerFactory enf;
	private static Scanner sc = new Scanner(System.in);

	ConsolaMonitor(EntityManagerFactory enf) {
		this.enf = enf;
	}

	public int mostrarConsolaMonitor() {
		int respuesta;
		respuesta = 10;
		while (respuesta != 0 && respuesta != 9) {
			System.out.println(
					"1: Dar de alta a un monitor. \n2: Dar de baja a un monitor. \n3: Mostrar todos los monitores. \n4: Modificar los datos de un monitor.\n5: Buscar monitor por DNI. \n6: Mostrar monitores por curso. \n7: Mostrar los monitores que tienen certificado. \n8: Volver atras."
							+ "\n0: Finalizar el programa");
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				respuesta = finalizarPrograma();
				break;
			case 1:
				altaMonitor();
				break;
			case 2:
				bajaMonitor();
				break;
			case 3:
				mostrarMonitores();
				break;
			case 4:

				break;
			case 5:
				buscarMonitorPorDni();
				break;
			case 6:
				mostrarMonitoresPorCurso();
				break;
			case 7:
				mostrarMonitoresConCertificado();
				break;
			case 8:
				respuesta = retroceder();
				break;
			}
		}
		return respuesta;
	}

	// funcion insertar
	public static void altaMonitor() {
		Monitor monitorTemporal;
		int diaNacimiento;
		int mesNacimiento;
		int anioNacimiento;
		String dniMonitor;
		String nombreMonitor;
		String primerApellidoMonitor;
		String segundoApellidoMonitor;
		String correoMonitor;
		String localidadMonitor;
		String codigoPostalMonitor;
		String telefonoFijoMonitor;
		String telefonoMovilMonitor;
		String telefonoEmergencia1;
		String telefonoEmergencia2;
		Cargo cargoMonitor;
		Curso cursoMonitor;
		boolean tieneTitulo = false;
		Curso[] cursos = Curso.values();
		Cargo[] cargos = Cargo.values();
		boolean bucle = true;
		String entregado;
		manager = enf.createEntityManager();

		System.out.println("Insertamos un monitor por consola");
		System.out.println("Introduzca su DNI.");
		dniMonitor = sc.next();
		System.out.println(dniMonitor);
		sc.hasNextLine();
		System.out.println("Introduzca su nombre");
		nombreMonitor = sc.next();
		System.out.println("Introduzca su primer apellido");
		primerApellidoMonitor = sc.next();
		System.out.println("Introduzca su segundo apellido");
		segundoApellidoMonitor = sc.next();
		System.out.println("Introduzca su dia de nacimiento");
		diaNacimiento = sc.nextInt();
		System.out.println("Introduzca su mes de nacimiento");
		mesNacimiento = sc.nextInt();
		System.out.println("Introduzca su anyo de nacimiento");
		anioNacimiento = sc.nextInt();
		System.out.println("Introduzca su correo");
		correoMonitor = sc.next();
		System.out.println("Introduzca su localidad");
		localidadMonitor = sc.next();
		System.out.println("Introduzca su codigo postal");
		codigoPostalMonitor = sc.next();
		System.out.println("Introduzca un telefono fijo");
		telefonoFijoMonitor = sc.next();
		System.out.println("Introduzca un telefono movil");
		telefonoMovilMonitor = sc.next();
		System.out.println("Introduzca un telefono de emergencia");
		telefonoEmergencia1 = sc.next();
		System.out.println("Introduzca otro telefono de emergencia");
		telefonoEmergencia2 = sc.next();
		System.out.println("Introduzca el cargo.");
		for (int i = 0; i < cargos.length; i++) {
			System.out.println((i + 1) + ". " + cargos[i]);
		}
		sc.nextLine();
		int cargoS = sc.nextInt();
		cargoMonitor = cargos[cargoS - 1];
		System.out.println("Introduzca el curso.");
		for (int i = 0; i < cursos.length; i++) {
			System.out.println((i + 1) + ". " + cursos[i]);
		}
		sc.nextLine();
		int cursoSeleccionado = sc.nextInt();
		cursoMonitor = cursos[cursoSeleccionado - 1];
		System.out.println("¿Tiene titulo?s/n");
		while (bucle) {
			sc.nextLine();
			entregado = sc.next();
			if (entregado.toLowerCase().equals("s")) {
				tieneTitulo = true;
				bucle = false;
			} else if (entregado.toLowerCase().equals("n")) {
				tieneTitulo = false;
				bucle = false;
			} else {
				System.out.println("Entrada incorrecta, por favor escriba S o N.");
			}
		}
		monitorTemporal = new Monitor(dniMonitor, nombreMonitor, primerApellidoMonitor, segundoApellidoMonitor,
				LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento), correoMonitor, localidadMonitor,
				codigoPostalMonitor, telefonoFijoMonitor, telefonoMovilMonitor, telefonoEmergencia1,
				telefonoEmergencia2, cargoMonitor, cursoMonitor, tieneTitulo);
		Monitor monitorRepetido = manager.find(Monitor.class, monitorTemporal.getDNI());
		// Comprobamos que no exista ningun otro alumno con el mismo dni
		if (monitorRepetido != null) {
			System.out.println("Ya existe otro monitor con el mismo DNI.");
		} else {
			manager.getTransaction().begin();
			manager.persist(monitorTemporal);
			manager.getTransaction().commit();
			System.out.println("Monitor dado de alta correctamente");
		}
		manager.close();

	}

	public static void mostrarMonitores() {
		manager = enf.createEntityManager();
		List<Monitor> lista = manager.createQuery("FROM Monitor").getResultList();

		if (lista.isEmpty()) {
			System.out.println("No hay monitores!");
		} else {
			for (Monitor al : lista) {
				System.out.println(al);
			}
		}
		manager.close();
	}

	public static void bajaMonitor() {
		Monitor monitorTemporal;
		manager = enf.createEntityManager();
		System.out.println("Escribe el DNI del monitor que deseas dar de baja");
		String dniMonitor = sc.next();
		sc.nextLine();
		monitorTemporal = manager.find(Monitor.class, dniMonitor);
		if (!(monitorTemporal == null)) {
			System.out.println("Estas seguro que quieres eliminar a " + monitorTemporal.getNombre() + " ?(S/N)");
			String confirmacion = sc.nextLine();
			if (confirmacion.toLowerCase().equals("s")) {
				// Borramos
				manager.getTransaction().begin();
				manager.merge(monitorTemporal);
				manager.remove(monitorTemporal);
				manager.getTransaction().commit();
				System.out.println("Monitor " + monitorTemporal + " borrado");
			} else {
				System.out.println("Monitor no borrado.");
			}
		} else {
			System.out.println("Monitor no encontrado.");
		}
		manager.close();
	}

	public void buscarMonitorPorDni() {
		Monitor monitorTemporal;
		manager = enf.createEntityManager();

		System.out.println("Escribe el DNI del monitor que deseas buscar");
		String dniMonitor = sc.next();
		sc.nextLine();
		monitorTemporal = manager.find(Monitor.class, dniMonitor);
		if (!(monitorTemporal == null)) {
			System.out.println(monitorTemporal);
		} else {
			System.out.println("Monitor no encontrado.");
		}
		manager.close();
	}

	public void mostrarMonitoresPorCurso() {
		final Curso[] cursos = Curso.values();
		List<Monitor> listaMonitores;
		List<Monitor> monitoresDelCurso = new ArrayList<Monitor>();
		manager = enf.createEntityManager();
		listaMonitores = manager.createQuery("FROM Monitor ORDER BY curso").getResultList();
		for (int curso = 0; curso < Curso.values().length; curso++) {
			monitoresDelCurso.clear();
			for (Monitor m : listaMonitores) {
				if (m.getCurso() == cursos[curso]) {
					monitoresDelCurso.add(m);
				}
			}

			if (monitoresDelCurso.isEmpty()) {
				System.out.println("No se ha encontrado ningun monitor asignado a ese curso.");
			} else {
				if(monitoresDelCurso.size() == 1) {
					System.out.println("Hay " + monitoresDelCurso.size() + " monitor encargado del curso " + cursos[curso] + ": ");
				}else {
				System.out.println("Hay " + monitoresDelCurso.size() + " monitores encargado del curso " + cursos[curso] + ": ");
				}
				for (Monitor m : monitoresDelCurso) {
					System.out.println("\t" + m);
				}
			}
		}
	}

	public void mostrarMonitoresConCertificado() {
		Monitor monitorTemporal;
		List<Monitor> listaMonitores;
		int contador = 0;
		manager = enf.createEntityManager();
		listaMonitores = manager.createQuery("FROM Monitor").getResultList();
		if (listaMonitores != null) {
			for (Monitor m : listaMonitores) {
				if (m.isTieneTitulo()) {
					System.out.println(m.getNombre() + " " + m.getApellido1() + ".");
					contador++;
				}
			}
			System.out.println("Total: " + contador + " monitores.");
		} else {
			System.out.println("¡No hay monitores!");
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
