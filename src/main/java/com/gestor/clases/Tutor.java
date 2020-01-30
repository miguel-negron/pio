package com.gestor.clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Tutor")
@Table(name="TUTOR")
public class Tutor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="DNI_TUTOR")
	private String DNI;
	@OneToMany(mappedBy="Tutor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//Puede que sea otro import(List)
	private List<Alumno> alumnos = new ArrayList<>();
	@Column(name="NOMBRE")
	private String Nombre;
	@Column(name="APELLIDO_1")
	private String Apellido1;
	@Column(name="APELLIDO_2")
	private String Apellido2;
	@Column(name="DIRECCION")
	private String Direccion;
	@Column(name="LOCALIDAD")
	private String Localidad;
	@Column(name="CODIGO_POSTAL")
	private String codigoPostal;
	@Column(name="TEL_FIJO")
	private String telFijo;
	@Column(name="TEL_MOVIL")
	private String telMovil;
	@Column(name="EMAIL")
	private String Email;
	
	public Tutor() {
		
	}

	public Tutor(String DNI, String nombre, String apellido1, String apellido2, String direccion,
			String localidad, String codigoPostal, String telFijo, String telMovil, String email) {
		super();
		this.DNI = DNI;
		Nombre = nombre;
		Apellido1 = apellido1;
		Apellido2 = apellido2;
		Direccion = direccion;
		Localidad = localidad;
		this.codigoPostal = codigoPostal;
		this.telFijo = telFijo;
		this.telMovil = telMovil;
		Email = email;
	}
	
	public void addAlumno(Alumno alumno) {
		alumnos.add(alumno);
		alumno.setTutor(this);
	}
	
	public void removeAlumno(Alumno alumno) {
		alumnos.remove(alumno);
		alumno.setTutor(null);
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
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

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getTelFijo() {
		return telFijo;
	}

	public void setTelFijo(String telFijo) {
		this.telFijo = telFijo;
	}

	public String getTelMovil() {
		return telMovil;
	}

	public void setTelMovil(String telMovil) {
		this.telMovil = telMovil;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Tutor [DNI=" + DNI + ", Nombre=" + Nombre + ", Apellido1=" + Apellido1 + ", Apellido2=" + Apellido2
				+ ", Direccion=" + Direccion + ", Localidad=" + Localidad + ", codigoPostal=" + codigoPostal
				+ ", telFijo=" + telFijo + ", telMovil=" + telMovil + ", Email=" + Email + "]";
	}



	

}
