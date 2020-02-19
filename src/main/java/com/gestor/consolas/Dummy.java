package com.gestor.consolas;

import java.time.LocalDate;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.gestor.clases.Alergia;
import com.gestor.clases.Alumno;
import com.gestor.clases.Curso;
import com.gestor.clases.Tutor;
import com.gestor.enums.Alergeno;
import com.gestor.enums.NombreCurso;

public class Dummy {

	private static EntityManager manager; ///TEMPORAL
	private static EntityManagerFactory enf; ///TEMPORAL

	public static void main(String[] args) {
		enf = Persistence.createEntityManagerFactory("Persistencia");
		manager = enf.createEntityManager();
		
		Tutor t = new Tutor("1", "Mateo", "Apell", "Dosque", "C/Urguilla", "Barcelona", "498349J", "4565432", "4309853098", "placeholder@placeholder.ph");

		Curso c = new Curso(NombreCurso.clan);
		Alumno a1 = new Alumno("a", "a", "a", "a", LocalDate.of(2008, 5, 5), t, true, true, c);
		Alergia al1 = new Alergia(Alergeno.Gluten);
		
		manager.getTransaction().begin();
		manager.persist(c);
		manager.persist(t);
		manager.persist(al1);
		manager.persist(a1);
		manager.getTransaction().commit();
		
		System.out.println(manager.find(Alumno.class, "a"));

		
		
		
		
		
		manager.close();
		enf.close();
		
		
	}

	private static void a() {
		Alumno a1 = new Alumno("1234S", "nom", "a1", "a2", LocalDate.of(2002, 5, 7), null, true, true);
		
		//System.out.println(a1);
		

		System.out.println("Introduzca los datos del Alumno");
		
		System.out.println("Introduzca su DNI. [1/8]");
		Scanner sc = new Scanner(System.in);
		String dni = sc .nextLine();
		//System.out.println(dni);
		System.out.println("Introduzca su nombre.[2/8]");
		String nombre = sc.nextLine();
		System.out.print("Introduzca el dni de su tutor.[4/8]");
		String dniTutor = sc.nextLine();
		System.out.println("Introduzca su fecha de nacimiento.[3/8]");
		
		System.out.println("Escriba el dia. [dd]/mm/aaaa");
		int diaFechaNacimiento = sc.nextInt();
		System.out.println("Escriba el mes. " + diaFechaNacimiento + "/[mm]/aaaa");
		int mesFechaNacimiento = sc.nextInt();
		System.out.println("Escriba el anyo. " + diaFechaNacimiento + "/" + mesFechaNacimiento + "/[aaaa]");
		int anyoFechaNacimiento = sc.nextInt();
		
		LocalDate fechaNac = LocalDate.of(anyoFechaNacimiento, mesFechaNacimiento, diaFechaNacimiento);
		
sc.reset();
		
		
		System.out.println(dniTutor);
	}

}
