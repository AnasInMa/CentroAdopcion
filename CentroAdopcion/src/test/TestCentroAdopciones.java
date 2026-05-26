package test;

import modelo.Animal;
import modelo.CentroAdopcion;

public class TestCentroAdopciones {

	public static void main(String[] args) {
		
		CentroAdopcion centro1 = new CentroAdopcion(0, "Arca del Torcal", "Avenida José María Fernández", 213456, 29200, (short) 20);
		
		System.out.println(centro1.toStringSimple());
		System.out.println(centro1.toString());
		
		try {
			centro1.alojaAnimal(new Animal(234123, "Wilson", "Gato", "Dorado Sombreado", "es un gato que duerme mucho, come mucho y caga mucho", (byte) 2, "12/02/2026", centro1.getIDCentro()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(centro1);
	}
}
