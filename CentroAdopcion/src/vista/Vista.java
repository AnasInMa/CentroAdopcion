package vista;

import java.awt.CardLayout;

import javax.swing.*;

public class Vista extends JPanel{

	private static final long serialVersionUID = -5752211613049689258L;
	
	private static final byte BASE_HORIZONTAL, BASE_VERTICAL;
	public static short ANCHO_PANEL, ALTO_PANEL;
	public static int ANCHO_COMPONENTE, ALTO_COMPONENTE;
	public static float multiplicadorPanel,		//esta variable se va a utilizar para las resoluciones de los paneles
						multiplicadorAnchoComponente,	//⬇️
						multiplicadorAltoComponente;	//y estos para el tamaño de los componetes
	
	static {
		
		BASE_HORIZONTAL = 16;
		BASE_VERTICAL = 9;

		multiplicadorPanel = 60;
		multiplicadorAnchoComponente = 0.2f;
		multiplicadorAltoComponente = 0.1f;
		
		ANCHO_PANEL = (short) (BASE_HORIZONTAL * multiplicadorPanel);
		ALTO_PANEL = (short) (BASE_VERTICAL * multiplicadorPanel);
		
		ANCHO_COMPONENTE = (int) (ANCHO_PANEL * multiplicadorAnchoComponente);
		ALTO_COMPONENTE = (int) (ALTO_PANEL * multiplicadorAltoComponente);
	}
	
	private CardLayout cartas;
	
	private VistaMenu vMenu;
	private VistaOpcionesCentros vOpcionesCentros;
	
	public Vista() {
		
		cartas = new CardLayout();
		this.setLayout(cartas);
		
		this.add(vMenu = new VistaMenu(), 0);
		this.add(vOpcionesCentros = new VistaOpcionesCentros(), 1);
	}
	
	public void siguientePanel() {
		
		cartas.next(this);
	}
	
	public void anteriorPanel() {
		
		cartas.previous(this);
	}

	public VistaMenu getvMenu() {
		return vMenu;
	}

	public VistaOpcionesCentros getvOpcionesCentros() {
		return vOpcionesCentros;
	}
}
