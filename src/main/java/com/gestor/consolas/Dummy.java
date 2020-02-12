package com.gestor.consolas;

import java.time.LocalDate;

import com.gestor.clases.Alumno;

public class Dummy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Alumno a1 = new Alumno("1234S", "nom", "a1", "a2", LocalDate.of(2002, 5, 7), null, true, true);
		
		System.out.println(a1);
	}

}
