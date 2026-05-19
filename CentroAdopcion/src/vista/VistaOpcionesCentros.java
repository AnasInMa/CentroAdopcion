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
		this.setBackground(Vista.MARRON_CLARO3);
		
		JLabel texto = new JLabel("⬇️ELIJA UNO DE ESTOS CENTROS PARA VER LOS ANIMALES DISPONIBLES⬇️");
		texto.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		texto.setForeground(Vista.MARRON_OSCURO4);
		texto.setHorizontalAlignment(JLabel.CENTER);
		texto.setBorder(new EmptyBorder(0, 0, 100, 0));
		
		editarBotones();
		
		JPanel panelPrincipal = new JPanel(new GridLayout(1, 4));
		panelPrincipal.setBorder(new EmptyBorder(0, 0, 200, 0));
		panelPrincipal.setBackground(Vista.MARRON_CLARO3);
		
		panelPrincipal.add(añadePanelesCentros("ARCA DEL TORCAL", bEntrarCentro1, new ImageIcon("./imgs/arcaDelTorcal.jpg")));
		panelPrincipal.add(añadePanelesCentros("S.P.A.P.M.", bEntrarCentro2, new ImageIcon("./imgs/SPAPM.png")));
		panelPrincipal.add(añadePanelesCentros("REFUGIO DEL BURRITO", bEntrarCentro3, new ImageIcon("./imgs/refugioDelBurrito.jpg")));
		panelPrincipal.add(añadePanelesCentros("P.A.D.", bEntrarCentro4, new ImageIcon("./imgs/PAD.png")));
		
		menu = new JLabel(" ≡");
		menu.setFocusable(true);
		menu.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		menu.setBackground(Vista.MARRON_CLARO3);
		
		this.add(menu, BorderLayout.NORTH);
		this.add(texto, BorderLayout.CENTER);
		this.add(panelPrincipal, BorderLayout.SOUTH);
	}
	
	public void control(ControladorOpcionesCentros c) {
		
		this.menu.addMouseListener(c);
		
		this.bEntrarCentro1.addActionListener(c);
		this.bEntrarCentro2.addActionListener(c);
		this.bEntrarCentro3.addActionListener(c);
		this.bEntrarCentro4.addActionListener(c);
	}
	
	private void instanciarBotones() {
		
		bEntrarCentro1 = new JButton("ENTRAR");
		bEntrarCentro2 = new JButton("ENTRAR");
		bEntrarCentro3 = new JButton("ENTRAR");
		bEntrarCentro4 = new JButton("ENTRAR");
	}
	
	private void editarBotones() {
		
		instanciarBotones();
		
		JButton[] botones = {bEntrarCentro1, bEntrarCentro2, bEntrarCentro3, bEntrarCentro4};
		
		for(JButton boton : botones) {

			boton.setFocusable(false);
			boton.setAlignmentX(JButton.CENTER_ALIGNMENT);
			
			boton.setForeground(Vista.MARRON_OSCURO4);
			boton.setBackground(Vista.MARRON_CLARO4);
		}
	}
	
	private JPanel añadePanelesCentros(String nombreCentro, JButton boton, ImageIcon imagen) {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Vista.MARRON_CLARO3);
		
		JLabel texto = new JLabel(nombreCentro);
		texto.setForeground(Vista.MARRON_OSCURO4);
		texto.setBorder(new EmptyBorder(0,0,10,0));
		texto.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		Image imagenNueva = imagen.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		
		JLabel img = new JLabel();
		img.setIcon(new ImageIcon(imagenNueva));
		img.setBorder(new LineBorder(Vista.MARRON_OSCURO4, 3));
		img.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		JLabel espacio = new JLabel();
		espacio.setBorder(new EmptyBorder(10,0,0,0));
		
		panel.add(texto);
		panel.add(img);
		panel.add(espacio);		//Preguntar si es malo o esta bien
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
