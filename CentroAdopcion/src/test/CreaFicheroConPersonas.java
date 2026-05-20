package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import modelo.Persona;

public class CreaFicheroConPersonas {

	public static void main(String[] args) {

		Persona p0 = new Persona(0, "Pedro", "12345678A", "Sánchez", "Castejón", (byte) 54);
		Persona p1 = new Persona(1, "María", "23456789B", "García", "López", (byte) 32);
		Persona p2 = new Persona(2, "Juan", "34567890C", "Martínez", "Ruiz", (byte) 47);
		Persona p3 = new Persona(3, "Laura", "45678901D", "Fernández", "Torres", (byte) 28);
		Persona p4 = new Persona(4, "Carlos", "56789012E", "Rodríguez", "Vega", (byte) 61);
		Persona p5 = new Persona(5, "Ana", "67890123F", "González", "Moreno", (byte) 39);
		Persona p6 = new Persona(6, "Diego", "78901234G", "López", "Navarro", (byte) 22);
		Persona p7 = new Persona(7, "Isabel", "89012345H", "Muñoz", "Serrano", (byte) 55);
		Persona p8 = new Persona(8, "Roberto", "90123456I", "Pérez", "Jiménez", (byte) 43);
		Persona p9 = new Persona(9, "Sofía", "01234567J", "Castro", "Blanco", (byte) 17);

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File("./src/modelo/Personas.dat"), false))) {
			
			oos.writeObject(p0);
			oos.writeObject(p1);
			oos.writeObject(p2);
			oos.writeObject(p3);
			oos.writeObject(p4);
			oos.writeObject(p5);
			oos.writeObject(p6);
			oos.writeObject(p7);
			oos.writeObject(p8);
			oos.writeObject(p9);
			
			System.out.println("Fichero creado");
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}

	}

}
