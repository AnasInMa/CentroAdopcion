package vista;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.JPopupMenu.Separator;
import javax.swing.border.LineBorder;

import modelo.Animal;
import modelo.CentroAdopcion;
import modelo.DAOAnimales;

public class VistaAnimales extends JPanel {
	
	private static final long serialVersionUID = 3726098725460425172L;
	
	private DAOAnimales dao;
	
	private CentroAdopcion centroAdopcion;
	private JPanel[][] matrizPaneles;
	private JPanel[] arrayPaneles; 	//array de paneles en los que en cada uno habran metidos los 4 paneles con los animales
	private static int contFilas;
	private static final float COLUMNAS;
	private CardLayout cartas;
	private int filas;
	private ButtonGroup grupoRB;
	private JRadioButton rbAnimal1, rbAnimal2, rbAnimal3, rbAnimal4;	//el jradioButton que este presionado sera el animal que va a ser adoptado
	private Dimension tamañoPanelAnimal;
	
	static {
		
		contFilas = 0;
		
		COLUMNAS = 4;
	}

	public VistaAnimales(CentroAdopcion centro) {
		
		this.setLayout(cartas = new CardLayout());
		this.setBackground(Vista.FONDO_PRINCIPAL);
		
		centroAdopcion = centro;
		
		//centroAdopcion.alojaAnimales(null);
		
		try {
			
			this.dao = new DAOAnimales();
			
			centroAdopcion.alojaAnimales(dao.getAnimalesCentro(centroAdopcion));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public VistaAnimales(CentroAdopcion centro, VistaCentroAdopcion v) {
		
		this(centro);
		v.getDatosCentro().setText(" " + centroAdopcion.toStringSinNombre() + " ");
		
		int ancho, alto;
		
		if (VistaOpciones.getCbPantallaCompleta().isSelected()) {

			ancho = (int) (v.getPanelAnimales().getPreferredSize().getWidth() * 17.5f);
			alto = (int) (v.getPanelAnimales().getPreferredSize().getHeight() * 30);

		} else {
			
			ancho = (int) (v.getPanelAnimales().getPreferredSize().getWidth() * 12);
			alto = (int) (v.getPanelAnimales().getPreferredSize().getHeight() * 22);
		}
		
		tamañoPanelAnimal = new Dimension(ancho, alto);
		
		if(centro.getAnimalesAlojados().size() > 0) matrizPanelesAnimales();
	}
	
	private void iniciaRBotones() {
		
		this.rbAnimal1 = new JRadioButton("Elegir animal");
		this.rbAnimal2 = new JRadioButton("Elegir animal");
		this.rbAnimal3 = new JRadioButton("Elegir animal");
		this.rbAnimal4 = new JRadioButton("Elegir animal");
		
		grupoRB = new ButtonGroup();
		
		JRadioButton[] rBotones = {rbAnimal1, rbAnimal2, rbAnimal3, rbAnimal4};
		
		for (JRadioButton rboton : rBotones) {
			
			rboton.setFocusable(false);
			rboton.setAlignmentX(JRadioButton.LEFT_ALIGNMENT);
			rboton.setBackground(Vista.FONDO_ANIMALES);
			grupoRB.add(rboton);
		}
	}
	
	private void matrizPanelesAnimales() {
		
		filas = (int) Math.ceil(centroAdopcion.getAnimalesAlojados().size() / COLUMNAS);	// si tiene 6 animales habran 2 filas, si
																				// hay 3 animales solo habra 1 fila
		int columnas = (int) COLUMNAS;
		
		iniciaRBotones();
		
		this.matrizPaneles = new JPanel[filas][columnas];
		this.arrayPaneles = new JPanel[filas];
		
		JPanel panel, panelNombre;
		Animal animal;
		
		LineBorder bordeLinea = new LineBorder(Vista.TEXTO_OSCURO, 4);
		
		Iterator<Animal> iterador = centroAdopcion.getAnimalesAlojados().iterator();
		
		JScrollPane spDescripcion;
		JTextArea taDescripcion;
		
		JLabel lNombre, lId, lDatos;
		
		for (int i = 0; i < filas; i++) {
			
			arrayPaneles[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
			
			for (int j = 0; j < columnas && iterador.hasNext(); j++) {
				
				animal = iterador.next();
				
				panel = (matrizPaneles[i][j] = new JPanel());
				panel.setBackground(Vista.FONDO_ANIMALES);
				panel.setBorder(bordeLinea);
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
				//Para que ocupan todos los paneles lo mismo
				panel.setPreferredSize(tamañoPanelAnimal);
				panel.setMinimumSize(tamañoPanelAnimal);
				panel.setMaximumSize(tamañoPanelAnimal);
				
				lNombre = new JLabel(animal.getNombre());
				
				lId = new JLabel(animal.getIDAnimal() + "");
				
				panelNombre = new JPanel();
				panelNombre.setBackground(Vista.FONDO_ANIMALES);
				panelNombre.setAlignmentX(JPanel.LEFT_ALIGNMENT);
				panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.X_AXIS));
				panelNombre.add(lNombre);
				panelNombre.add(Box.createHorizontalGlue());
				panelNombre.add(lId);
				
				lDatos = new JLabel(animal.toStringSinCodigo());
				lDatos.setAlignmentX(JLabel.LEFT_ALIGNMENT);
				
				panel.add(panelNombre);
				panel.add(new Separator());
				//TODO aqui va a ir la imagen
				panel.add(lDatos);
				panel.add(new Separator());

				taDescripcion = new JTextArea(animal.toStringDescripcion());				
				taDescripcion.setEditable(false);
				taDescripcion.setFocusable(false);
				taDescripcion.setBackground(Vista.FONDO_ANIMALES);
				taDescripcion.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
				
				taDescripcion.setLineWrap(true);		// salto de linea automatico
				taDescripcion.setWrapStyleWord(true);	// corta por palabras, no por caracteres
				
				spDescripcion = new JScrollPane(taDescripcion);
				spDescripcion.setBorder(null);
				spDescripcion.setPreferredSize(new Dimension(100, 200));
				spDescripcion.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
				
				panel.add(spDescripcion);
				
				arrayPaneles[i].add(panel);
				arrayPaneles[i].setBackground(Vista.FONDO_PRINCIPAL);
			}
			
			
			this.add(arrayPaneles[i], "panel" + i); 	//le pongo el contador para que despues pueda acceder a las cartas del cardLayout
		}
		
		mueveRBotonesAlSiguientePanel(contFilas);
	}

	public JPanel panelAnimalSeleccionado() {

		//int columna = -1;

		if (this.rbAnimal1.isSelected()) {
			
			return this.matrizPaneles[contFilas][0];
			
		} else if (this.rbAnimal2.isSelected()) {
			
			return this.matrizPaneles[contFilas][1];
		
		} else if (this.rbAnimal3.isSelected()) {
			
			return this.matrizPaneles[contFilas][2];
			
		} else if (this.rbAnimal4.isSelected()) {
			
			return this.matrizPaneles[contFilas][3];
			
		} else
			return null;

		//return (columna == -1) ? null : this.matrizPaneles[contFilas][columna];
	}
	
	public void primeraFila() {
		
		contFilas = 0;
		muestraFilaPanelesAnimales();
	}
	
	public void anteriorFila() {
		
		if(--contFilas < 0) contFilas = 0;
		muestraFilaPanelesAnimales();
	}
	
	public void siguienteFila() {
		
		if(++contFilas > filas - 1) contFilas = filas - 1; 	//le resto uno a filas porque el contFilas empieza por 0
		muestraFilaPanelesAnimales();
	}
	
	public void ultimaFila() {
		
		contFilas = filas - 1;
		muestraFilaPanelesAnimales();
	}
	
	private void muestraFilaPanelesAnimales() {
		
		//System.out.println(contFilas);
		
		mueveRBotonesAlSiguientePanel(contFilas);
		
		this.cartas.show(this, ("panel" + contFilas));
	}
	
	private void mueveRBotonesAlSiguientePanel(int fila) {
		
		//rbAnimal1.setSelected(false);
		//rbAnimal2.setSelected(false);
		//rbAnimal3.setSelected(false);
		//rbAnimal4.setSelected(false);
		
		grupoRB.clearSelection();
		
		if(matrizPaneles[fila][0] != null) matrizPaneles[fila][0].add(rbAnimal1);
		if(matrizPaneles[fila][1] != null) matrizPaneles[fila][1].add(rbAnimal2);
		if(matrizPaneles[fila][2] != null) matrizPaneles[fila][2].add(rbAnimal3);
		if(matrizPaneles[fila][3] != null) matrizPaneles[fila][3].add(rbAnimal4);
	}

	public DAOAnimales getDao() {
		return dao;
	}

	public ButtonGroup getGrupoRB() {
		return grupoRB;
	}

}
