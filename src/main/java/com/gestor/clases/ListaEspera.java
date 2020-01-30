package com.gestor.clases;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LISTA_DE_ESPERA")
public class ListaEspera implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "DNI")
	private String DNI;
	@Column(name = "NOMBRE")
	private String Nombre;
	@Column(name = "APELLIDO_1")
	private String Apellido1;
	@Column(name = "APELLIDO_2")
	private String Apellido2;
	@Column(name = "FECHA_NACIMIENTO")
	private Date fechaNac;
	@Column(name = "DNI_TUTOR_1")
	private String DNITutor1;
	@Column(name = "DNI_TUTOR_2")
	private String DNITutor2;
	@Column(name = "DISPONIBILIDAD")
	private Date Disponibilidad;
	@Column(name = "TEL_FAMILIAR")
	private String telFamiliar;

	public ListaEspera() {

	}

	public ListaEspera(String dNI, String nombre, String apellido1, String apellido2, Date fechaNac, String dNITutor1,
			String dNITutor2, Date disponibilidad, String tel_familiar) {
		DNI = dNI;
		Nombre = nombre;
		Apellido1 = apellido1;
		Apellido2 = apellido2;
		this.fechaNac = fechaNac;
		DNITutor1 = dNITutor1;
		DNITutor2 = dNITutor2;
		Disponibilidad = disponibilidad;
		this.telFamiliar = tel_familiar;
	}

	@Override
	public String toString() {
		return "Lista_Espera [DNI=" + DNI + ", Nombre=" + Nombre + ", Apellido1=" + Apellido1 + ", Apellido2="
				+ Apellido2 + ", fechaNac=" + fechaNac + ", DNITutor1=" + DNITutor1 + ", DNITutor2=" + DNITutor2
				+ ", Disponibilidad=" + Disponibilidad + ", tel_familiar=" + telFamiliar + "]";
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
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

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getDNITutor1() {
		return DNITutor1;
	}

	public void setDNITutor1(String dNITutor1) {
		DNITutor1 = dNITutor1;
	}

	public String getDNITutor2() {
		return DNITutor2;
	}

	public void setDNITutor2(String dNITutor2) {
		DNITutor2 = dNITutor2;
	}

	public Date getDisponibilidad() {
		return Disponibilidad;
	}

	public void setDisponibilidad(Date disponibilidad) {
		Disponibilidad = disponibilidad;
	}

	public String getTel_familiar() {
		return telFamiliar;
	}

	public void setTel_familiar(String tel_familiar) {
		this.telFamiliar = tel_familiar;
	}

}
