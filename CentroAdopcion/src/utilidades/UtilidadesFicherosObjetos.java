package utilidades;

import java.io.*;
import java.util.*;

import javax.swing.*;

import modelo.Animal;
import modelo.Persona;

public class UtilidadesFicherosObjetos {


	/**
	 * Metodo que guardara el animal recien adoptado (y eliminado de la base de datos)
	 * en un fichero, para despues poder ver todos los animales que ha adoptado una persona
	 * 
	 * @param animal
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void guardarAnimalEnFicheroObjetos(Animal animal, File archivoDestino) throws FileNotFoundException, IOException {
		
		//System.out.println(Vista.archivoAnimalesAdoptados.getPath());
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDestino, true))) {
			
			//System.out.println("controlador" + animal);
			
			oos.writeObject(animal);
		}
	}

	public static void quitarAnimalDelFicheroObjetos(Animal animal, File archivoDestino, JDialog dialogo) {

		LinkedList<Animal> listaAnimales = new LinkedList<>();
		// System.out.println(listaAnimales);

		// se añaden todos los animales menos el que se pasa como parametro a la
		// listaAnimales
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoDestino))) {

			Animal anim;

			while (true) {

				anim = (Animal) ois.readObject();

				if (!anim.equals(animal)) {

					listaAnimales.add(anim);
				}

			}

		} catch (EOFException e) {

			// e.printStackTrace();
			JOptionPane.showMessageDialog(dialogo, "Has elegido a " + animal.getNombre(), "",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (ClassNotFoundException | IOException e) {

			// e.printStackTrace();
			System.err.println(e.getMessage());
		}

		// listaAnimales.remove(animal);

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDestino))) {

			for (Animal animal2 : listaAnimales) {

				oos.writeObject(animal2);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		// System.out.println(listaAnimales);
	}

	public static String[][] leeFicheroObjetos(Persona persona, File archivoDestino) throws Exception {

		LinkedList<Animal> listaAnimales = new LinkedList<Animal>();
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoDestino))) {
			
			while(true) {
				
				listaAnimales.add((Animal) ois.readObject());
			}
			
		} catch (IOException e) {
			
			//e.printStackTrace();
			
			if(listaAnimales.size() == 0) throw new Exception(persona.getNombre() + " no tiene ningun animal adoptado disponible");
			
			String[][] matrizAnimales = new String[listaAnimales.size()][10];
			
			Animal animal;
			Iterator<Animal> iterator = listaAnimales.iterator();
			
			for (int i = 0; i < matrizAnimales.length; i++) {
				
				animal = (Animal) iterator.next();
				
				matrizAnimales[i][0] = animal.getIDAnimal() + "";
				matrizAnimales[i][1] = animal.getIDCentro() + "";
				matrizAnimales[i][2] = animal.getIDPersona() + "";
				matrizAnimales[i][3] = animal.getNombre();
				matrizAnimales[i][4] = animal.getTipo();
				matrizAnimales[i][5] = animal.getRaza();
				matrizAnimales[i][6] = animal.getDescripcion();
				matrizAnimales[i][7] = animal.getEdad() + "";
				matrizAnimales[i][8] = LibFechas8.getFechaShort(animal.getFechaAlojamiento());
				matrizAnimales[i][9] = LibFechas8.getFechaShort(animal.getFechaAdopcion());
			}
			
			return matrizAnimales;
		}
	}
	
}
