package com.gestor.clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ALERGIA")
public class Alergia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId()
	private DNIAlergia NombreAlergia;
	@OneToMany(mappedBy="Alergia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//Puede que sea otro import(List)
	private List<Alumno> alumnos = new ArrayList<>();
		
	public Alergia() {
		
	}
	
	public Alergia(DNIAlergia nombreAlergia) {
		super();
		NombreAlergia = nombreAlergia;
	}

	public DNIAlergia getNombreAlergia() {
		return NombreAlergia;
	}

	@Override
	public String toString() {
		return "Alergia [NombreAlergia=" + NombreAlergia + "]";
	}
	
	

}

@Embeddable
class DNIAlergia implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String DNI;
    private String Alergia;

    public DNIAlergia() {}

    public DNIAlergia(String DNI, String Alergia) {
       this.DNI = DNI;
       this.Alergia = Alergia;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Alergia == null) ? 0 : Alergia.hashCode());
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
		DNIAlergia other = (DNIAlergia) obj;
		if (Alergia == null) {
			if (other.Alergia != null)
				return false;
		} else if (!Alergia.equals(other.Alergia))
			return false;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		return true;
	}

	public String getDNI() {
		return DNI;
	}

	public String getAlergia() {
		return Alergia;
	}

	@Override
	public String toString() {
		return "DNIAlergia [DNI=" + DNI + ", Alergia=" + Alergia + "]";
	}
	
	
    
}
