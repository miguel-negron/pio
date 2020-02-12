package com.gestor.consolas;

import java.time.LocalDate;
import java.util.Scanner;

import com.gestor.clases.Alumno;

public class Dummy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
