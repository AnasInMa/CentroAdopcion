package vista;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VistaMenu extends JPanel{

	private static final long serialVersionUID = 4112437023958187221L;

	private JButton bComenzar, bOpciones, bSalir, bModo; //el botonModo es para cambiar entre modo claro y modo oscuro
	
	public static final byte BASE_HORIZONTAL, BASE_VERTICAL;
	public static short ANCHO, ALTO;
	public static float multiplicador;	//esta variable se va a utilizar para las resoluciones
	
	static {
		
		BASE_HORIZONTAL = 16;
		BASE_VERTICAL = 9;

		multiplicador = 30;
		
		ANCHO = (short) (BASE_HORIZONTAL * multiplicador);
		ALTO = (short) (BASE_VERTICAL * multiplicador);
	}
	
	public VistaMenu() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(ANCHO, ALTO));
		this.setBorder(new EmptyBorder(20,10,10,10));
		
		JLabel titulo = new JLabel("CEADOP");
		titulo.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		
		iniciaBotones();
		
		this.add(titulo);
		this.add(Box.createRigidArea(new Dimension(0,80)));
		this.decorarYAñadirBotones();		
		this.add(Box.createVerticalGlue());	//Crea un espacio y manda lo que este debajo al final
		this.add(bModo);
	}
	
	private void iniciaBotones() {
		
		this.bComenzar = new JButton("COMENZAR");
		this.bOpciones = new JButton("OPCIONES");
		this.bSalir = new JButton("SALIR");
		this.bModo = new JButton("Claro");
		
		this.bModo.setAlignmentX(CENTER_ALIGNMENT);
	}
	
	private void decorarYAñadirBotones() {
		
		JButton[] botones = {bComenzar, bOpciones, bSalir};
		
		Font fuenteBoton = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		
		for(JButton boton : botones) {
			
			boton.setAlignmentX(CENTER_ALIGNMENT);
			boton.setFont(fuenteBoton);
			boton.setFocusable(false);
			
			this.add(boton);
			this.add(Box.createRigidArea(new Dimension(0,50)));
		}
	}
	
}
