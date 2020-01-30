package com.gestor.clases;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.gestor.clases.Alumno;

public class Main {

	private static EntityManager manager;
	private static EntityManagerFactory enf;
	private static Scanner sc = new Scanner(System.in);
	static List<Tutor> listaTutores;
	private static Alumno alumnoTemporal;
	private static int respuesta = 9;

	public static void main(String[] args) {
		//Branch miguel
		// CREAMOS UN GESTOR DE PERSISTENCIA(MANAGER)
		//Miguel estuvo aqui ñ
		enf = Persistence.createEntityManagerFactory("Persistencia");

		Tutor t = new Tutor("1", "Mateo", "Apell", "Dosque", "C/Urguilla", "Barcelona", "498349J", "4565432",
				"4309853098", "placeholder@placeholder.ph");
		manager = enf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		List<Tutor> listaTutor = manager.createQuery("FROM Tutor").getResultList();
		for (Tutor tu : listaTutor) {
			System.out.println(tu);
		}
		manager.close();

		manager = enf.createEntityManager();
		Alumno alumnoTemporal = new Alumno("43098573", "Miguel", "Negrï¿½n", "TONTO", new Date(), listaTutor.get(0), true, false, "pï¿½os");
		manager.getTransaction().begin();
		manager.persist(alumnoTemporal);
		manager.getTransaction().commit();
		manager.close();

		//Consola
		while(respuesta != 0) {
			System.out.println("1:Dar de alta a un alumno. \n2:Dar de baja a un alumno. \n3:Mostrar todos los alumnos. \n4:Modificar los datos de un alumno \n0:Terminar el programa.");
			respuesta = sc.nextInt();
		switch(respuesta) {
		case 0:
			salir();
			break;
		case 1:
			alta();
			break;
		case 2:
			baja();
			break;
		case 3:
			mostrarAlumnos();
			break;
		case 4:
			modificarAlumno();
		}
		}
		
		

	}
	
	// Aqui empieza la funciï¿½n insertar
			public static void alta() {
				manager = enf.createEntityManager();
				System.out.println("Insertamos un alumno por consola.");
				String dniAlumno;
				String nombreAlumno;
				String dniTutor;
				System.out.println("Introduzca su DNI.");
				dniAlumno = sc.next();
				String basura = sc.nextLine(); //Fix chapuza para eliminar la apariciï¿½n de doble lï¿½nea y aseguramos que se insertan todos los datos
				System.out.println("Introduzca su nombre.");
				nombreAlumno = sc.nextLine();
				System.out.println("Introduzca el dni de su tutor.");
				dniTutor = sc.nextLine();
				// Tenemos que comprobar si existe el tutor en nuestra base de datos
				Tutor tutor2 = manager.find(Tutor.class, dniTutor);
				//Si existe aï¿½adimos el alumno a la database y si no existe le pedimos que seleccione un tutor existente
				if (tutor2 == null) {
					System.out.println("No hemos encontrado a nadie con ese DNI, por favor eliga un tutor existente.");
				} else {
					alumnoTemporal = new Alumno(dniAlumno, nombreAlumno, "", "", new Date(), tutor2, true, true, "");
				}
				manager.getTransaction().begin();
				manager.persist(alumnoTemporal);
				manager.getTransaction().commit();
				manager.close();
				respuesta = 9;
				mostrarAlumnos();
			}
			
		//Funciï¿½n eliminar
			public static void baja() {
				manager = enf.createEntityManager();
				System.out.println("Escribe el dni del alumno que desea borrar.");
				String dniAlumno = sc.next();
				String basura = sc.nextLine(); //Fix chapuza para eliminar la apariciï¿½n de doble lï¿½nea y aseguramos que se insertan todos los datos
				alumnoTemporal = manager.find(Alumno.class, dniAlumno);
				System.out.println(alumnoTemporal);
				System.out.println("ï¿½Estï¿½s seguro que quieres eliminar a " + alumnoTemporal + " ?(Y/N)");
				String confirmacion = sc.nextLine();
				if(confirmacion.toLowerCase().equals("y")) {
					//Borramos
					manager.getTransaction().begin();
					manager.merge(alumnoTemporal); 
					manager.remove(alumnoTemporal);
					manager.getTransaction().commit();
					System.out.println("Alumno " + alumnoTemporal + " borrado");
				} else {
					System.out.println("Alumno no borrado.");
				}
				respuesta = 9;
				manager.close();
				mostrarAlumnos();
			}
			
			public static void mostrarAlumnos() {
				manager = enf.createEntityManager();
				List<Alumno> lista = manager.createQuery("FROM Alumno").getResultList();
				for (Alumno al : lista) {
					System.out.println(al);
				}
				manager.close();
				respuesta = 9;
			}
			
			public static void modificarAlumno() {
				manager = enf.createEntityManager();
				System.out.println("Introduzca el DNI del alumno que desea modificar");
				String dniAlumno = sc.next();
				String basura = sc.nextLine(); //Fix chapuza para eliminar la apariciï¿½n de doble lï¿½nea y aseguramos que se insertan todos los datos
				alumnoTemporal = manager.find(Alumno.class, dniAlumno);
				//Vemos si existe el alumno buscado
				if(alumnoTemporal == null) {
					System.out.println("No se ha encontrado el alumno introducido.");
				} else {
					System.out.println("Alumno encontrado!");
					System.out.println("Seleccione qu desea modificar:\n1:Dni. \n2:Nombre.\n3:Primer apellido.\n4:Segundo apellido.\n4:Tutor.\n5Entrega de la ficha.\n6:Entrega de la foto");
				}
			}
			
			//Funciï¿½n para salir del programa
			public static void salir() {
				respuesta = 0;
				System.out.println("Programa terminado.");
			}

}
