package com.gestor.consolas;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import com.gestor.clases.Alergia;
import com.gestor.clases.Alumno;
import com.gestor.enums.Alergeno;

public class ConsolaAlergias {

	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public ConsolaAlergias(EntityManagerFactory enf) {
		ConsolaAlergias.emf = enf;
	}
	
	//Metodos
		public int mostrarConsolaLista() {
			Scanner sc = new Scanner(System.in);
			int respuesta;

			respuesta = 10;
			while (respuesta != 0 && respuesta != 9) {
				System.out.println(
						"\n\n\n  - CONSOLA ALERGIAS - \n"
						+ "1: Anyadir alergia por dni. \n"
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
					anyadirAlergiaPorId(sc);
					break;
				case 2:
					//bajaAlumno();
					break;
				case 3:
					//mostrarAlumnosLista();
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
		}//Mostrar consola
		
		public Alumno getAlumnoById(String dniAlumno) {
			Alumno alumno;
			
			manager = emf.createEntityManager();
			
			alumno = manager.find(Alumno.class, dniAlumno);
			
			//manager.close();
			
			return alumno;
		}
		
		public void anyadirAlergiaPorId(Scanner sc) {
			manager = emf.createEntityManager();

			int selecAlergia = 0;
			Alergeno[] alergenos = Alergeno.values();
			Alumno alumno;
			
			System.out.println("Introduzca el DNI del alumno que desea buscar:");
			String dniAlumno = sc.next();
			alumno = getAlumnoById(dniAlumno);
			System.out.println("Anyadiendo alergia a " + alumno.getNombre() + " con DNI: " + alumno.getDNI());
			
			if(alumno == null)
				return;
			
			do {
				System.out.println("Seleccione la alergia a anyadir");
				
				//Ciclar por las alergias que no tiene el alumno
				for (int i = 0; i < alergenos.length; i++) {
					if (!alumno.getAlergias().contains(manager.find(Alergia.class, alergenos[i]))) {
						System.out.println("\t" + (i + 1) + ". " + alergenos[i]);
					}
				}
				System.out.println("\t" + (alergenos.length + 1) + ". Finalizar");
				
				selecAlergia = sc.nextInt() - 1;
				System.out.println(selecAlergia + "sel -- len " + alergenos.length);
				if (selecAlergia >= 0 && selecAlergia < alergenos.length) {
					alumno.addAlergia(manager.find(Alergia.class, alergenos[selecAlergia]));
				} else if (selecAlergia == (alergenos.length + 1)){
					
				} else {
					System.out.println("introduzca un numero valido");
				}
			} while (selecAlergia != (alergenos.length + 1));
			
			manager.close();

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}//ConsolaAlergias
