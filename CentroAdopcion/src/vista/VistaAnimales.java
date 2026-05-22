package vista;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import modelo.Animal;
import modelo.CentroAdopcion;

public class VistaAnimales extends JPanel{
	
	private static final long serialVersionUID = 3726098725460425172L;
	
	private CentroAdopcion centroAdopcion;
	private JPanel[][] matrizPaneles;
	private static int contFilas;
	private CardLayout cartas;
	
	static {
		
		contFilas = 0;
	}

	public VistaAnimales(CentroAdopcion centro) {
		
		this.setLayout(cartas = new CardLayout());
		
		centroAdopcion = centro;
		
		matrizPaneles = matrizPanelesAnimales();
	}
	
	private JPanel[][] matrizPanelesAnimales() {
		
		int filas = (int) Math.ceil(centroAdopcion.getAnimalesAlojados().size() / 4);	// si tiene 6 animales habran 2 filas, si
																						// hay 13 animales habran 4 filas, si
																						// hay 3 animales solo habra 1 fila
		int columnas = filas / centroAdopcion.getCapacidadMaxima();
		
		JPanel[][] matrizPaneles = new JPanel[filas][columnas];
		JPanel panel;
		Animal animal;
		
		for (int i = 0, j = 0; i < filas; i++) {
			
			for (Iterator<Animal> iterador = centroAdopcion.getAnimalesAlojados().iterator() ; j < columnas; j++) {
				
				panel = (matrizPaneles[i][j] = new JPanel());
				animal = iterador.next();
				
				panel.add(new JLabel(animal.getNombre()));
				
				this.add(panel, j); 	//le pongo el contador para que despues pueda acceder a las cartas del cardLayout
			}
		}
		
		return matrizPaneles;
	}
	
	public void siguienteFila() {
		
		//if()
		contFilas++;
	}
	
	public JPanel[] muestraFilaPanelesAnimales() {
		
		JPanel[] arrayPaneles = new JPanel[contFilas];
		
		for (int i = 0; i < arrayPaneles.length; i++) {
			
		}
		
		return arrayPaneles;
	}

}
