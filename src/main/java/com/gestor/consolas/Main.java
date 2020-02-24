package com.gestor.consolas;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.gestor.clases.Alumno;
import com.gestor.clases.AlumnoEnEspera;
import com.gestor.clases.Monitor;
import com.gestor.clases.Tutor;
import com.gestor.clases.Vacantes;
import com.gestor.enums.Alergia;
import com.gestor.enums.Cargo;
import com.gestor.enums.NombreCurso;

public class Main {

	private static EntityManager manager; /// TEMPORAL
	private static EntityManagerFactory enf; /// TEMPORAL

	public static void main(String[] args) {
		// CREAMOS UN GESTOR DE PERSISTENCIA(MANAGER)
		System.out.println(
				"===========================\n" + "    Comienzo de main.    \n" + "============================");
		enf = Persistence.createEntityManagerFactory("Persistencia");

		//// TEMPORAL
		Tutor t = new Tutor("1", "Mateo", "Apell", "Dosque", "C/Urguilla", "Barcelona", "498349J", "4565432",
				"4309853098", "placeholder@placeholder.ph");
		manager = enf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();

		List<Tutor> listaTutor = manager.createQuery("FROM Tutor").getResultList();
		for (Tutor tu : listaTutor) {
			// System.out.println(tu);
		}
		manager.close();

		manager = enf.createEntityManager();
		manager.getTransaction().begin();

		Vacantes vacante1 = new Vacantes(NombreCurso.colonia, 0, 5);
		manager.persist(vacante1);
		Vacantes vacante2 = new Vacantes(NombreCurso.manada, 0, 5);
		manager.persist(vacante2);
		Vacantes vacante3 = new Vacantes(NombreCurso.tropa, 0, 5);
		manager.persist(vacante3);
		Vacantes vacante4 = new Vacantes(NombreCurso.esculta, 0, 5);
		manager.persist(vacante4);
		Vacantes vacante5 = new Vacantes(NombreCurso.clan, 0, 5);
		manager.persist(vacante5);

		NombreCurso curso;

		Alumno alumnoTemporal = new Alumno("1", "Miguel", "Negron", "Cuello", LocalDate.of(2008, 5, 5),
				listaTutor.get(0), true, false);
		curso = alumnoTemporal.getCurso();
		Vacantes vacante = manager.find(Vacantes.class, curso);
		vacante.anyadePlazaOcupada();
		manager.persist(alumnoTemporal);

		alumnoTemporal = new Alumno("6", "Miguel", "Negron", "Cuello", LocalDate.of(2008, 5, 5), listaTutor.get(0),
				true, false);

		curso = alumnoTemporal.getCurso();
		vacante = manager.find(Vacantes.class, curso);
		vacante.anyadePlazaOcupada();
		manager.persist(alumnoTemporal);

		alumnoTemporal = new Alumno("7", "Miguel", "Negron", "Cuello", LocalDate.of(2008, 5, 5), listaTutor.get(0),
				true, false);
		curso = alumnoTemporal.getCurso();
		vacante = manager.find(Vacantes.class, curso);
		vacante.anyadePlazaOcupada();
		manager.persist(alumnoTemporal);

		alumnoTemporal = new Alumno("8", "Miguel", "Negron", "Cuello", LocalDate.of(2008, 5, 5), listaTutor.get(0),
				true, false);
		curso = alumnoTemporal.getCurso();
		vacante = manager.find(Vacantes.class, curso);
		vacante.anyadePlazaOcupada();
		manager.persist(alumnoTemporal);

		alumnoTemporal = new Alumno("9", "Miguel", "Negron", "Cuello", LocalDate.of(2008, 5, 5), listaTutor.get(0),
				true, false);
		curso = alumnoTemporal.getCurso();
		vacante = manager.find(Vacantes.class, curso);
		vacante.anyadePlazaOcupada();
		manager.persist(alumnoTemporal);
		
		AlumnoEnEspera aee = new AlumnoEnEspera("10", "Mateo", LocalDate.of(2008, 5, 5), "767467T", "Alberto", "668553645", "alberto90@gmail.com", "Avisar cuando haya hueco");
		manager.persist(aee);

		alumnoTemporal = new Alumno("2", "Oscar", "Alcalde", "Zuerra", LocalDate.of(2002, 5, 5), listaTutor.get(0),
				true, false);
		curso = alumnoTemporal.getCurso();
		vacante = manager.find(Vacantes.class, curso);
		vacante.anyadePlazaOcupada();
		manager.persist(alumnoTemporal);

		alumnoTemporal = new Alumno("3", "Alvaro", "Cuello", "Puerta", LocalDate.of(2014, 5, 5), listaTutor.get(0),
				true, false);
		curso = alumnoTemporal.getCurso();
		vacante = manager.find(Vacantes.class, curso);
		vacante.anyadePlazaOcupada();
		manager.persist(alumnoTemporal);

		alumnoTemporal = new Alumno("4", "Roberto", "Rojo", "Enrile", LocalDate.of(2010, 5, 5), listaTutor.get(0), true,
				false);
		curso = alumnoTemporal.getCurso();
		vacante = manager.find(Vacantes.class, curso);
		vacante.anyadePlazaOcupada();
		manager.persist(alumnoTemporal);

		alumnoTemporal = new Alumno("5", "Manuel", "Real", "Lozano", LocalDate.of(2005, 5, 5), listaTutor.get(0), true,
				false);
		curso = alumnoTemporal.getCurso();
		vacante = manager.find(Vacantes.class, curso);
		vacante.anyadePlazaOcupada();
		manager.persist(alumnoTemporal);

		Monitor monitorTemporal = new Monitor("1", "Roberto", "Cervantes", "kj", LocalDate.of(2005, 5, 5), "k", "k",
				"k", "k", "k", "k", "k", Cargo.coordinacion, NombreCurso.colonia, true);
		manager.persist(monitorTemporal);
		Monitor monitorTemporal2 = new Monitor("2", "Roberto", "K", "k", LocalDate.of(2005, 5, 5), "k", "k", "k", "k",
				"k", "k", "k", Cargo.coordinacion, NombreCurso.esculta, false);
		manager.persist(monitorTemporal2);
		Monitor monitorTemporal3 = new Monitor("3", "Roberto", "K", "k", LocalDate.of(2005, 5, 5), "k", "k", "k", "k",
				"k", "k", "k", Cargo.coordinacion, NombreCurso.manada, false);
		manager.persist(monitorTemporal3);
		Monitor monitorTemporal4 = new Monitor("4", "Roberto", "K", "k", LocalDate.of(2005, 5, 5), "k", "k", "k", "k",
				"k", "k", "k", Cargo.coordinacion, NombreCurso.manada, false);
		manager.persist(monitorTemporal4);
		Monitor monitorTemporal5 = new Monitor("5", "Roberto", "K", "k", LocalDate.of(2005, 5, 5), "k", "k", "k", "k",
				"k", "k", "k", Cargo.coordinacion, NombreCurso.tropa, false);
		manager.persist(monitorTemporal5);

		manager.getTransaction().commit();
		manager.close();
		//// TEMPORAL

		Consola consola = new Consola(enf);
		consola.mostrarConsola();

		System.out.println("===========================\n" + "    Fin de main.    \n" + "============================");
	}

}
