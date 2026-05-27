package controlador;

import java.awt.event.*;
import java.io.*;

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
			
			guardarColor();
			
			System.exit(0);
		}
		
	}
	
	private void guardarColor() {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(Vista.FicheroColor), false))) {
			
			oos.writeObject(Vista.ColoresVisibles);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
