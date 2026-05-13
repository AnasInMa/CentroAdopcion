package test;

import modelo.Animal;
import modelo.Persona;

public class TestPersonas {

	public static void main(String[] args) {
		
		Persona persona1 = new Persona(0, "Pedro", "12345678A", "Sanchez", "Castejon", (byte) 54);
		
		System.out.println(persona1);
		
		persona1.adoptaAnimal(new Animal(0, "Firulais", "Perro", "Chiuaua", "es un perro que lleva mucho tiempo aqui, y a pesar de ladrar mucho siempre le gusta dar y recibir cariño", (byte) 5, "12-02-2023"));
		
		System.out.println(persona1);

		persona1.adoptaAnimal(new Animal(1, "Wilson", "Gato", "Dorado Sombreado", "es un gato que duerme mucho, come mucho y caga mucho", (byte) 2, "12-02-2026"));
		
		System.out.println(persona1);
	}

}
