package controlador;

import java.awt.event.*;

import vista.Vista;

public class Controlador implements ActionListener{

	Vista vista;
	
	public Controlador(Vista v) {
		
		vista = v;
		
		vista.control(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == vista.getbComenzar()) {
			
			
		}
		
	}

}
