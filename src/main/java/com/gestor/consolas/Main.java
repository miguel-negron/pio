package com.gestor.consolas;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.gestor.clases.Alergia;
import com.gestor.clases.Alumno;
import com.gestor.clases.Curso;
import com.gestor.clases.Monitor;
import com.gestor.clases.Tutor;
import com.gestor.clases.Vacantes;
import com.gestor.enums.Alergeno;
import com.gestor.enums.Cargo;
import com.gestor.enums.NombreCurso;

public class Main {

	private static EntityManager manager; ///TEMPORAL
	private static EntityManagerFactory enf; ///TEMPORAL


	public static void main(String[] args) {
		// CREAMOS UN GESTOR DE PERSISTENCIA(MANAGER)
		System.out.println(
				"===========================\n"
				+ "    Comienzo de main.    \n"
				+ "============================"
				);
		enf = Persistence.createEntityManagerFactory("Persistencia");

		
		////TEMPORAL
		Tutor t = new Tutor("1", "Mateo", "Apell", "Dosque", "C/Urguilla", "Barcelona", "498349J", "4565432", "4309853098", "placeholder@placeholder.ph");
		manager = enf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		
		List<Tutor> listaTutor = manager.createQuery("FROM Tutor").getResultList();
		for (Tutor tu : listaTutor) {
			//System.out.println(tu);
		}
		manager.close();

		manager = enf.createEntityManager();
		manager.getTransaction().begin();
		
		/*Vacantes vacante1 = new Vacantes(Curso.colonia, 0, 5);
		manager.persist(vacante1);
		Vacantes vacante2 = new Vacantes(Curso.manada, 0, 5);
		manager.persist(vacante2);
		Vacantes vacante3 = new Vacantes(Curso.tropa, 0, 5);
		manager.persist(vacante3);
		Vacantes vacante4 = new Vacantes(Curso.esculta, 0, 5);
		manager.persist(vacante4);
		Vacantes vacante5 = new Vacantes(Curso.clan, 0, 5);
		manager.persist(vacante5);*/
		
		Alumno alumnoTemporal = new Alumno("1", "Miguel", "Negron", "ElMagnifico", LocalDate.of(2008, 5, 5), listaTutor.get(0), true, false);
		manager.persist(alumnoTemporal);
		
		Alumno alumnoTemporal2 = new Alumno("2", "Miguel2", "Negron2", "ElMagnifico2", LocalDate.of(2002, 5, 5), listaTutor.get(0), true, false);
		manager.persist(alumnoTemporal2);
		
		Alumno alumnoTemporal3 = new Alumno("3", "Miguel", "Negron3", "ElMagnifico3", LocalDate.of(2014, 5, 5), listaTutor.get(0), true, false);
		manager.persist(alumnoTemporal3);

		Alumno alumnoTemporal4 = new Alumno("4", "Miguel4", "Negron4", "ElMagnifico4", LocalDate.of(2010, 5, 5), listaTutor.get(0), true, false);
		manager.persist(alumnoTemporal4);
		
		Alumno alumnoTemporal5 = new Alumno("5", "Miguel5", "Negron5", "ElMagnifico5", LocalDate.of(2005, 5, 5), listaTutor.get(0), true, false);
		manager.persist(alumnoTemporal5);
		
		Monitor monitorTemporal = new Monitor("1", "Roberto", "Cervantes", "kj", LocalDate.of(2005, 5, 5), "k", "k", "k", "k", "k", "k","k", Cargo.coordinacion, NombreCurso.colonia, true);
		manager.persist(monitorTemporal);
		Monitor monitorTemporal2 = new Monitor("2", "Roberto", "K", "k", LocalDate.of(2005, 5, 5), "k", "k", "k", "k", "k", "k", "k", Cargo.coordinacion, NombreCurso.esculta, false);
		manager.persist(monitorTemporal2);
		Monitor monitorTemporal3 = new Monitor("3", "Roberto", "K", "k", LocalDate.of(2005, 5, 5), "k", "k", "k", "k", "k", "k", "k", Cargo.coordinacion, NombreCurso.manada, false);
		manager.persist(monitorTemporal3);
		Monitor monitorTemporal4 = new Monitor("4", "Roberto", "K", "k", LocalDate.of(2005, 5, 5), "k", "k", "k", "k", "k", "k", "k", Cargo.coordinacion, NombreCurso.manada, false);
		manager.persist(monitorTemporal4);
		Monitor monitorTemporal5 = new Monitor("5", "Roberto", "K", "k", LocalDate.of(2005, 5, 5), "k", "k", "k", "k", "k", "k", "k", Cargo.coordinacion, NombreCurso.tropa, false);
		manager.persist(monitorTemporal5);


		for (int i = 0; i < Alergeno.values().length; i++) {
			manager.persist(new Alergia(Alergeno.values()[i]));
			System.out.println(Alergeno.values()[i]);
		}
		
		for (int i = 0; i < NombreCurso.values().length; i++) {
			manager.persist(new Curso(NombreCurso.values()[i]));
			System.out.println(NombreCurso.values()[i]);
		}

		manager.getTransaction().commit();
		manager.close();
		////TEMPORAL
		
		Consola consola = new Consola(enf);
		consola.mostrarConsola();
		
		System.out.println(
				"===========================\n"
				+ "    Fin de main.    \n"
				+ "============================"
				);
	}
		

}

