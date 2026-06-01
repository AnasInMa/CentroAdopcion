package modelo;

import java.io.Serializable;
import java.util.*;

public class Persona implements Serializable{

	private static final long serialVersionUID = -4039320836185613290L;
	
	private SortedSet<Animal> animalesAdoptados;	//Va a ser un SortedSet, ya que se ordenaran por la fecha de adopcion y ademas no habran repetidos
	private String nombre, nif, primerApellido, segundoApellido;
	private int idPersona;
	private byte edad;
	
	public Persona(int cod, TreeSet<Animal> animales, String nombre, String nif, String apellido1, String apellido2, byte edad) {
		
		this.idPersona = cod;
		animalesAdoptados = animales;
		this.nombre = nombre;
		this.nif = nif;
		this.primerApellido = apellido1;
		this.segundoApellido = apellido2;
		this.edad = edad;
	}
	
	public Persona(int cod, String nombre, String nif, String apellido1, String apellido2, byte edad) {
		
		this(cod, new TreeSet<Animal>(), nombre, nif, apellido1, apellido2, edad);
	}
	
	public static void validaDni(String dni) throws Exception {
		
		Exception ex = new Exception("Dni no valido");
		
		if(dni == null || dni.length() != 9 ) {
			
			throw ex;
		}
		
		String numeros = "";
		
		for (int i = 0; i < dni.length() - 1; i++) {
			
			numeros += dni.charAt(i);
		}
		
		//System.out.println("1: " +numeros);
		
		try {
			
			Integer.parseInt(numeros);
			
		} catch (NumberFormatException e) {
			
			throw ex;
		}
		
		//System.out.println("2: " + dni);
		//System.out.println("3: " + dni.charAt(8));
		
		if(!Character.isLetter(dni.charAt(8))) throw ex;
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

			boolean estaAdoptado = false;

			for (Animal anim : animalesAdoptados) {

				if (anim.equals(animal))
					estaAdoptado = true;
			}

			if (!estaAdoptado) {
				
				animal.esAdoptado(idPersona);
				
				animalesAdoptados.add(animal);
			}
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
	
	public byte getEdad() {
		return edad;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public int getIDPersona() {
		return idPersona;
	}
}
