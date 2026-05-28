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
			
			guardarOpciones();
			
			System.exit(0);
		}
		
	}
	
	private void guardarOpciones() {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Vista.FicheroOpciones), false))) {
			
			bw.write(VistaOpciones.getCbPantallaCompleta().toString());
			bw.write(vista.getvOpciones().getCmbColores().getSelectedItem().toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
