package vista;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.ControladorMenu;

public class VistaMenu extends JPanel{

	private static final long serialVersionUID = 4112437023958187221L;

	private JButton bComenzar, bOpciones, bSalir;
	private JLabel titulo;

	public VistaMenu() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(Vista.ANCHO_PANEL, Vista.ALTO_PANEL));
		this.setBorder(new EmptyBorder(60,10,10,10));
		this.setBackground(Vista.FONDO_PRINCIPAL);
		
		titulo = new JLabel("CEADOP");
		titulo.setFont(new Font(Font.SERIF, Font.BOLD, 60));
		titulo.setForeground(Vista.TEXTO_OSCURO);
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		
		iniciaBotones();
		
		this.add(titulo);
		this.add(Box.createVerticalStrut((int) Vista.multiplicadorPanel));
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
			
			boton.setForeground(Vista.TEXTO_CLARO);
			boton.setBackground(Vista.FONDO_BOTON);
			
			this.add(boton);
			//this.add(Box.createRigidArea(new Dimension(0, Vista.ALTO_COMPONENTE)));
			this.add(Box.createVerticalStrut(Vista.ALTO_COMPONENTE));
			
		}
	}
	
	public void actualizarColores() {
		
		this.setBackground(Vista.FONDO_PRINCIPAL);
		
		titulo.setForeground(Vista.TEXTO_OSCURO);
		
		bComenzar.setBackground(Vista.FONDO_BOTON);
		bComenzar.setForeground(Vista.TEXTO_CLARO);
		
		bOpciones.setBackground(Vista.FONDO_BOTON);
		bOpciones.setForeground(Vista.TEXTO_CLARO);
		
		bSalir.setBackground(Vista.FONDO_BOTON);
		bSalir.setForeground(Vista.TEXTO_CLARO);
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
