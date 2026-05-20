package controlador;

import vista.Vista;

public class Controlador {

	public Vista vista;
	
	public Controlador(Vista v) {
		
		vista = v;
		
		new ControladorMenu(vista);
		new ControladorOpcionesCentros(vista);
		new ControladorOpciones(vista);
		new ControladorSalir(vista);
	}
}
