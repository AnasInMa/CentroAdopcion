package vista;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.JPopupMenu.Separator;
import javax.swing.border.LineBorder;

import modelo.Animal;
import modelo.CentroAdopcion;

public class VistaAnimales extends JPanel {
	
	private static final long serialVersionUID = 3726098725460425172L;
	
	private CentroAdopcion centroAdopcion;
	private JPanel[][] matrizPaneles;
	private JPanel[] arrayPaneles; 	//array de paneles en los que en cada uno habran metidos los 4 paneles con los animales
	private static int contFilas;
	private static final float COLUMNAS;
	private CardLayout cartas;
	private int filas;
	private JRadioButton rbAnimal1, rbAnimal2, rbAnimal3, rbAnimal4; 	//como siempre van a haber 4 paneles de animales visibles, entonces puedo acceder al animal q se quiera adoptar siempre que este el animal visible
	
	static {
		
		contFilas = 0;
		
		COLUMNAS = 4;
	}

	public VistaAnimales(CentroAdopcion centro) {
		
		this.setLayout(cartas = new CardLayout());
		this.setBackground(Vista.MARRON_CLARO3);
		
		centroAdopcion = centro;
		
		//matrizPaneles = matrizPanelesAnimales();
		if(centro.getAnimalesAlojados().size() > 0) matrizPanelesAnimales();
	}
	
	private void iniciaRBotones() {
		
		
		this.rbAnimal1 = new JRadioButton("Elegir animal");
		this.rbAnimal2 = new JRadioButton("Elegir animal");
		this.rbAnimal3 = new JRadioButton("Elegir animal");
		this.rbAnimal4 = new JRadioButton("Elegir animal");
		
		ButtonGroup grupoRB = new ButtonGroup();
		
		JRadioButton[] rBotones = {rbAnimal1, rbAnimal2, rbAnimal3, rbAnimal4};
		
		for (JRadioButton rboton : rBotones) {
			
			rboton.setFocusable(false);
			rboton.setAlignmentX(JRadioButton.LEFT_ALIGNMENT);
			rboton.setBackground(Vista.MARRON_CLARO3);
			grupoRB.add(rboton);
		}
	}
	
	private void matrizPanelesAnimales() {
		
		filas = (int) Math.ceil(centroAdopcion.getAnimalesAlojados().size() / COLUMNAS);	// si tiene 6 animales habran 2 filas, si
																							// hay 13 animales habran 4 filas, si
																							// hay 3 animales solo habra 1 fila
		int columnas = (int) COLUMNAS;
		
		iniciaRBotones();
		
		this.matrizPaneles = new JPanel[filas][columnas];
		this.arrayPaneles = new JPanel[filas];
		JPanel panel;
		Animal animal;
		
		LineBorder bordeLinea = new LineBorder(Vista.MARRON_OSCURO3, 4);
		
		Iterator<Animal> iterador = centroAdopcion.getAnimalesAlojados().iterator();
		
		JScrollPane spDescripcion;
		JTextArea taDescripcion;
		
		Dimension tamanio = new Dimension(160, 300);
		
		JLabel lNombre;
		JLabel lDatos;
		
		for (int i = 0; i < filas; i++) {
			
			arrayPaneles[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
			
			for (int j = 0; j < columnas && iterador.hasNext(); j++) {
				
				animal = iterador.next();
				
				panel = (matrizPaneles[i][j] = new JPanel());
				panel.setBackground(Vista.MARRON_CLARO3);
				panel.setBorder(bordeLinea);
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
				//Para que ocupan todos los paneles lo mismo
				panel.setPreferredSize(tamanio);
				panel.setMinimumSize(tamanio);
				panel.setMaximumSize(tamanio);
				
				lNombre = new JLabel(animal.getNombre());
				lNombre.setAlignmentX(JLabel.LEFT_ALIGNMENT);
				
				lDatos = new JLabel(animal.toStringSinCodigo());
				lDatos.setAlignmentX(JLabel.LEFT_ALIGNMENT);
				
				panel.add(lNombre);
				panel.add(new Separator());
				//aqui va a ir la imagen
				panel.add(lDatos);
				panel.add(new Separator());
				
				taDescripcion = new JTextArea(animal.getDescripcion());				
				taDescripcion.setEditable(false);
				taDescripcion.setFocusable(false);
				taDescripcion.setBackground(Vista.MARRON_CLARO3);
				taDescripcion.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
				
				taDescripcion.setLineWrap(true);		// salto de linea automatico
				taDescripcion.setWrapStyleWord(true);	// corta por palabras, no por caracteres
				
				spDescripcion = new JScrollPane(taDescripcion);
				spDescripcion.setBorder(null);
				spDescripcion.setPreferredSize(new Dimension(100, 200));
				spDescripcion.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
				
				panel.add(spDescripcion);
				
				arrayPaneles[i].add(panel);
				arrayPaneles[i].setBackground(Vista.MARRON_CLARO3);
			}
			
			
			this.add(arrayPaneles[i], "panel" + i); 	//le pongo el contador para que despues pueda acceder a las cartas del cardLayout
		}
		
		mueveRBotonesAlSiguientePanel(contFilas);
	}
	
	public void anteriorFila() {
		
		if(--contFilas < 0) contFilas = 0;
		muestraFilaPanelesAnimales();
	}
	
	public void siguienteFila() {
		
		if(++contFilas > filas - 1) contFilas = filas - 1; 	//le resto uno a filas porque el contFilas empieza por 0
		muestraFilaPanelesAnimales();
	}
	
	private void muestraFilaPanelesAnimales() {
		
		System.out.println(contFilas);
		
		mueveRBotonesAlSiguientePanel(contFilas);
		
		this.cartas.show(this, ("panel" + contFilas));
	}
	
	private void mueveRBotonesAlSiguientePanel(int fila) {
		
		rbAnimal1.setSelected(false);
		rbAnimal2.setSelected(false);
		rbAnimal3.setSelected(false);
		rbAnimal4.setSelected(false);
		
		if(matrizPaneles[fila][0] != null) matrizPaneles[fila][0].add(rbAnimal1);
		if(matrizPaneles[fila][1] != null) matrizPaneles[fila][1].add(rbAnimal2);
		if(matrizPaneles[fila][2] != null) matrizPaneles[fila][2].add(rbAnimal3);
		if(matrizPaneles[fila][3] != null) matrizPaneles[fila][3].add(rbAnimal4);
	}

}
