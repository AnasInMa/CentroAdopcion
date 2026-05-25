package test;

import modelo.Animal;
import modelo.Persona;

public class TestAnimales {

	public static void main(String[] args) {

		Animal animal1;
		try {
			animal1 = new Animal(0, "Wilson", "Gato", "Dorado Sombreado",
					"es un gato que duerme mucho, come mucho y caga mucho", (byte) 2, "12/02/2026");
			Animal animal2 = new Animal(1, "Firulais", "Perro", "Chiuaua",
					"es un perro que lleva mucho tiempo aqui, y a pesar de ladrar mucho siempre le gusta dar y recibir cariño",
					(byte) 5, "12/02/2023");
			Animal animal3 = new Animal(1, "", "Perro", "Chiuaua", null, (byte) 5, "12/02/2023");

			System.out.println(animal1);
			System.out.println(animal2);
			System.out.println(animal3.toStringSimple());

			System.out.println(animal1.equals(animal2));
			System.out.println(animal2.equals(animal3));

			animal1.esAdoptado(new Persona(1, "Pedro", "12345678A", "Sánchez", "Castejón", (byte) 54));

			System.out.println(animal1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}