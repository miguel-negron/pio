package com.gestor.clases;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gestor.enums.NombreCurso;

@Entity
@Table(name = "LISTA_DE_ESPERA")
public class AlumnoEnEspera implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "DNI")
	private String dni;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "FECHA_NACIMIENTO")
	private LocalDate fechaNac;
	@Column(name = "CURSO")
	private NombreCurso curso;
	@Column(name = "ENTRADA")
	private LocalDate fechaEntrada;
	
	@Column(name = "DNI_TUTOR")
	private String dniTutor;
	@Column(name = "NOMBRE_TUT")
	private String nombreTutor;
	@Column(name = "TEL_CONTACTO")
	private String telContacto;
	@Column(name = "EMAIL_CONTACTO")
	private String emailContacto;
	@Column(name = "INFO_ADICIONAL")
	private String infoAdicional;


	public AlumnoEnEspera() {}

	public AlumnoEnEspera(
			String dni,
			String nombre,
			LocalDate fechaNac,
			String dniTutor,
			String nombreTutor,
			String telContacto,
			String emailContacto,
			String infoAdicional
			) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.dniTutor = dniTutor;
		this.nombreTutor = nombreTutor;
		this.telContacto = telContacto;
		this.emailContacto = emailContacto;
		this.infoAdicional = infoAdicional;
		this.fechaEntrada = LocalDate.now();
		this.setCursoAutomatico();
	}

	//Getters and Setters
	public void setCursoAutomatico() {
		int edad = LocalDate.now().getYear() - fechaNac.getYear();
		int edadMin = 17;
		NombreCurso[] cursos = com.gestor.enums.NombreCurso.values();
		
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public NombreCurso getCurso() {
		return curso;
	}

	public void setCurso(NombreCurso cursos) {
		this.curso = cursos;
	}

	public LocalDate getFectaEntrada() {
		return fechaEntrada;
	}

	public void setFectaEntrada(LocalDate fectaEntrada) {
		this.fechaEntrada = fectaEntrada;
	}

	public String getDniTutor() {
		return dniTutor;
	}

	public void setDniTutor(String dniTutor) {
		this.dniTutor = dniTutor;
	}

	public String getNombreTutor() {
		return nombreTutor;
	}

	public void setNombreTutor(String nombreTutor) {
		this.nombreTutor = nombreTutor;
	}

	public String getTelContacto() {
		return telContacto;
	}

	public void setTelContacto(String telContacto) {
		this.telContacto = telContacto;
	}

	public String getEmailContacto() {
		return emailContacto;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	@Override
	public String toString() {
		return "DNI: " + dni + "\n"
				+ "\tAlumno: " + nombre + "\n"
				+ "\tFecha de Nacimiento: " + fechaNac + "\n"
				+ "\tCurso objetivo: " + curso + "\n"
				+ "\tFecha de Entrada: " + fechaEntrada + "\n"
				+ "\tDNI del tutor: " + dniTutor + "\n"
				+ "\tNombre del tutor: " + nombreTutor + "\n"
				+ "\tTelefono de Contacto: " + telContacto + "\n"
				+ "\tEmail: " + emailContacto + "\n"
				+ "\tInformacion adicional: " + infoAdicional;
	}
	
}
