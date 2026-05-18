package controlador;

import java.awt.event.*;

import vista.*;

public class ControladorOpcionesCentros implements MouseListener{

	private Vista vista;
	private VistaOpcionesCentros vOpcionesCentros;
	
	public ControladorOpcionesCentros(Vista v) {
		
		vista = v;
		
		vOpcionesCentros = vista.getvOpcionesCentros();
		
		vOpcionesCentros.control(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getSource() == vOpcionesCentros.getMenu()) {
			
			//System.out.println("menu");
			vista.muestraAnteriorPanel();
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
