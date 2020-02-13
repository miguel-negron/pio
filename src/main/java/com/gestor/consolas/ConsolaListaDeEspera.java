package com.gestor.consolas;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.gestor.clases.Alumno;
import com.gestor.clases.AlumnoEnEspera;

public class ConsolaListaDeEspera {
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public ConsolaListaDeEspera(EntityManagerFactory enf) {
		ConsolaListaDeEspera.emf = enf;
	}
	
	//Metodos
	public int mostrarConsolaLista() {
		Scanner sc = new Scanner(System.in);
		int respuesta;

		respuesta = 10;
		while (respuesta != 0 && respuesta != 9) {
			System.out.println(
					"\n\n\n"
					+ "1: Dar de alta a un alumno. \n"
					//+ "2: Dar de baja a un alumno. \n"
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
				insertarAlumno(sc);
				break;
			case 2:
				//bajaAlumno();
				break;
			case 3:
				mostrarAlumnosLista();
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
		
		
		//Abrimos manager
		manager = emf.createEntityManager();
		
		System.out.println("Introduzca los datos del Alumno");
		
		System.out.println("Introduzca su DNI. [1/8]");
		dni = sc.nextLine();
	
		System.out.println("Introduzca su nombre.[2/8]");
		nombre = sc.nextLine();

		System.out.println("Introduzca el dni de su tutor.[4/8]");
		dniTutor = sc.nextLine();

		System.out.println("Introduzca el nombre de su tutor.[5/8]");
		nombreTutor = sc.nextLine();
		
		System.out.println("Introduzca un telefono de contacto.[6/8]");
		telContacto = sc.nextLine();

		System.out.println("Introduzca un email de contacto.[7/8]");
		emailContacto = sc.nextLine();

		System.out.println("Introduzca datos adicionales.[8/8]");
		infoAdicional = sc.nextLine();
		
		System.out.println("Introduzca su fecha de nacimiento.[3/8]");
		
		System.out.println("Escriba el dia. [dd]/mm/aaaa");
		sc.nextLine();
		int diaFechaNacimiento = sc.nextInt();
		System.out.println("Escriba el mes. " + diaFechaNacimiento + "/[mm]/aaaa");
		sc.nextLine();
		int mesFechaNacimiento = sc.nextInt();
		System.out.println("Escriba el anyo. " + diaFechaNacimiento + "/" + mesFechaNacimiento + "/[aaaa]");
		sc.nextLine();
		int anyoFechaNacimiento = sc.nextInt();
		
		fechaNac = LocalDate.of(anyoFechaNacimiento, mesFechaNacimiento, diaFechaNacimiento);
		
		//Introducimos los datos en un objeto AEE
		aee = new AlumnoEnEspera(dni, nombre, fechaNac, dniTutor, nombreTutor, telContacto, emailContacto, infoAdicional);

			manager.getTransaction().begin();
			manager.persist(aee);
			manager.getTransaction().commit();		
		
		manager.close();
	}
	
	public static void mostrarAlumnosLista() {
		manager = emf.createEntityManager();
		List<AlumnoEnEspera> lista = manager.createQuery("FROM LISTA_DE_ESPERA ORDER BY curso").getResultList();
		
		if(lista.isEmpty()) {
			System.out.println("No hay alumnos!");
		} else {
			System.out.println("Hay " + lista.size() + "alumnos:");
			for (AlumnoEnEspera al : lista) {
				System.out.println(al);
			}
		}
		manager.close();
	}
}


