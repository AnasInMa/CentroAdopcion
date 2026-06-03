package vista;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.ControladorMenu;
import utilidades.UtilidadesVariables;

public class VistaMenu extends JPanel{

	private static final long serialVersionUID = 4112437023958187221L;

	private JButton bComenzar, bOpciones, bSalir;
	private JLabel titulo;

	public VistaMenu() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(UtilidadesVariables.ANCHO_PANEL, UtilidadesVariables.ALTO_PANEL));
		this.setBorder(new EmptyBorder(60,10,10,10));
		this.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		
		titulo = new JLabel("CEADOP");
		titulo.setFont(new Font(Font.SERIF, Font.BOLD, 60));
		titulo.setForeground(UtilidadesVariables.TEXTO_OSCURO);
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		
		iniciaBotones();
		
		this.add(titulo);
		this.add(Box.createVerticalStrut((int) UtilidadesVariables.multiplicadorPanel));
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
		
		Dimension dimensionBoton = new Dimension(UtilidadesVariables.ANCHO_COMPONENTE, UtilidadesVariables.ALTO_COMPONENTE);
		
		for(JButton boton : botones) {
			
			boton.setMinimumSize(dimensionBoton);
			boton.setPreferredSize(dimensionBoton);
			boton.setMaximumSize(dimensionBoton);
			
			boton.setAlignmentX(CENTER_ALIGNMENT);
			boton.setFont(UtilidadesVariables.FUENTE_BOTONES);
			boton.setFocusable(false);
			
			boton.setForeground(UtilidadesVariables.TEXTO_CLARO);
			boton.setBackground(UtilidadesVariables.FONDO_BOTON);
			
			this.add(boton);
			//this.add(Box.createRigidArea(new Dimension(0, UtilidadesVariables.ALTO_COMPONENTE)));
			this.add(Box.createVerticalStrut(UtilidadesVariables.ALTO_COMPONENTE));
			
		}
	}
	
	public void actualizarColores() {
		
		this.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		
		titulo.setForeground(UtilidadesVariables.TEXTO_OSCURO);
		
		bComenzar.setBackground(UtilidadesVariables.FONDO_BOTON);
		bComenzar.setForeground(UtilidadesVariables.TEXTO_CLARO);
		
		bOpciones.setBackground(UtilidadesVariables.FONDO_BOTON);
		bOpciones.setForeground(UtilidadesVariables.TEXTO_CLARO);
		
		bSalir.setBackground(UtilidadesVariables.FONDO_BOTON);
		bSalir.setForeground(UtilidadesVariables.TEXTO_CLARO);
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
