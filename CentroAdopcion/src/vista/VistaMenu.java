package vista;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.ControladorMenu;

public class VistaMenu extends JPanel{

	private static final long serialVersionUID = 4112437023958187221L;

	private JButton bComenzar, bOpciones, bSalir;

	public VistaMenu() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(960, 540));
		this.setBorder(new EmptyBorder(60,10,10,10));
		this.setBackground(Vista.MARRON_CLARO3);
		
		JLabel titulo = new JLabel("CEADOP");
		titulo.setFont(new Font(Font.SERIF, Font.BOLD, 60));
		titulo.setForeground(Vista.MARRON_OSCURO4);
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		
		iniciaBotones();
		
		this.add(titulo);
		this.add(Box.createRigidArea(new Dimension(0,80)));
		this.decorarYAñadirBotones();
	}
	
	public void control(ControladorMenu c) {
		
		this.bComenzar.addActionListener(c);
		this.bOpciones.addActionListener(c);
		this.bSalir.addActionListener(c);
	}
	
	private void iniciaBotones() {
		
		this.bComenzar = new JButton("COMENZAR");
		this.bOpciones = new JButton("OPCIONES");
		this.bSalir = new JButton("SALIR");
	}
	
	private void decorarYAñadirBotones() {
		
		JButton[] botones = {bComenzar, bOpciones, bSalir};
		
		Dimension dimensionBoton = new Dimension(Vista.ANCHO_COMPONENTE, Vista.ALTO_COMPONENTE);
		for(JButton boton : botones) {
			
			boton.setMinimumSize(dimensionBoton);
			boton.setPreferredSize(dimensionBoton);
			boton.setMaximumSize(dimensionBoton);
			
			boton.setAlignmentX(CENTER_ALIGNMENT);
			boton.setFont(Vista.FUENTE_BOTONES);
			boton.setFocusable(false);
			
			boton.setForeground(Vista.MARRON_OSCURO4);
			boton.setBackground(Vista.MARRON_CLARO4);
			
			this.add(boton);
			this.add(Box.createRigidArea(new Dimension(0, 45)));
			
		}
	}

	public JButton getbComenzar() {
		return bComenzar;
	}

	public JButton getbOpciones() {
		return bOpciones;
	}

	public JButton getbSalir() {
		return bSalir;
	}
	
}
