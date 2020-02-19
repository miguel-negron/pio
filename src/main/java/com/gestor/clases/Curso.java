package com.gestor.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gestor.enums.NombreCurso;

@Entity
@Table(name="Cursos")
public class Curso {
	
	@Id
	private NombreCurso curso;
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Alumno> alumnos = new ArrayList<>();
	@Column(name="NUMERO_ALUMNOS")
	private int numAlumnos;
	@Column(name="NUMERO_MAXIMO_ALUMNOS")
	private int numMaxAlumnos;
	
	//Constructores vacio y solo con nombre
	public Curso() {
		
	}
	
	public Curso(NombreCurso curso) {
		this.curso = curso;
		numMaxAlumnos = 20;
		setNumAlumnosAuto();
	}
	
	//Getters y setters
	public NombreCurso getNombreCurso() {
		return curso;
	}
	
	public void setNombreCurso(NombreCurso curso) {
		this.curso = curso;
	}
	
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Alumno> getAlumnos(){
		return alumnos;
	}
	
	public void addAlumno(Alumno alumno) {
		this.alumnos.add(alumno);
		alumno.setCursoAlumnos(this);
		this.setNumAlumnosAuto();
	}

	public int getNumAlumnos() {
		return numAlumnos;
	}

	public void setNumAlumnosAuto() {
		this.numAlumnos = this.alumnos.size();
	}

	public int getNumMaxAlumnos() {
		return numMaxAlumnos;
	}

	public void setNumMaxAlumnos(int numMaxAlumnos) {
		this.numMaxAlumnos = numMaxAlumnos;
	}

	@Override
	public String toString() {
		//si metemos + alumnos.size()  falla
		return "Curso [curso=" + curso + ", alumnos=" + ", numAlumnos=" + numAlumnos + ", numMaxAlumnos="
				+ numMaxAlumnos + "]";
	}
	
	
	
	

}
