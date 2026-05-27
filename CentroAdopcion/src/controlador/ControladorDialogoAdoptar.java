package controlador;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import vista.DialogoAdoptar;
import vista.Vista;
import vista.VistaAnimales;

public class ControladorDialogoAdoptar implements ActionListener{

	private DialogoAdoptar dAdoptar;
	
	public ControladorDialogoAdoptar(DialogoAdoptar d) {
		
		dAdoptar = d;
		
		dAdoptar.control(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == dAdoptar.getbConfirmar()) {
			
			System.out.println("confirmar");
			
		} else if (e.getSource() == dAdoptar.getbCancelar()) {
			
			
			
		} else {	//Elegir persona
			
			
		}
		
	}

}
