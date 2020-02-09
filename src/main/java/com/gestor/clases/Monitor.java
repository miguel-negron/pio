package com.gestor.clases;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MONITOR")
public class Monitor implements Serializable {

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
	private LocalDate fechaNac;
	@Column(name = "CORREO")
	private String Correo;
	@Column(name = "LOCALIDAD")
	private String Localidad;
	@Column(name = "CODIGO_POSTAL")
	private String codigoPostal;
	@Column(name = "TEL_FIJO")
	private String telFijo;
	@Column(name = "TEL_MOVIL")
	private String Movil;
	@Column(name = "TEL_EMERGENCIA_1")
	private String telEmergencia1;
	@Column(name = "TEL_EMERGENCIA_2")
	private String telEmergencia2;
	@Column(name = "OCUPACION")
	private String Ocupacion;
	@Column(name = "CURSO")
	private String curso;
	@Column(name = "TIENE_TITULO")
	private boolean tieneTitulo;

	public Monitor() {

	}
	

	public Monitor(String dNI, String nombre, String apellido1, String apellido2, LocalDate fechaNac, String correo,
			String localidad, String codigoPostal, String telFijo, String movil, String telEmergencia1,
			String telEmergencia2, String ocupacion, String curso, boolean tieneTitulo) {
		super();
		DNI = dNI;
		Nombre = nombre;
		Apellido1 = apellido1;
		Apellido2 = apellido2;
		this.fechaNac = fechaNac;
		Correo = correo;
		Localidad = localidad;
		this.codigoPostal = codigoPostal;
		this.telFijo = telFijo;
		Movil = movil;
		this.telEmergencia1 = telEmergencia1;
		this.telEmergencia2 = telEmergencia2;
		Ocupacion = ocupacion;
		this.curso = curso;
		this.tieneTitulo = tieneTitulo;
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

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
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

	public String getMovil() {
		return Movil;
	}

	public void setMovil(String movil) {
		Movil = movil;
	}

	public String getTelEmergencia1() {
		return telEmergencia1;
	}

	public void setTelEmergencia1(String telEmergencia1) {
		this.telEmergencia1 = telEmergencia1;
	}

	public String getTelEmergencia2() {
		return telEmergencia2;
	}

	public void setTelEmergencia2(String telEmergencia2) {
		this.telEmergencia2 = telEmergencia2;
	}

	public String getOcupacion() {
		return Ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		Ocupacion = ocupacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Monitor [DNI=" + DNI + ", Nombre=" + Nombre + ", Apellido1=" + Apellido1 + ", Apellido2=" + Apellido2
				+ ", fechaNac=" + fechaNac + ", Correo=" + Correo + ", Localidad=" + Localidad + ", codigoPostal="
				+ codigoPostal + ", telFijo=" + telFijo + ", Movil=" + Movil + ", telEmergencia1=" + telEmergencia1
				+ ", telEmergencia2=" + telEmergencia2 + ", Ocupacion=" + Ocupacion + "]";
	}


}
