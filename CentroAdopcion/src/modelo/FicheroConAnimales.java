package modelo;

import java.io.*;

public class FicheroConAnimales {

	public static void creaFicheroAnimales() {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File("./files/Animales.dat"), false))) {
			
		Animal[] animales = {
				new Animal(0, "Wilson", "Gato", "Dorado Sombreado",
						"es un gato que duerme mucho, come mucho y caga mucho", (byte) 2, "12/02/2025", 1),
				new Animal(1, "Firulais", "Perro", "Chiuaua",
						"es un perro que lleva mucho tiempo aqui, y a pesar de ladrar mucho siempre le gusta dar y recibir cariño",
						(byte) 5, "12/02/2023", 1),
				new Animal(2, "", "Pajaro", "Periquito", "es un pajaro que...", (byte) 1, "12/02/2026", 2),
				new Animal(3, "", "Conejo", "Holandes", "es un conejo que...", (byte) 1, "12/02/2026", 2),
				new Animal(4, "Adolfo", "Perro", "Pastor Aleman", "es un perro que...", (byte) 1, "12/02/2026", 4),
				new Animal(5, "Luna", "Gato", "Siamés", "es una gata muy cariñosa y juguetona...", (byte) 2,
						"15/04/2024", 4),
				new Animal(6, "Rocky", "Perro", "Golden Retriever", "es un perro amigable, ideal para familias...",
						(byte) 3, "20/08/2023", 4),
				new Animal(7, "Coco", "Loro", "Yaco", "es un loro muy inteligente que imita sonidos...", (byte) 1,
						"05/11/2025", 3),
				new Animal(8, "Bella", "Conejo", "Angora", "es una coneja tranquila con un pelaje muy suave...",
						(byte) 0, "10/01/2026", 3)};
		
			
			for (int i = 0; i < animales.length; i++) {
				
				oos.writeObject(animales[i]);
			}

			System.out.println("Fichero animales creado");

		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (Exception e1) {
			
			System.err.println(e1.getMessage());
		}
	}

}
