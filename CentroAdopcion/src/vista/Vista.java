package vista;

import java.awt.*;

import javax.swing.*;

import controlador.Controlador;

public class Vista extends JPanel{

	private static final long serialVersionUID = -5752211613049689258L;

	private JButton bComenzar;
	
	private CardLayout cartas;
	
	public Vista() {
		
		cartas = new CardLayout();
		
		this.setLayout(cartas);
		
		this.add(panelPrincipal(), 0);
		this.add(new VistaCentroAdopcion(), 1);
		
	}
	
	public void siguienteCarta() {
		
		this.cartas.next(this);
	}
	
	public JButton getbComenzar() {
		return bComenzar;
	}

	public void control(Controlador c) {
		
		this.bComenzar.addActionListener(c);
	}
	
	private JPanel panelPrincipal() {
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
		panelPrincipal.add(panelTexto());
		panelPrincipal.add(panelBoton());
		
		return panelPrincipal;
	}
	
	private JPanel panelTexto() {
		
		JPanel panel = new JPanel();
		
		JLabel texto = new JLabel("Bienvenido a CEADOP. Dale a comenzar y elije un Centro de Adopcion!");
		texto.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
		
		panel.add(texto);
		
		return panel;
	}
	
	private JPanel panelBoton() {
		
		JPanel panel = new JPanel();
		
		bComenzar = new JButton("Comenzar");
		bComenzar.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		bComenzar.setFocusable(false);
		
		panel.add(bComenzar);
		
		return panel;
	}
}
