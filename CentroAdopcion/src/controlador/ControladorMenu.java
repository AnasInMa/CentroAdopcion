package controlador;

import java.awt.event.*;

import vista.*;

public class ControladorMenu implements ActionListener{

	private Vista vista;
	private VistaMenu vMenu;
	
	public ControladorMenu(Vista v1) {
		
		vista = v1;
		
		vMenu = v1.getvMenu();
		vMenu.control(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == vMenu.getbComenzar()) {
			
			//System.out.println("comenzar");
			
			vista.siguientePanel();
			
		} else if(e.getSource() == vMenu.getbOpciones()) {
			
			//System.out.println("opciones");
			
		} else {
			
			vista.muestraPanelSalir();
			
			/*
			int opcion = JOptionPane.showConfirmDialog(vMenu, "¿Esta seguro que desea salir?", "Confirmacion", JOptionPane.YES_NO_OPTION);
			
			if(opcion == JOptionPane.YES_OPTION) {
				
				System.exit(0);				
			}
			*/
		}
		
	}

}
