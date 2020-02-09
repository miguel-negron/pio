package com.gestor.clases;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class Alergia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	private int ID;
	private String DNI;
	@Column(name="NOMBRE")
	private String Nombre;
	@Column(name="APELLIDO_1")
	private String Apellido1;
	

}
