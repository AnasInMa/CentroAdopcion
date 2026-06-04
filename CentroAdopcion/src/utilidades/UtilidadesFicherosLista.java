package utilidades;

import java.io.*;
import java.util.*;

import modelo.Animal;
import modelo.Persona;

public class UtilidadesFicherosLista {
	
	/**
	 * Metodo que guardara el animal recien adoptado (y eliminado de la base de datos)
	 * en una lista, junto con los animales que ya hay en esa lista (lista que se lee del fichero),
	 * para despues poder ver todos los animales que ha adoptado una persona
	 * 
	 * @param animal
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void guardarAnimalEnFichero(Animal animal, File archivoDestino) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		LinkedList<Animal> listaAnimales;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoDestino))) {
			
			listaAnimales = (LinkedList<Animal>) ois.readObject();
		}
		
		listaAnimales.add(animal);
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDestino))) {
			
			oos.writeObject(listaAnimales);
		}
	}
	
	/**
	 * Metodo que elimina un Animal del archivo designado
	 * 
	 * @param animal
	 * @param archivoDestino
	 */
	public static void quitarAnimalDelFichero(Animal animal, File archivoDestino) {
		
		LinkedList<Animal> listaAnimales = new LinkedList<>();
		//System.out.println(listaAnimales);
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoDestino))) {
	        
			listaAnimales = (LinkedList<Animal>) ois.readObject();

	    } catch (ClassNotFoundException | IOException e) {
	    	
	        e.printStackTrace();
	    }

		for (Animal animalLista : listaAnimales) {
			
			if(animalLista.equals(animal)) {
				
				listaAnimales.remove(animalLista);
			}
		}

	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDestino))) {
	    	
	        oos.writeObject(listaAnimales);
	        
	    } catch (IOException e) {
	        
	        e.printStackTrace();
	    }
	    
	    //System.out.println(listaAnimales);
	}
	
	public static String[][] leeFichero(Persona persona, File archivoDestino) throws Exception {
		
		LinkedList<Animal> listaAnimales = null;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoDestino))) {
			
			//Animal animal;
			
			listaAnimales = (LinkedList<Animal>) ois.readObject();
			
		} catch(FileNotFoundException e) {
			
			throw new FileNotFoundException("No hay ningun animal adoptado");
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}


		int cont = 0;
		
		//Bucle para saber el tamaño del jtable
		for (Animal animal : listaAnimales) {
			
			if(animal.getIDPersona() == persona.getIDPersona()) {
				
				cont++;
			}
			
		}
		
		String[][] animales = new String[cont][10];
		
		cont = 0;
		
		for (Animal animal : listaAnimales) {
			
			if(animal.getIDPersona() == persona.getIDPersona()) {
				
				animales[cont][0] = animal.getIDAnimal() + "";
				animales[cont][1] = animal.getIDCentro() + "";
				animales[cont][2] = animal.getIDPersona() + "";
				animales[cont][3] = animal.getNombre();
				animales[cont][4] = animal.getTipo();
				animales[cont][5] = animal.getRaza();
				animales[cont][6] = animal.getDescripcion();
				animales[cont][7] = animal.getEdad() + "";
				animales[cont][8] = LibFechas8.getFechaShort(animal.getFechaAlojamiento());
				animales[cont][9] = LibFechas8.getFechaShort(animal.getFechaAdopcion());
				
				cont++;				
			}
			
		}
		
		if(cont == 0) throw new Exception(persona.getNombre() + " no tiene ningun animal adoptado disponible");
		
		return animales;
	}
	
}
