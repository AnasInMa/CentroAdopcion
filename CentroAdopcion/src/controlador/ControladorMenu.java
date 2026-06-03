package controlador;

import java.awt.event.*;

import vista.*;

public class ControladorMenu implements ActionListener{

	private Vista vista;
	private VistaMenu vMenu;
	
	public ControladorMenu(Vista v) {
		
		vista = v;
		
		vMenu = v.getvMenu();
		vMenu.control(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == vMenu.getbComenzar()) {
			
			vista.muestraSiguientePanel();
			
		} else if(e.getSource() == vMenu.getbOpciones()) {
			
			vista.muestraPanelOpciones();
			
		} else {
			
			vista.muestraPanelSalir();
			
		}
		
	}

}
