package com.gestor.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gestor.enums.Alergeno;

@Entity
@Table(name="ALERGIAS")
public class Alergia {
	@Id
	private Alergeno alergeno;
	@ManyToMany(mappedBy = "alergias", fetch = FetchType.EAGER)
	private List<Alumno> alumnosAlergicos = new ArrayList<>();
	//@ManyToMany(mappedBy = "alergias")
	//private List<Alumno> monitoresAlergicos = new ArrayList<>();
	
	public Alergia() {
		super();
	}
	
	public Alergia(Alergeno alergia) {
		super();
		this.alergeno = alergia;
	}

	public Alergeno getAlergeno() {
		return alergeno;
	}

	public List<Alumno> getAlumnosAlergicos() {
		return alumnosAlergicos;
	}
	
	public void addAlumno(Alumno alumno) {
		this.alumnosAlergicos.add(alumno);
		alumno.getAlergias().add(this);
	}
	
	public void removeAlumno(Alumno alumno) {
		this.alumnosAlergicos.remove(alumno);
		alumno.getAlergias().remove(this);
	}
	
}
