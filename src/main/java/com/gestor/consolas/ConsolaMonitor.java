package com.gestor.consolas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.gestor.clases.Monitor;
import com.gestor.enums.Cargo;
import com.gestor.enums.NombreCurso;

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
				modificarMonitor();
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
		NombreCurso cursoMonitor;
		boolean tieneTitulo = false;
		NombreCurso[] cursos = NombreCurso.values();
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
		System.out.println("�Tiene titulo?s/n");
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
		final NombreCurso[] cursos = NombreCurso.values();
		List<Monitor> listaMonitores;
		List<Monitor> monitoresDelCurso = new ArrayList<Monitor>();
		manager = enf.createEntityManager();
		listaMonitores = manager.createQuery("FROM Monitor ORDER BY curso").getResultList();
		for (int curso = 0; curso < NombreCurso.values().length; curso++) {
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
				System.out.println("Hay " + monitoresDelCurso.size() + " monitores encargados del curso " + cursos[curso] + ": ");
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
			System.out.println("�No hay monitores!");
		}
	}
	
	public static void modificarMonitor() {
		final NombreCurso [] cursos = NombreCurso.values();
		Monitor monitorTemporal;
				String seguirModificando;
				boolean bucle = true;
				boolean seguir =  true;
				String entregado;
				int diaFechaNacimiento;
				int mesFechaNacimiento;
				int anyoFechaNacimiento;
				String nombreMonitor;
				String primerApellidoMonitor;
				String segundoApellidoMonitor;
				LocalDate fechaNac;
				String correoMonitor;
				String localidadMonitor;
				String codigoPostalMonitor;
				String telefonoFijoMonitor;
				String telefonoMovilMonitor;
				String telefonoEmergencia1;
				String telefonoEmergencia2;
				String ocupacionMonitor;
				String cursoMonitor;
				boolean tieneTitulo2;
				
				manager = enf.createEntityManager();
				System.out.println("Introduzca el Dni del monitor que desea modificar");
				String dniMonitor = sc.next();
				sc.nextLine();
				monitorTemporal = manager.find(Monitor.class, dniMonitor);
				if (monitorTemporal == null) {
					System.out.println("No se ha encontrado el monitor introducido.");
				} else {
					System.out.println("Monitor encontrado!");
					while(seguir) {
						System.out.println("Monitor a modificar: "+ monitorTemporal );
						System.out.println("Seleccione que desea modificar:\n0:Dni.\n1:Nombre.\n2:Primer apellido.\n3:Segundo apellido.\n4:Fecha de nacimiento.\n5:Correo electr�nico.\n6:Localidad.\n7:C�digo postal.\n8:Tel�fono fijo.\n9:Tel�fono m�vil.\n10:Tel�fono de emergencia 1.\n11:Tel�fono de emergencia 2.\n12:Ocupaci�n.\n13:Curso.\n14:T�tulo.\n15:Retroceder.");
						int opcion = sc.nextInt();
						switch(opcion) {
						case 0:
							System.out.println("Introduzca el nuevo DNI.");
							sc.nextLine();
							dniMonitor = sc.next();
							// Borramos el alumno y lo creamos de nuevo con el nuevo DNI (no se pueden
							// cambiar claves primarias)
							manager.getTransaction().begin();
							manager.merge(monitorTemporal);
							manager.remove(monitorTemporal);
							manager.getTransaction().commit();
							monitorTemporal.setDNI(dniMonitor);
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Dni modificado.");
							break;
						case 1:
							System.out.println("Escriba el nuevo nombre.");
							sc.nextLine();
							nombreMonitor = sc.next();
							monitorTemporal.setNombre(nombreMonitor);
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Nombre modificado.");
							break;
						case 2:
							System.out.println("Escriba el primer apellido.");
							sc.nextLine();
							primerApellidoMonitor = sc.next();
							monitorTemporal.setApellido1(primerApellidoMonitor);
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Apellido modificado.");
							break;
						case 3:
							System.out.println("Escriba el segundo apellido.");
							sc.nextLine();
							segundoApellidoMonitor = sc.next();
							monitorTemporal.setApellido2(segundoApellidoMonitor);
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
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
							monitorTemporal
									.setFechaNac(LocalDate.of(anyoFechaNacimiento, mesFechaNacimiento, diaFechaNacimiento));
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Fecha de nacimiento modificada.");
							break;
						case 5:
							System.out.println("Escriba el correo.");
							sc.nextLine();
							correoMonitor = sc.next();
							monitorTemporal.setCorreo(correoMonitor);
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Correo modificado.");
							break;
						case 6:
							System.out.println("Introduzca la nueva localidad");
							sc.hasNextLine();
							localidadMonitor = sc.next();
							monitorTemporal.setLocalidad(localidadMonitor);
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Correo modificado.");
							break;
						case 7:
							System.out.println("Introduzca el nuevo c�digo postal");
							sc.nextLine();
							codigoPostalMonitor = sc.next();
							monitorTemporal.setCodigoPostal(codigoPostalMonitor);
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("C�digo postal modificado");
							break;
						case 8:
							System.out.println("Introduce el nuevo tel�fono fijo");
							sc.nextLine();
							telefonoFijoMonitor = sc.next();
							monitorTemporal.setTelFijo(telefonoFijoMonitor);
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Tel�fono fijo modificado");
							break;
						case 9:
							System.out.println("Introduce el nuevo tel�fono m�vil");
							sc.nextLine();
							telefonoMovilMonitor = sc.next();
							monitorTemporal.setMovil(telefonoMovilMonitor);
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Tel�fono m�vil modificado");
							break;
						case 10:
							System.out.println("Introduce el primer tel�fono de emergencia");
							sc.nextLine();
							telefonoEmergencia1 = sc.next();
							monitorTemporal.setTelEmergencia1(telefonoEmergencia1);
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Tel�fono de emergencia 1 modificado");
							break;
						case 11:
							System.out.println("Introduce el segundo tel�fono de emergencia");
							sc.nextLine();
							telefonoEmergencia2 = sc.next();
							monitorTemporal.setTelEmergencia2(telefonoEmergencia2);
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Tel�fono de emergencia 2 modificado");
							break;
						case 12:
							System.out.println("Introduce la nueva ocupaci�n");
							sc.nextLine();
							ocupacionMonitor = sc.next();
							monitorTemporal.setCargo(Cargo.valueOf(ocupacionMonitor));;
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Cargo modificado");
							break;
						case 13:
							System.out.println("Introduce el nuevo curso");
							sc.nextLine();
							cursoMonitor = sc.next();
							monitorTemporal.setCurso(NombreCurso.valueOf(cursoMonitor));
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Curso modificado");
							break;
						case 14:
							System.out.println("Tiene titulo? S/N");
							bucle = true;
							while (bucle) {
								sc.nextLine();
								entregado = sc.next();
								if (entregado.toLowerCase().equals("s")) {
									monitorTemporal.setTieneTitulo(true);;
									bucle = false;
								} else if (entregado.toLowerCase().equals("n")) {
									monitorTemporal.setTieneTitulo(false);
									bucle = false;
								} else {
									System.out.println("Entrada incorrecta, por favor escriba S o N.");

								}
							}
							manager.getTransaction().begin();
							manager.persist(monitorTemporal);
							manager.getTransaction().commit();
							System.out.println("Entrega modificada.");
							break;
							
						case 15:
							seguir = false;
							break;
							
							
							
						}
						
						
					}
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
