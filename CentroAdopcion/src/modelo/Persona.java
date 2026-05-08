package modelo;

import java.util.*;

public class Persona {

	private SortedSet<Animal> animalesAdoptados;	//Va a ser un SortedSet, ya que se ordenaran por la fecha de adopcion y ademas no habran repetidos
	private String nombre, nif, primer_apellido, segundo_apellido;
	private byte edad;
	
	public Persona(TreeSet<Animal> animales, String nombre, String nif, String apellido1, String apellido2, byte edad) {
		
		animalesAdoptados = animales;
	}
	
	public Persona(String nombre, String nif, String apellido1, String apellido2, byte edad) {
		
		this(new TreeSet<Animal>(), nombre, nif, apellido1, apellido2, edad);
	}
	
	@Override
	public String toString() {
		
		return "";
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
		return primer_apellido;
	}

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public byte getEdad() {
		return edad;
	}
}
