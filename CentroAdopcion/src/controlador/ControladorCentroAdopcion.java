package controlador;

import java.awt.event.*;

import vista.Vista;
import vista.VistaCentroAdopcion;

public class ControladorCentroAdopcion implements MouseListener, ActionListener{

	Vista vista;
	VistaCentroAdopcion vCentroAdopcion;
	
	public ControladorCentroAdopcion(Vista v) {
		
		vista = v;
		
		vCentroAdopcion = vista.getvCentroAdopcion();
		vCentroAdopcion.control(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == vCentroAdopcion.getbAdoptar()) {
			
			//System.out.println("adoptar");
			
			
			
		} else if (e.getSource() == vCentroAdopcion.getbPedirCita()) {
			
			
			
		} else if (e.getSource() == vCentroAdopcion.getbPrimero()) {
			
			
			
		} else if (e.getSource() == vCentroAdopcion.getbAnterior()) {
			
			
			
		} else if (e.getSource() == vCentroAdopcion.getbSiguiente()) {
			
			
			
		} else {
			
			//System.out.println("boton ultimo");
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == vCentroAdopcion.getAtras()) {

			//System.out.println("atras");
			
			vista.muestraPanelOpcionesCentros();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
