package com.gestor.consolas;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.gestor.clases.Alumno;
import com.gestor.enums.Alergia;

public class ConsolaAlergias {

	private static EntityManager manager;
	private static EntityManagerFactory emf;
	Alergia[] alergenos = Alergia.values();

	public ConsolaAlergias(EntityManagerFactory enf) {
		ConsolaAlergias.emf = enf;
	}

	// Metodos
	public int mostrarConsolaLista() {
		Scanner sc = new Scanner(System.in);
		int respuesta;

		respuesta = 10;
		while (respuesta != 0 && respuesta != 9) {
			System.out.println("\n\n\n" + "1: Modificar la alergia de un alumno. \n"
					+ "2: Ver todas las alergias. \n" + "3: Ver la alergia de cada alumno. \n" + "4: Retroceder.\n" + "0: Finalizar el programa" + "\n\n\n");
			respuesta = sc.nextInt();
			switch (respuesta) {
			case 0:
				respuesta = finalizarPrograma();
				break;
			case 1:
				modificarAlergiaDeAlumno();
				break;
			case 2:
				showAlergias();
				break;
			case 3:
				 mostrarAlergiasAlumno();
				break;
			case 4:
				respuesta = retroceder();
			}
		}
		return respuesta;
	}// Mostrar consola

	public Alumno getAlumnoById(String dniAlumno) {
		Alumno alumno;

		//manager = emf.createEntityManager();

		alumno = manager.find(Alumno.class, dniAlumno);

		// manager.close();

		return alumno;
	}

	
	public void modificarAlergiaDeAlumno() {
		Alergia[] alergias = Alergia.values();
		String dniAlumno;
		Scanner sc = new Scanner(System.in);
		Alumno alumno;
		int n = 0;
		Alergia alergia;
		manager = emf.createEntityManager();
		System.out.println("Escribe el DNI del alumno que deseas modificar.");
		dniAlumno = sc.next();
		alumno = manager.find(Alumno.class, dniAlumno);
		System.out.println("Alumno: " + alumno.getNombre() + " " + alumno.getApellido1());
		System.out.println("Seleccione la alergia que tiene:");
		for(Alergia a: alergias) {
			System.out.println(n + ": " + a.toString());
			n++;
		}
		alergia = alergias[sc.nextInt()]; //Asumimos que no nos equivocamos y escribimos un numero mayor a los que hay
		alumno.setAlergia(alergia);
		manager.getTransaction().begin();
		manager.merge(alumno);
		manager.getTransaction().commit();
		System.out.println("Alumno " + alumno.getNombre() + " modificado con alergia a :" + alumno.getAlergia());
		manager.close();

	}

	public void showAlergias() {
		Alergia[] alergias = Alergia.values();
		for(Alergia a: alergias) {
			System.out.println(a.toString());
		}

		/*
		System.out.println(todos.size());
		for (int i = 0; i < alergenos.length; i++) {

			System.out.println(alergenos[i]);
			for (Alumno al : todos) {
				System.out.println(al.getAlergias().size());
				if (al.getAlergias().contains(alergenos[i])) {
					System.out.println(
							"Dni: " + al.getDNI() + " -- Nombre: " + al.getNombre() + " -- Rama: " + al.getCurso());

				}
			}
		}*/
	}

	public void mostrarAlergiasAlumno() {
		manager = emf.createEntityManager();
		List<Alumno> todos;
		String hql = "FROM Alumno";
		int contador = 0;
		todos = manager.createQuery(hql).getResultList();
		for(Alumno a: todos) {
			if(a.getAlergia() != null) {
				System.out.println("Alumno " + a.getNombre() + " " + a.getApellido1() + " tiene alergia al: " + a.getAlergia());
				contador++;
			}
		}
		if(contador == 0) {
			System.out.println("Ningun alumno tiene alergias!");
		}
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

}// ConsolaAlergias
