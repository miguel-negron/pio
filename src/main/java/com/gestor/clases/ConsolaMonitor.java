package com.gestor.clases;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ConsolaMonitor {
	private static Monitor monitorTemporal;
	static int diaNacimiento;
	static int mesNacimiento;
	static int anioNacimiento;
	private static String dniMonitor;
	private static String nombreMonitor;
	private static String primerApellidoMonitor;
	private static String segundoApellidoMonitor;
	private static LocalDate fechaNacMonitor;
	private static String correoMonitor;
	private static String localidadMonitor;
	private static String codigoPostalMonitor;
	private static String telefonoFijoMonitor;
	private static String telefonoMovilMonitor;
	private static String telefonoEmergencia1;
	private static String telefonoEmergencia2;
	private static String ocupacionMonitor;
	private static String cursoMonitor;
	private static boolean tieneTitulo;
	private static int respuesta;
	private static EntityManager manager;
	private static EntityManagerFactory enf;
	private static Scanner sc = new Scanner(System.in);
	
	ConsolaMonitor(EntityManagerFactory enf){
		this.enf = enf;
	}
	
	public int mostrarConsolaMonitor() {
		respuesta = 10;
		while (respuesta != 0  && respuesta != 9) {
			System.out.println(
					"1: Dar de alta a un monitor. \n2: Dar de baja a un monitor. \n3: Mostrar todos los monitores. \n4: Modificar los datos de un monitor \n5: Mostrar alumnos por curso elegido. \n6: Búsqueda de alumno por DNI. \n7: Volver atrás."
					+ "\n0: Finalizar el programa");
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				finalizarPrograma();
				break;
			case 1:
				altaMonitor();
				break;
			case 2:
				
				break;
			case 3:
				mostrarMonitores();
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				

			case 7:
				retroceder();
			}
		}
		return respuesta;
	}
	
	
	//funcion insertar
	public static void altaMonitor() {
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
		System.out.println("Introduzca su año de nacimiento");
		anioNacimiento = sc.nextInt();
		System.out.println("Introduzca su correo");
		correoMonitor = sc.next();
		System.out.println("Introduzca su localidad");
		localidadMonitor= sc.next();
		System.out.println("Introduzca su codigo postal");
		codigoPostalMonitor= sc.next();
		System.out.println("Introduzca un telefono fijo");
		telefonoFijoMonitor= sc.next();
		System.out.println("Introduzca un telefono movil");
		telefonoMovilMonitor= sc.next();
		System.out.println("Introduzca un telefono de emergencia");
		telefonoEmergencia1 = sc.next();
		System.out.println("Introduzca otro telefono de emergencia");
		telefonoEmergencia2 = sc.next();
		System.out.println("Introduzca la ocupacion");
		ocupacionMonitor= sc.next();
		System.out.println("Introduzca el curso");
		cursoMonitor= sc.next();
		System.out.println("¿Tiene titulo?");
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
	
		monitorTemporal = new Monitor(dniMonitor, nombreMonitor, primerApellidoMonitor, segundoApellidoMonitor,LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento), correoMonitor, localidadMonitor, codigoPostalMonitor, telefonoFijoMonitor, telefonoMovilMonitor, telefonoEmergencia1, telefonoEmergencia2, ocupacionMonitor, cursoMonitor, tieneTitulo);
		manager.getTransaction().begin();
		manager.persist(monitorTemporal);
		manager.getTransaction().commit();
		System.out.println("Monitor dado de alta correctamente");
		manager.close();
			
		
	}
	public static void mostrarMonitores() {
		manager = enf.createEntityManager();
		List<Monitor> lista = manager.createQuery("FROM Monitor").getResultList();
		
		if(lista.isEmpty()) {
			System.out.println("No hay monitores!");
		} else {
			for (Monitor al : lista) {
				System.out.println(al);
			}
		}
		manager.close();
	}
	
	
	// Función para salir del programa
	public static void finalizarPrograma() {
		respuesta = 0;
	}
	
	//Función retroceder
	//Nos permite volver al anterior menú
	public static void retroceder() {
		respuesta = 9;
	}

}
