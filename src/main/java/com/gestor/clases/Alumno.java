package com.gestor.clases;

import java.io.Serializable;
import java.util.Date;

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
	private Date fechaNac;
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="DNI_TUTOR")
	//@Column(name="DNI_TUTOR")
	private Tutor Tutor;
	@Column(name="FICHA_ENTREGADA")
	private boolean fichaEntregada;
	@Column(name="FOTO_ENTREGADA")
	private boolean fotoEntregada;
	@Column(name="CURSO")
	private String Curso;
	
	public Alumno() {
		
	}



	public Alumno(String dNI, String nombre, String apellido1, String apellido2, Date fechaNac,
			com.gestor.clases.Tutor tutor, boolean fichaEntregada, boolean fotoEntregada, String curso) {
		super();
		DNI = dNI;
		Nombre = nombre;
		Apellido1 = apellido1;
		Apellido2 = apellido2;
		this.fechaNac = fechaNac;
		Tutor = tutor;
		this.fichaEntregada = fichaEntregada;
		this.fotoEntregada = fotoEntregada;
		Curso = curso;
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

	public String getCurso() {
		return Curso;
	}

	public void setCurso(String curso) {
		Curso = curso;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "Alumno [DNI=" + DNI + ", Nombre=" + Nombre + ", Apellido1=" + Apellido1 + ", Apellido2=" + Apellido2
				+ ", fechaNac=" + fechaNac + ", Tutor=" + Tutor + ", fichaEntregada=" + fichaEntregada
				+ ", fotoEntregada=" + fotoEntregada + ", Curso=" + Curso + "]";
	}



	public Tutor getTutor() {
		return Tutor;
	}

	public void setTutor(Tutor tutor) {
		Tutor = tutor;
	}



	

	
	

}
