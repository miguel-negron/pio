package com.gestor.asistentes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.gestor.clases.Alumno;
import com.gestor.clases.Curso;
import com.gestor.clases.Tutor;
import com.gestor.enums.NombreCurso;

public class AsistenteCurso {
	//Managers
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	//Globales
	private static Scanner sc = new Scanner(System.in);
	static List<Tutor> listaTutores;
	private static Alumno alumnoTemporal;

	//Constructores
	public AsistenteCurso(EntityManagerFactory enf) {
		AsistenteCurso.emf = enf;
	}

	public Curso getCurso(NombreCurso nombreCurso, EntityManager managerAjeno) {
		//Creamos manager
		//manager = emf.createEntityManager();
		
		Curso c = managerAjeno.find(Curso.class, nombreCurso);
		
		//Cerramos manager
		//manager.close();
		return c;
	}
	
}
