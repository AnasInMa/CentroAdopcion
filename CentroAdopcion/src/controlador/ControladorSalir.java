package controlador;

import java.awt.event.*;
import java.sql.SQLException;

import vista.*;

public class ControladorSalir implements ActionListener{

	private Vista vista;
	private VistaSalir vSalir;
	
	private ControladorOpcionesCentros controlador;
	
	public ControladorSalir(ControladorOpcionesCentros c, Vista v) {
		
		vista = v;
		controlador = c;
		
		vSalir = vista.getvSalir();
		vSalir.control(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == vSalir.getbCancelarSalida()) {
			
			vista.muestraPrimerPanel();
			
		} else {
			
			try {
				
				controlador.getcCentroAdopcion().getDaoAnimales().cierraStatement();
				controlador.getcCentroAdopcion().getDaoAnimales().cierraConexion();
				
				controlador.getcCentroAdopcion().getDaoPersonas().cierraStatement();
				controlador.getcCentroAdopcion().getDaoPersonas().cierraConexion();
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			System.exit(0);
		}
	}

}
