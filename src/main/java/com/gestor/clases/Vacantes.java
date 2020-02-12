package com.gestor.clases;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VACANTES")
public class Vacantes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "CURSO")
	private Curso curso;
	@Column(name = "VACANTES")
	private int vacantes;

	//Constructores
	public Vacantes() {}

	public Vacantes(Curso curso, int vacantes) {
		super();
		this.curso = curso;
		this.vacantes = vacantes;
	}

	//Auto ++ y --
	public void anyadeVacante() {
		this.setVacantes(this.getVacantes() + 1);
	}
	
	public void reduceVacante() {
		this.setVacantes(this.getVacantes() - 1);
	}
	
	
	//Getters and Setters
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public int getVacantes() {
		return vacantes;
	}

	public void setVacantes(int vacantes) {
		this.vacantes = vacantes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
