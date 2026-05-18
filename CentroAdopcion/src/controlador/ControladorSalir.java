package controlador;

import java.awt.event.*;

import vista.*;

public class ControladorSalir implements ActionListener{

	private Vista vista;
	private VistaSalir vSalir;
	
	public ControladorSalir(Vista v) {
		
		vista = v;
		
		vSalir = vista.getvSalir();
		vSalir.control(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == vSalir.getbCancelarSalida()) {
			
			vista.muestraPrimerPanel();
			
		} else {
			
			System.exit(0);
		}
		
	}

}
