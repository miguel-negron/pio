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
	@Column(name = "PLAZAS_LIBRES")
	private int plazasOcupadas;
	@Column(name = "LIMITE")
	private int limite;

	//Constructores
	public Vacantes() {}

	public Vacantes(NombreCurso curso, int plazas, int limite) {
		super();
		this.curso = curso;
		this.plazasOcupadas = plazas;
		this.limite = limite;
	}

	//Auto ++ y --
	public boolean anyadePlazaOcupada() {
		boolean limiteAlcanzado = false;
		if(plazasOcupadas == limite) {
			//Si todas las plazas están ocupadas, metemos al alumno en la lista de espera
			limiteAlcanzado = true;
		}else {
		this.setPlazas(this.getPlazas() + 1);
		}
		return limiteAlcanzado;
	}
	
	public void reducePlazaOcupada() {
		this.setPlazas(this.getPlazas() - 1);
	}
	
	
	//Getters and Setters
	public NombreCurso getCurso() {
		return curso;
	}

	public void setCurso(NombreCurso curso) {
		this.curso = curso;
	}

	public int getPlazas() {
		return plazasOcupadas;
	}

	public void setPlazas(int plazas) {
		plazasOcupadas = plazas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Vacantes: curso= " + curso + ", Plazas ocupadas= " + plazasOcupadas + ", limite= " + limite + ".";
	}

	

}
