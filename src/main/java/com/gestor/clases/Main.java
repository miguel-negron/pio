package com.gestor.clases;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

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
		Alumno alumnoTemporal = new Alumno("1", "Miguel", "Negron", "TONTO", LocalDate.now(), listaTutor.get(0), null,  true, false,
				Curso.clan);
		manager.getTransaction().begin();
		manager.persist(alumnoTemporal);
		manager.getTransaction().commit();
		manager.close();
		////TEMPORAL
		
		Consola consola = new Consola(enf);
		consola.mostrarConsola();
		
	}


}
