package controlador;

import java.awt.event.*;

import modelo.CentroAdopcion;
import modelo.FicheroConCentros;
import vista.*;

public class ControladorOpcionesCentros implements MouseListener, ActionListener{

	private Vista vista;
	private VistaOpcionesCentros vOpcionesCentros;
	//private static int cont1, cont2, cont3, cont4;	//contadores para controlar que los centros solo se creen una vez
	
	/*static {
		
		cont1 = 0;
		cont2 = 0;
		cont3 = 0;
		cont4 = 0;
	}*/
	
	private CentroAdopcion centroAdopcion1, centroAdopcion2, centroAdopcion3, centroAdopcion4;
	
	public ControladorOpcionesCentros(Vista v) {
		
		vista = v;
		
		vOpcionesCentros = vista.getvOpcionesCentros();
		vOpcionesCentros.control(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == vOpcionesCentros.getbEntrarCentro1()) {

			// System.out.println("centro1");
			/*if(cont == 0) {
				
				centroAdopcion1 = FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro1());
				cont1++;
				
			}
			
			vista.añadeVistaCentroAdopcion(centroAdopcion1);
			vista.muestraPanelCentro();
			*/
			
			if(centroAdopcion1 == null) {
				
				centroAdopcion1 = new CentroAdopcion(FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro1()));
				
				//System.out.println("centro " + centroAdopcion1.getNombre() + " creado");
			}
			
			vista.añadeVistaCentroAdopcion(centroAdopcion1);

		} else if (e.getSource() == vOpcionesCentros.getbEntrarCentro2()) {
			
			if(centroAdopcion2 == null) {
				
				centroAdopcion2 = new CentroAdopcion(FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro2()));
			}
			
			vista.añadeVistaCentroAdopcion(centroAdopcion2);

		} else if (e.getSource() == vOpcionesCentros.getbEntrarCentro3()) {
			
			if(centroAdopcion3 == null) {
				
				centroAdopcion3 = new CentroAdopcion(FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro3()));
			}
			
			vista.añadeVistaCentroAdopcion(centroAdopcion3);
			
		} else if (e.getSource() == vOpcionesCentros.getbEntrarCentro4()) {
			
			if(centroAdopcion4 == null) {
				
				centroAdopcion4 = new CentroAdopcion(FicheroConCentros.buscaCentro(vOpcionesCentros.getNombreCentro4()));
			}
			
			vista.añadeVistaCentroAdopcion(centroAdopcion4);
		}
		
		//vista.getvCentroAdopcion().setCentroAdopcion(centroAdopcion);
		//vista.añadeVistaCentroAdopcion(centroAdopcion);

		vista.muestraPanelCentro();
		new ControladorCentroAdopcion(vista);
	}
	
	/*private void creaYAñadeCentro(CentroAdopcion centro, String nombre) {
		
		if(centro == null) {
			
			centro = new CentroAdopcion(FicheroConCentros.buscaCentro(nombre));
			
			System.out.println("centro " + centro.getNombre() + " creado");
		}
		
		vista.añadeVistaCentroAdopcion(centro);
	}*/

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
