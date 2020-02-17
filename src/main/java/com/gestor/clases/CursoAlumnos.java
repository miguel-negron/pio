package com.gestor.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gestor.enums.Curso;

@Entity
@Table(name="Cursos")
public class CursoAlumnos {
	
	@Id
	private Curso curso;
	
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Alumno> alumnos = new ArrayList<>();
	
	@Column(name="NUMERO_ALUMNOS")
	private int numAlumnos;
	
	@Column(name="NUMERO_MAXIMO_ALUMNOS")
	private int numMaxAlumnos;
	
	public CursoAlumnos() {
		
	}
	
	public CursoAlumnos(Curso curso) {
		this.curso = curso;
		numMaxAlumnos = 20;
		numAlumnos = 0;
	}
	
	public Curso getCurso() {
		return curso;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public List<Alumno> getAlumnos(){
		return alumnos;
	}
	
	public void addAlumno(Alumno alumno) {
		alumnos.add(alumno);
		alumno.setCursoAlumnos(this);
		this.setNumAlumnos(getNumAlumnos() + 1);
	}

	public int getNumAlumnos() {
		return numAlumnos;
	}

	public void setNumAlumnos(int numAlumnos) {
		this.numAlumnos = numAlumnos;
	}

	public int getNumMaxAlumnos() {
		return numMaxAlumnos;
	}

	public void setNumMaxAlumnos(int numMaxAlumnos) {
		this.numMaxAlumnos = numMaxAlumnos;
	}
	
	
	
	

}
