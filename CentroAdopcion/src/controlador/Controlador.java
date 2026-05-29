package controlador;

import java.sql.SQLException;

import modelo.DAOAnimales;
import vista.Vista;

public class Controlador {

	private Vista vista;
	private DAOAnimales daoAnimales;
	
	public Controlador(Vista v) {
		
		vista = v;
		
		try {
			
			daoAnimales = new DAOAnimales();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		new ControladorMenu(vista);
		new ControladorOpcionesCentros(vista, daoAnimales);
		new ControladorOpciones(vista);
		new ControladorSalir(vista);
	}
}
