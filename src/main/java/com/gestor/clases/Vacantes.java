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
	@Column(name = "IDCURSO")
	private int IDCurso;
	@Column(name = "VACANTES")
	private int Vacantes;

	public Vacantes() {

	}

	@Override
	public String toString() {
		return "Vacantes [IDCurso=" + IDCurso + ", Vacantes=" + Vacantes + "]";
	}

	public int getIDCurso() {
		return IDCurso;
	}

	public void setIDCurso(int iDCurso) {
		IDCurso = iDCurso;
	}

	public int getVacantes() {
		return Vacantes;
	}

	public void setVacantes(int vacantes) {
		Vacantes = vacantes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Vacantes(int iDCurso, int vacantes) {
		super();
		IDCurso = iDCurso;
		Vacantes = vacantes;
	}

}
