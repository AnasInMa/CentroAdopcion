package vista;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import controlador.ControladorOpcionesCentros;

public class VistaOpcionesCentros extends JPanel{

	private static final long serialVersionUID = -6104990496472019211L;
	
	private JPanel panelCentro1, panelCentro2, panelCentro3, panelCentro4, panelPrincipal;
	private JLabel nombreCentro1, nombreCentro2, nombreCentro3, nombreCentro4,
					imgCentro1, imgCentro2, imgCentro3, imgCentro4;
	private JButton bEntrarCentro1, bEntrarCentro2, bEntrarCentro3, bEntrarCentro4;
	private JLabel menu, texto;
	
	public VistaOpcionesCentros() {
		
		//this.setPreferredSize(new Dimension(Vista.ANCHO_PANEL, Vista.ALTO_PANEL));
		this.setLayout(new BorderLayout());
		this.setBackground(Vista.FONDO_PRINCIPAL);
		
		texto = new JLabel("⬇️ELIJA UNO DE ESTOS CENTROS PARA VER LOS ANIMALES DISPONIBLES⬇️");
		texto.setFont(Vista.FuenteTexto);
		texto.setForeground(Vista.TEXTO_OSCURO);
		texto.setHorizontalAlignment(JLabel.CENTER);
		texto.setBorder(new EmptyBorder(50, 0, 50, 0));
		
		editarBotones();
		
		panelPrincipal = new JPanel(new GridLayout(1, 4));
		panelPrincipal.setBorder(new EmptyBorder(0, 0, 200, 0));
		panelPrincipal.setBackground(Vista.FONDO_PRINCIPAL);
		
		panelPrincipal.add(panelCentro1 = añadePanelesCentros(nombreCentro1 = new JLabel("ARCA DEL TORCAL"), bEntrarCentro1, new ImageIcon("./imgs/arcaDelTorcal.jpg"), imgCentro1 = new JLabel()));
		panelPrincipal.add(panelCentro2 = añadePanelesCentros(nombreCentro2 = new JLabel("S.P.A.P.M."), bEntrarCentro2, new ImageIcon("./imgs/SPAPM.png"), imgCentro2 = new JLabel()));
		panelPrincipal.add(panelCentro3 = añadePanelesCentros(nombreCentro3 = new JLabel("REFUGIO DEL BURRITO"), bEntrarCentro3, new ImageIcon("./imgs/refugioDelBurrito.jpg"), imgCentro3 = new JLabel()));
		panelPrincipal.add(panelCentro4 = añadePanelesCentros(nombreCentro4 = new JLabel("P.A.D."), bEntrarCentro4, new ImageIcon("./imgs/PAD.png"), imgCentro4 = new JLabel()));
		
		menu = new JLabel(" ≡");
		menu.setFocusable(true);
		menu.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		menu.setBackground(Vista.FONDO_PRINCIPAL);
		
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
			
			boton.setForeground(Vista.TEXTO_CLARO);
			boton.setBackground(Vista.FONDO_BOTON);
		}
	}
	
	private JPanel añadePanelesCentros(JLabel nombreCentro, JButton boton, ImageIcon imagen, JLabel img) {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Vista.FONDO_PRINCIPAL);
		
		nombreCentro.setForeground(Vista.TEXTO_OSCURO);
		nombreCentro.setBorder(new EmptyBorder(0, 0, 10, 0));
		nombreCentro.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		Image imagenNueva = imagen.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		
		img.setIcon(new ImageIcon(imagenNueva));
		img.setBorder(VistaCentroAdopcion.BordeLinea);
		img.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		JLabel espacio = new JLabel();
		espacio.setBorder(new EmptyBorder(10,0,0,0));
		
		panel.add(nombreCentro);
		panel.add(img);
		panel.add(espacio);		//Preguntar si es una mala practica o esta bien
		panel.add(boton);
		
		return panel;
	}
	
	public void actualizarColores() {

		this.setBackground(Vista.FONDO_PRINCIPAL);

		texto.setForeground(Vista.TEXTO_OSCURO);

		bEntrarCentro1.setBackground(Vista.FONDO_BOTON);
		bEntrarCentro1.setForeground(Vista.TEXTO_CLARO);

		bEntrarCentro2.setBackground(Vista.FONDO_BOTON);
		bEntrarCentro2.setForeground(Vista.TEXTO_CLARO);

		bEntrarCentro3.setBackground(Vista.FONDO_BOTON);
		bEntrarCentro3.setForeground(Vista.TEXTO_CLARO);

		bEntrarCentro4.setBackground(Vista.FONDO_BOTON);
		bEntrarCentro4.setForeground(Vista.TEXTO_CLARO);

		panelPrincipal.setBackground(Vista.FONDO_PRINCIPAL);

		nombreCentro1.setForeground(Vista.TEXTO_OSCURO);
		nombreCentro2.setForeground(Vista.TEXTO_OSCURO);
		nombreCentro3.setForeground(Vista.TEXTO_OSCURO);
		nombreCentro4.setForeground(Vista.TEXTO_OSCURO);
		
		imgCentro1.setBorder(VistaCentroAdopcion.BordeLinea);
		imgCentro2.setBorder(VistaCentroAdopcion.BordeLinea);
		imgCentro3.setBorder(VistaCentroAdopcion.BordeLinea);
		imgCentro4.setBorder(VistaCentroAdopcion.BordeLinea);

		panelCentro1.setBackground(Vista.FONDO_PRINCIPAL);
		panelCentro2.setBackground(Vista.FONDO_PRINCIPAL);
		panelCentro3.setBackground(Vista.FONDO_PRINCIPAL);
		panelCentro4.setBackground(Vista.FONDO_PRINCIPAL);
	}

	public JLabel getTexto() {
		return texto;
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

	public JLabel getNombreCentro1() {
		return nombreCentro1;
	}

	public JLabel getNombreCentro2() {
		return nombreCentro2;
	}

	public JLabel getNombreCentro3() {
		return nombreCentro3;
	}

	public JLabel getNombreCentro4() {
		return nombreCentro4;
	}

}
