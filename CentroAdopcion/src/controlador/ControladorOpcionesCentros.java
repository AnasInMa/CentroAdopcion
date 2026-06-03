package controlador;

import java.awt.event.*;

import modelo.CentroAdopcion;
import modelo.FicheroConCentros;
import vista.*;

public class ControladorOpcionesCentros implements MouseListener, ActionListener{

	private Vista vista;
	private VistaOpcionesCentros vOpcionesCentros;
	
	private CentroAdopcion centroAdopcion1, centroAdopcion2, centroAdopcion3, centroAdopcion4;
	
	public ControladorOpcionesCentros(Vista v) {
		
		vista = v;
		
		vOpcionesCentros = vista.getvOpcionesCentros();
		vOpcionesCentros.control(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == vOpcionesCentros.getbEntrarCentro1()) {

			if(centroAdopcion1 == null) {
				
				centroAdopcion1 = new CentroAdopcion(FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro1().getText()));
				
			}
			
			vista.añadeVistaCentroAdopcion(centroAdopcion1);

		} else if (e.getSource() == vOpcionesCentros.getbEntrarCentro2()) {
			
			if(centroAdopcion2 == null) {
				
				centroAdopcion2 = new CentroAdopcion(FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro2().getText()));
			}
			
			vista.añadeVistaCentroAdopcion(centroAdopcion2);

		} else if (e.getSource() == vOpcionesCentros.getbEntrarCentro3()) {
			
			if(centroAdopcion3 == null) {
				
				centroAdopcion3 = new CentroAdopcion(FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro3().getText()));
			}
			
			vista.añadeVistaCentroAdopcion(centroAdopcion3);
			
		} else if (e.getSource() == vOpcionesCentros.getbEntrarCentro4()) {
			
			if(centroAdopcion4 == null) {
				
				centroAdopcion4 = new CentroAdopcion(FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro4().getText()));
			}
			
			vista.añadeVistaCentroAdopcion(centroAdopcion4);
		}
		
		vista.muestraPanelCentro();
		new ControladorCentroAdopcion(vista);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getSource() == vOpcionesCentros.getMenu()) {
			
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
