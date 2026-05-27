package vista;

import java.awt.*;

import javax.swing.*;

import modelo.CentroAdopcion;

public class Vista extends JPanel{

	private static final long serialVersionUID = -5752211613049689258L;
	
	private static final byte BASE_HORIZONTAL, BASE_VERTICAL;
	public static final short ANCHO_PANEL, ALTO_PANEL;
	public static final int ANCHO_COMPONENTE, ALTO_COMPONENTE;
	
	public static float multiplicadorPanel,		//esta variable se va a utilizar para las resoluciones de los paneles
						multiplicadorAnchoComponente,
						multiplicadorAltoComponente;	//y estos para el tamaño de los componetes

	public static Color FONDO_DATOS, FONDO_ANIMALES, FONDO_PRINCIPAL, FONDO_BOTON,
						TEXTO_CLARO, TEXTO_OSCURO;	//Colores que tendra la vista, y cambiara en funcion de la eleccion del usuario en el panel opciones
	public static final Color FONDO_DATOS_MARRON, FONDO_ANIMALES_MARRON, FONDO_PRINCIPAL_MARRON, FONDO_BOTON_MARRON,
								TEXTO_CLARO_MARRON, TEXTO_OSCURO_MARRON,
							FONDO_DATOS_VERDE, FONDO_ANIMALES_VERDE, FONDO_PRINCIPAL_VERDE, FONDO_BOTON_VERDE,
								TEXTO_CLARO_VERDE, TEXTO_OSCURO_VERDE,
							FONDO_DATOS_GRIS, FONDO_ANIMALES_GRIS, FONDO_PRINCIPAL_GRIS, FONDO_BOTON_GRIS,
								TEXTO_CLARO_GRIS, TEXTO_OSCURO_GRIS;
	
	public static Color[] ColoresVisibles, ColoresMarron, ColoresVerde, ColoresGris;
	
	public static final Font FUENTE_BOTONES;
	
	static {
		
		//Dimensiones de la ventana y los componentes
		BASE_HORIZONTAL = 16;
		BASE_VERTICAL = 9;
		
		multiplicadorPanel = 60;

		multiplicadorAnchoComponente = 0.3f;
		multiplicadorAltoComponente = 0.1f;
		
		ANCHO_PANEL = (short) (BASE_HORIZONTAL * multiplicadorPanel);
		ALTO_PANEL = (short) (BASE_VERTICAL * multiplicadorPanel);
		
		ANCHO_COMPONENTE = (int) (ANCHO_PANEL * multiplicadorAnchoComponente);
		ALTO_COMPONENTE = (int) (ALTO_PANEL * multiplicadorAltoComponente);

		//Marron
		FONDO_DATOS_MARRON = new Color(249, 248, 246);
		FONDO_ANIMALES_MARRON = new Color(239, 233, 227);
		FONDO_PRINCIPAL_MARRON = Color.black;
		FONDO_BOTON_MARRON = new Color(196, 134, 78);
		
		TEXTO_CLARO_MARRON = new Color(243, 238, 234);
		TEXTO_OSCURO_MARRON = new Color(107, 79, 42);
		
		ColoresMarron = new Color[] {FONDO_DATOS_MARRON, FONDO_ANIMALES_MARRON, FONDO_PRINCIPAL_MARRON, FONDO_BOTON_MARRON,
								TEXTO_CLARO_MARRON, TEXTO_OSCURO_MARRON};
		
		//Verde
		FONDO_DATOS_VERDE = new Color(249, 248, 246);
		FONDO_ANIMALES_VERDE = new Color(239, 233, 227);
		FONDO_PRINCIPAL_VERDE = Color.green;
		FONDO_BOTON_VERDE = new Color(196, 134, 78);
		
		TEXTO_CLARO_VERDE = new Color(243, 238, 234);
		TEXTO_OSCURO_VERDE = new Color(107, 79, 42);
		
		ColoresVerde = new Color[] {FONDO_DATOS_VERDE, FONDO_ANIMALES_VERDE, FONDO_PRINCIPAL_VERDE, FONDO_BOTON_VERDE,
								TEXTO_CLARO_VERDE, TEXTO_OSCURO_VERDE};

		//Gris
		FONDO_PRINCIPAL_GRIS = new Color(242, 239, 233);
		FONDO_ANIMALES_GRIS = new Color(224, 221, 213);
		FONDO_DATOS_GRIS = new Color(90, 86, 80);
		FONDO_BOTON_GRIS = new Color(90, 86, 80);
		
		TEXTO_CLARO_GRIS = new Color(243, 238, 234);
		TEXTO_OSCURO_GRIS = new Color(61, 58, 53);
		
		ColoresGris = new Color[] {FONDO_DATOS_GRIS, FONDO_ANIMALES_GRIS, FONDO_PRINCIPAL_GRIS, FONDO_BOTON_GRIS,
								TEXTO_CLARO_GRIS, TEXTO_OSCURO_GRIS};
		
		//Colores
		
		ColoresVisibles = ColoresGris;
		
		FONDO_DATOS = ColoresVisibles[0];
		FONDO_ANIMALES = ColoresVisibles[1];
		FONDO_PRINCIPAL = ColoresVisibles[2];
		FONDO_BOTON = ColoresVisibles[3];
		
		TEXTO_CLARO = ColoresVisibles[4];
		TEXTO_OSCURO = ColoresVisibles[5];
		
		//Fuentes
		FUENTE_BOTONES = new Font(Font.SANS_SERIF, Font.BOLD, 25);
	}
	
	private CardLayout cartas;
	
	private VistaMenu vMenu;
	private VistaOpcionesCentros vOpcionesCentros;
	private VistaCentroAdopcion vCentroAdopcion;
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
	
	public void añadeVistaCentroAdopcion(CentroAdopcion centro) {
		
		this.add(vCentroAdopcion = new VistaCentroAdopcion(centro), "centroAdopcion");
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
	
	public void muestraPanelOpcionesCentros() {
		
		cartas.show(this, "opcionesCentros");
	}
	
	public void muestraPanelCentro() {
		
		cartas.show(this, "centroAdopcion");
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
	
	public VistaCentroAdopcion getvCentroAdopcion() {
		return vCentroAdopcion;
	}

	public VistaOpciones getvOpciones() {
		return vOpciones;
	}

	public VistaSalir getvSalir() {
		return vSalir;
	}
}
