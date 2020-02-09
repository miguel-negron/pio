package com.gestor.clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ALUMNO")
public class Alumno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DNI")
	private String DNI;
	@Column(name="NOMBRE")
	private String Nombre;
	@Column(name="APELLIDO_1")
	private String Apellido1;
	@Column(name="APELLIDO_2")
	private String Apellido2;
	@Column(name="FECHA_NACIMIENTO")
	private LocalDate fechaNac;
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="DNI_TUTOR")
	//@Column(name="DNI_TUTOR")
	private Tutor Tutor;
	@Column(name="FICHA_ENTREGADA")
	private boolean fichaEntregada;
	@Column(name="FOTO_ENTREGADA")
	private boolean fotoEntregada;
	@Column(name="CURSO")
	private Curso curso;
	
	public Alumno() {
		
	}



	public Alumno(String dni, String nombre, String apellido1, String apellido2, LocalDate fechaNac,
			com.gestor.clases.Tutor tutor, boolean fichaEntregada, boolean fotoEntregada) {
		super();
		DNI = dni;
		Nombre = nombre;
		Apellido1 = apellido1;
		Apellido2 = apellido2;
		this.fechaNac = fechaNac;
		Tutor = tutor;
		this.fichaEntregada = fichaEntregada;
		this.fotoEntregada = fotoEntregada;
		this.setCursoAutomatico();
	}


	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dni) {
		DNI = dni;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido1() {
		return Apellido1;
	}

	public void setApellido1(String apellido1) {
		Apellido1 = apellido1;
	}

	public String getApellido2() {
		return Apellido2;
	}

	public void setApellido2(String apellido2) {
		Apellido2 = apellido2;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(!(o instanceof Alumno)) {
			return false;
		}
		return DNI != null && DNI.contentEquals(((Alumno) o).getDNI());
	}
	
	@Override
	public int hashCode() {
		return 31;
	}

	public boolean isFichaEntregada() {
		return fichaEntregada;
	}

	public void setFichaEntregada(boolean fichaEntregada) {
		this.fichaEntregada = fichaEntregada;
	}

	public boolean isFotoEntregada() {
		return fotoEntregada;
	}

	public void setFotoEntregada(boolean fotoEntregada) {
		this.fotoEntregada = fotoEntregada;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public void setCursoAutomatico() {
		int edad = LocalDate.now().getYear() - fechaNac.getYear();
		int edadMin = 17;
		Curso[] cursos = com.gestor.clases.Curso.values();
		
		for (int i = cursos.length - 1; i >= 0; i--) {
			//System.out.println(i + " ---- " + cursos[i]);
			//System.out.println(edadMin + "<- min : e ->" + edad);
			
			if (edad > edadMin) {
				this.setCurso(cursos[i]);
				//System.out.println(this.getCurso());
				return;
			} else {
				edadMin -= 3;
			}
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		String estadoFicha;
		String estadoFoto;
		if(fichaEntregada) {
			estadoFicha = "Entregada";
		} else {
			estadoFicha = "No entregada";
		}
		if(fotoEntregada) {
			estadoFoto = "Entregada";
		} else {
			estadoFoto = "No entregada";
		}
		return "Alumno: " + Nombre +  ", DNI: " + DNI + ", Primer apellido: " + Apellido1 + ", Segundo apellido: " + Apellido2
				+ ", Fecha de nacimiento: " + fechaNac + ", Tutor=" + Tutor/* .getNombre() */ + ", Estado de la ficha: " + estadoFicha
				+ ", Estado de la foto: " + estadoFoto + ", Curso: " + curso;
	}



	public Tutor getTutor() {
		return Tutor;
	}

	public void setTutor(Tutor tutor) {
		Tutor = tutor;
	}



	

	
	

}
