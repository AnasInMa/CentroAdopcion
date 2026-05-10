package modelo;

import java.util.*;

public class Persona {

	private SortedSet<Animal> animalesAdoptados;	//Va a ser un SortedSet, ya que se ordenaran por la fecha de adopcion y ademas no habran repetidos
	private String nombre, nif, primerApellido, segundoApellido;
	private byte edad;
	
	public Persona(TreeSet<Animal> animales, String nombre, String nif, String apellido1, String apellido2, byte edad) {
		
		animalesAdoptados = animales;
		this.nombre = nombre;
		this.nif = nif;
		this.primerApellido = apellido1;
		this.segundoApellido = apellido2;
		this.edad = edad;
	}
	
	public Persona(String nombre, String nif, String apellido1, String apellido2, byte edad) {
		
		this(new TreeSet<Animal>(), nombre, nif, apellido1, apellido2, edad);
	}
	
	/**
	 * Metodo que comprueba la edad de la persona para saber si puede adoptar a un animal o no,
	 * si es menor devuelve falso, de lo contrario devolvera verdadero
	 * 
	 * @return boolean
	 */
	private boolean puedeAdoptar() {
		
		return edad < 18? false : true;
	}

	/**
	 * Metodo que añade un animal a la coleccion de la persona,
	 * validando antes que la persona pueda adoptar a un animal, con el metodo puedeAdoptar()
	 * 
	 * @param animal
	 */
	public void adoptaAnimal(Animal animal) {

		if (puedeAdoptar()) {

			boolean adoptado = false;

			for (Animal anim : animalesAdoptados) {

				if (anim.equals(animal))
					adoptado = true;
			}

			if (!adoptado) animalesAdoptados.add(animal);
		}
	}
	
	@Override
	public String toString() {
		
		String cadena = nif + ": " + nombre + " " + primerApellido + " " + segundoApellido + ", " + edad + " años";
		
		if(animalesAdoptados.size() > 0) {
			
			cadena += "\n\tAnimales adoptados: ";
			
			int cont = animalesAdoptados.size() - 1;	//contador para que el bucle termine en el penultimo animal, y que asi no termine la cadena en ','
			
			for (Iterator<Animal> iterator = animalesAdoptados.iterator(); cont > 0;) {
				
				cadena += iterator.next().toStringSimple() + ", ";
				
				cont--;
			}
			
			cadena += animalesAdoptados.last().toStringSimple();
			
		} else cadena += "\n\tAún no ha adoptado a ningun animal";
		
		return cadena;
	}

	public SortedSet<Animal> getAnimalesAdoptados() {
		return animalesAdoptados;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNif() {
		return nif;
	}

	public String getPrimer_apellido() {
		return primerApellido;
	}

	public String getSegundo_apellido() {
		return segundoApellido;
	}

	public byte getEdad() {
		return edad;
	}
}
