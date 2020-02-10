package com.gestor.clases;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.gestor.clases.Alumno;

public class Main {

	private static EntityManager manager; ///TEMPORAL
	private static EntityManagerFactory enf; ///TEMPORAL


	public static void main(String[] args) {
		//Branch miguel
		// CREAMOS UN GESTOR DE PERSISTENCIA(MANAGER)

		enf = Persistence.createEntityManagerFactory("Persistencia");

		
		////TEMPORAL
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
		manager.getTransaction().begin();
		
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


		manager.getTransaction().commit();
		manager.close();
		////TEMPORAL
		
		Consola consola = new Consola(enf);
		consola.mostrarConsola();
		
	}
		

}

