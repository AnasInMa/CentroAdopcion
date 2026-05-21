package controlador;

import java.awt.event.*;

import modelo.CentroAdopcion;
import test.FicheroConCentros;
import vista.*;

public class ControladorOpcionesCentros implements MouseListener, ActionListener{

	private Vista vista;
	private VistaOpcionesCentros vOpcionesCentros;
	private static int cont1, cont2, cont3, cont4;	//contadores para controlar que los centros solo se creen una vez
	
	static {
		
		cont1 = 0;
		cont2 = 0;
		cont3 = 0;
		cont4 = 0;
	}
	
	private CentroAdopcion centroAdopcion;
	
	public ControladorOpcionesCentros(Vista v) {
		
		vista = v;
		
		vOpcionesCentros = vista.getvOpcionesCentros();
		vOpcionesCentros.control(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == vOpcionesCentros.getbEntrarCentro1() && cont1 == 0) {

			// System.out.println("centro1");
			centroAdopcion = FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro1());
			
			cont1++;

		} else if (e.getSource() == vOpcionesCentros.getbEntrarCentro2() && cont2 == 0) {
			
			centroAdopcion = FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro2());
			
			cont2++;

		} else if (e.getSource() == vOpcionesCentros.getbEntrarCentro3() && cont3 == 0) {
			
			centroAdopcion = FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro3());
			
			cont3++;
			
		} else if (e.getSource() == vOpcionesCentros.getbEntrarCentro4() && cont4 == 0) {	//centro4
			
			centroAdopcion = FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro4());
			
			cont4++;
		}
		
		//vista.getvCentroAdopcion().setCentroAdopcion(centroAdopcion);
		vista.añadeVistaCentroAdopcion(centroAdopcion);
		vista.muestraPanelCentro();

		new ControladorCentroAdopcion(vista);
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
