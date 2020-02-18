package com.gestor.clases;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gestor.enums.NombreCurso;

@Entity
@Table(name = "VACANTES")
public class Vacantes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	@Id
	@Column(name = "CURSO")
	private NombreCurso curso;
	@Column(name = "VACANTES")
	private int vacantes;
	@Column(name = "LIMITE")
	private int limite;

	//Constructores
	public Vacantes() {}

	public Vacantes(NombreCurso curso, int vacantes, int limite) {
		super();
		this.curso = curso;
		this.vacantes = vacantes;
		this.limite = limite;
	}

	//Auto ++ y --
	public void anyadeVacante() {
		this.setVacantes(this.getVacantes() + 1);
	}
	
	public void reduceVacante() {
		this.setVacantes(this.getVacantes() - 1);
	}
	
	
	//Getters and Setters
	public NombreCurso getCurso() {
		return curso;
	}

	public void setCurso(NombreCurso curso) {
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

	@Override
	public String toString() {
		return "Vacantes: curso=" + curso + ", vacantes=" + vacantes + ", limite= " + limite + ".";
	}

	

}
