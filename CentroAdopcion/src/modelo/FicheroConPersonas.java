package modelo;

import java.io.*;

public class FicheroConPersonas {

	public static void creaFicheroPersonas() {
		
		Persona p0 = new Persona(1, "Pedro", "12345678A", "Abascal", "Garcia", (byte) 54);
		Persona p1 = new Persona(2, "María", "23456789B", "García", "López", (byte) 32);
		Persona p2 = new Persona(3, "Juan", "34567890C", "Martínez", "Ruiz", (byte) 47);
		Persona p3 = new Persona(4, "Laura", "45678901D", "Fernández", "Torres", (byte) 28);
		Persona p4 = new Persona(5, "Carlos", "56789012E", "Rodríguez", "Vega", (byte) 61);
		Persona p5 = new Persona(6, "Ana", "67890123F", "González", "Moreno", (byte) 39);
		Persona p6 = new Persona(7, "Diego", "78901234G", "López", "Navarro", (byte) 22);
		Persona p7 = new Persona(8, "Isabel", "89012345H", "Muñoz", "Serrano", (byte) 55);
		Persona p8 = new Persona(9, "Roberto", "90123456I", "Pérez", "Jiménez", (byte) 43);
		Persona p9 = new Persona(10, "Sofía", "01234567J", "Castro", "Blanco", (byte) 18);

		Persona[] personas = {p0, p1, p2, p3, p4, p5, p6, p7, p8, p9};
				
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File("./files/Personas.dat"), false))) {
			
			for (int i = 0; i < personas.length; i++) {
				
				oos.writeObject(personas[i]);
			}
			
			System.out.println("Fichero personas creado");
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
