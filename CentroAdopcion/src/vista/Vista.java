package vista;

import java.awt.*;

import javax.swing.*;

import modelo.CentroAdopcion;

public class Vista extends JPanel{

	private static final long serialVersionUID = -5752211613049689258L;
	
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
