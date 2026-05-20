package vista;

import java.awt.*;

import javax.swing.*;

public class Vista extends JPanel{

	private static final long serialVersionUID = -5752211613049689258L;
	
/*	private static final byte BASE_HORIZONTAL, BASE_VERTICAL;
	public static short ANCHO_PANEL, ALTO_PANEL;
	public static int ANCHO_COMPONENTE, ALTO_COMPONENTE;
	public static float multiplicadorPanel,		//esta variable se va a utilizar para las resoluciones de los paneles
						multiplicadorAnchoComponente,	//⬇️
						multiplicadorAltoComponente;	//y estos para el tamaño de los componetes
*/	
	public static final Color MARRON_CLARO1, MARRON_CLARO2, MARRON_CLARO3, MARRON_CLARO4,
							 MARRON_OSCURO1, MARRON_OSCURO2, MARRON_OSCURO3, MARRON_OSCURO4;
	public static final Font FUENTE_BOTONES;
	
	static {
		/*
		BASE_HORIZONTAL = 16;
		BASE_VERTICAL = 9;

		multiplicadorPanel = 60;
		multiplicadorAnchoComponente = 0.3f;
		multiplicadorAltoComponente = 0.1f;
		
		ANCHO_PANEL = (short) (BASE_HORIZONTAL * multiplicadorPanel);
		ALTO_PANEL = (short) (BASE_VERTICAL * multiplicadorPanel);
		
		ANCHO_COMPONENTE = (int) (ANCHO_PANEL * multiplicadorAnchoComponente);
		ALTO_COMPONENTE = (int) (ALTO_PANEL * multiplicadorAltoComponente);
		*/
		
		//Colores
		MARRON_CLARO1 = new Color(249, 248, 246);
		MARRON_CLARO2 = new Color(239, 233, 227);
		MARRON_CLARO3 = new Color(217, 207, 199);
		MARRON_CLARO4 = new Color(201, 181, 156);
		
		MARRON_OSCURO1 = new Color(243, 238, 234);
		MARRON_OSCURO2 = new Color(235, 227, 213);
		MARRON_OSCURO3 = new Color(176, 166, 149);
		MARRON_OSCURO4 = new Color(75, 64, 56);
		
		//Fuentes
		FUENTE_BOTONES = new Font(Font.SANS_SERIF, Font.BOLD, 25);
	}
	
	private CardLayout cartas;
	
	private VistaMenu vMenu;
	private VistaOpcionesCentros vOpcionesCentros;
	private VistaOpciones vOpciones;
	private VistaSalir vSalir;
	
	public Vista() {
		
		cartas = new CardLayout();
		this.setLayout(cartas);
		
		this.add(vMenu = new VistaMenu(), "menu");
		this.add(vOpcionesCentros = new VistaOpcionesCentros(), "opcionesCentros");
		this.add(vOpciones = new VistaOpciones(), "opciones");
		this.add(vSalir = new VistaSalir(), "salir");
	}
	
	public void muestraPrimerPanel() {
		
		cartas.first(this);
	}
	
	public void muestraSiguientePanel() {
		
		cartas.next(this);
	}

	public void muestraPanelOpciones() {
		
		cartas.show(this, "opciones");
	}
	
	public void muestraPanelSalir() {
		
		cartas.show(this, "salir");
	}
	
	public void muestraAnteriorPanel() {
		
		cartas.previous(this);
	}

	public VistaMenu getvMenu() {
		return vMenu;
	}

	public VistaOpcionesCentros getvOpcionesCentros() {
		return vOpcionesCentros;
	}
	
	public VistaOpciones getvOpciones() {
		return vOpciones;
	}

	public VistaSalir getvSalir() {
		return vSalir;
	}
}
