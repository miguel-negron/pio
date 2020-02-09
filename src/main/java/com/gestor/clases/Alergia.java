package com.gestor.clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ALERGIA")
@IdClass(DNIAlergia.class)
public class Alergia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name="DNI")
	private Alumno DNI;
	
	@Id
	private String alergia;
	
	
		
	

	public Alergia() {
		
	}



	public Alergia(String alergia, Alumno alumno) {
		super();
		this.alergia = alergia;
		this.DNI = alumno;
	}



	public String getAlergia() {
		return alergia;
	}



	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}



	public Alumno getAlumno() {
		return DNI;
	}



	public void setAlumno(Alumno alumno) {
		this.DNI = alumno;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alergia == null) ? 0 : alergia.hashCode());
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alergia other = (Alergia) obj;
		if (alergia == null) {
			if (other.alergia != null)
				return false;
		} else if (!alergia.equals(other.alergia))
			return false;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Alergia [alergia=" + alergia + ", alumno=" + DNI + "]";
	}

}


class DNIAlergia implements Serializable {

	private static final long serialVersionUID = 1L;
	private Alumno DNI;
    private String alergia;


    public DNIAlergia() {}

    public DNIAlergia(Alumno DNI, String Alergia) {
       this.DNI = DNI;
       this.alergia = Alergia;
    }

	public Alumno getDNI() {
		return DNI;
	}

	public String getAlergia() {
		return alergia;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		result = prime * result + ((alergia == null) ? 0 : alergia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DNIAlergia other = (DNIAlergia) obj;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		if (alergia == null) {
			if (other.alergia != null)
				return false;
		} else if (!alergia.equals(other.alergia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DNIAlergia [DNI=" + DNI + ", Alergia=" + alergia + "]";
	}
	
	
    
}
