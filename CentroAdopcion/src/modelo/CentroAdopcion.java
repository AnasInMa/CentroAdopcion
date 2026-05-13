package modelo;

import java.util.*;

public class CentroAdopcion {

	private String nombre, direccion;
	private int codigo, codigoPostal, codigoCentro;
	private short capacidadMaxima;
	private SortedSet<Animal> animalesAlojados;
	
	public CentroAdopcion(int cod, String nombre, String direccion, int codigoCentro, int codPostal, short capacidadMaxima, TreeSet<Animal> animalesAlojados) {

		this.codigo = cod;
		this.nombre = nombre;
		this.direccion = direccion;
		this.codigoCentro = codigoCentro;
		this.codigoPostal = codPostal;
		this.capacidadMaxima = capacidadMaxima;
		this.animalesAlojados = animalesAlojados;
	}
	
	public CentroAdopcion(int cod, String nombre, String direccion, int codigoCentro, int codPostal, short capacidadMaxima) {

		this(cod, nombre, direccion, codigoCentro, codPostal, capacidadMaxima, new TreeSet<Animal>());
	}
	
	/**
	 * Metodo que aloja a un animal en el centro de adopcion, si se ha podido alojar
	 * devuelve verdadero, si no, falso, no sin antes comprobar que no se ha llegado
	 * aún a la capacidad máxima
	 * 
	 * @param animal
	 * @return boolean
	 */
	public boolean alojaAnimal(Animal animal) {
		
		return (animal == null)? false	//comprueba que el animal que se pasa exista, si no existe devuelve un nulo
				: (this.animalesAlojados.size() < capacidadMaxima? this.animalesAlojados.add(animal) : false);	//si existe comprueba que se puede alojar el animal y lo aloja
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigoCentro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CentroAdopcion other = (CentroAdopcion) obj;
		return codigoCentro == other.codigoCentro;
	}

	public String toStringSimple() {
		
		return this.codigoCentro + ": " + this.nombre + ", " + this.direccion + " (" + this.codigoPostal + ")";
	}
	
	public String toStringSinNombre() {
		
		return this.codigoCentro + ": " + this.direccion + ", " + this.codigoPostal + " | " + this.animalesAlojados.size() + "/" + this.capacidadMaxima + " animales alojados";
	}
	
	@Override
	public String toString() {
		
		String cadena = toStringSimple();
		
		int numAnimales;
		
		if((numAnimales = animalesAlojados.size()) > 0) {
			
			cadena += "\n\tAnimales alojados (" + numAnimales + "/" + this.capacidadMaxima + "): ";
			
			int cont = numAnimales - 1;	//contador para que el bucle termine en el penultimo animal, y que asi no termine la cadena en ','
			
			for (Iterator<Animal> iterator = animalesAlojados.iterator(); cont > 0;) {
				
				cadena += iterator.next().toStringSimple() + ", ";
				
				cont--;
			}
			
			cadena += animalesAlojados.last().toStringSimple();
			
		} else cadena += "\n\tAún no hay alojado ningun animal";
		
		return cadena;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public int getCodigoCentro() {
		return codigoCentro;
	}

	public short getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public SortedSet<Animal> getAnimalesAlojados() {
		return animalesAlojados;
	}

}
