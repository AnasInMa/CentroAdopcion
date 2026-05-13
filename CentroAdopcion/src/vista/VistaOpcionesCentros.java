package vista;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import controlador.ControladorOpcionesCentros;

public class VistaOpcionesCentros extends JPanel{

	private static final long serialVersionUID = -6104990496472019211L;
	
	private JButton bEntrarCentro1, bEntrarCentro2, bEntrarCentro3, bEntrarCentro4;
	private JLabel menu;
	
	public VistaOpcionesCentros() {
		
		this.setLayout(new BorderLayout());
		
		JLabel texto = new JLabel("⬇️ELIJA UNO DE ESTOS CENTROS PARA VER LOS ANIMALES DISPONIBLES⬇️");
		texto.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		texto.setHorizontalAlignment(JLabel.CENTER);
		texto.setBorder(new EmptyBorder(0, 0, 100, 0));
		
		editarBotones();
		
		JPanel panelPrincipal = new JPanel(new GridLayout(1, 4));
		panelPrincipal.setBorder(new EmptyBorder(0, 0, 200, 0));
		panelPrincipal.add(añadePanelesCentros("centro1", bEntrarCentro1));
		panelPrincipal.add(añadePanelesCentros("centro2", bEntrarCentro2));
		panelPrincipal.add(añadePanelesCentros("centro3", bEntrarCentro3));
		panelPrincipal.add(añadePanelesCentros("centro4", bEntrarCentro4));
		
		menu = new JLabel(" ≡");
		menu.setFocusable(true);
		menu.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		
		this.add(menu, BorderLayout.NORTH);
		this.add(texto, BorderLayout.CENTER);
		this.add(panelPrincipal, BorderLayout.SOUTH);
	}
	
	public void control(ControladorOpcionesCentros c) {
		
		this.menu.addMouseListener(c);
	}
	
	private void instanciarBotones() {
		
		bEntrarCentro1 = new JButton("Entrar");
		bEntrarCentro2 = new JButton("Entrar");
		bEntrarCentro3 = new JButton("Entrar");
		bEntrarCentro4 = new JButton("Entrar");
	}
	
	private void editarBotones() {
		
		instanciarBotones();
		
		JButton[] botones = {bEntrarCentro1, bEntrarCentro2, bEntrarCentro3, bEntrarCentro4};
		
		for(JButton boton : botones) {

			boton.setFocusable(false);
			boton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		}
	}
	
	private JPanel añadePanelesCentros(String nombreCentro, JButton boton) {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel texto = new JLabel(nombreCentro);

		texto.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		    
		panel.add(texto);
		//panel.add(); 	//imagen
		panel.add(boton);
		
		return panel;
	}

	public JButton getbEntrarCentro1() {
		return bEntrarCentro1;
	}

	public JButton getbEntrarCentro2() {
		return bEntrarCentro2;
	}

	public JButton getbEntrarCentro3() {
		return bEntrarCentro3;
	}

	public JButton getbEntrarCentro4() {
		return bEntrarCentro4;
	}

	public JLabel getMenu() {
		return menu;
	}

}
