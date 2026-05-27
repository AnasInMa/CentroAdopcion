package controlador;

import java.awt.event.*;

import javax.swing.*;

import vista.DialogoAdoptar;
import vista.Vista;
import vista.VistaAnimales;
import vista.VistaCentroAdopcion;

public class ControladorCentroAdopcion implements MouseListener, ActionListener {

	private Vista vista;
	private VistaCentroAdopcion vCentroAdopcion;
	private VistaAnimales vAnimales;

	public ControladorCentroAdopcion(Vista v) {

		vista = v;

		vCentroAdopcion = vista.getvCentroAdopcion();
		vCentroAdopcion.control(this);
		
		vAnimales = vCentroAdopcion.getVistaAnimales();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == vCentroAdopcion.getbAdoptar()) {

			// System.out.println("adoptar");
			
			try {
				
				/*DialogoAdoptar d =*/ new DialogoAdoptar((JFrame) SwingUtilities.getWindowAncestor(vista), vAnimales.panelAnimalSeleccionado());
				//new ControladorDialogoAdoptar(d);
				
			} catch (NullPointerException error) {
				
				JOptionPane.showMessageDialog(vCentroAdopcion, "Debe de seleccionar un animal para poder adoptarlo" , "ERROR", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == vCentroAdopcion.getbDarEnAdopcion()) {
			
			//TODO

		} else if (e.getSource() == vCentroAdopcion.getbPrimero()) {

			//System.out.println("primero");
			
			/*try {

				dao.getCuatroPrimeros();

			} catch (Exception e1) {

				e1.printStackTrace();
			}*/
			
			vAnimales.primeraFila();

		} else if (e.getSource() == vCentroAdopcion.getbAnterior()) {

			/*try {

				dao.getCuatroAnteriores();

			} catch (Exception e1) {

				e1.printStackTrace();
			}*/
			
			vAnimales.anteriorFila();

		} else if (e.getSource() == vCentroAdopcion.getbSiguiente()) {

			/*try {

				dao.getCuatroSiguientes();

			} catch (Exception e1) {

				e1.printStackTrace();
			}*/
			
			vAnimales.siguienteFila();

		} else {

			// System.out.println("boton ultimo");
			/*try {

				dao.getCuatroUltimos();

			} catch (Exception e1) {

				e1.printStackTrace();
			}*/
			
			vAnimales.ultimaFila();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == vCentroAdopcion.getAtras()) {

			// System.out.println("atras");

			vista.muestraPanelOpcionesCentros();
			vAnimales.primeraFila();	//si no pusiese esto el contador no se resetearia, y entonces al salir del centro y el contador es 3 por ejemplo, al volver al entrar otra vez al mismo centro se muestra el primer panel de los animales, pero el contador sigue siendo 3, entonces si quisieras navegar por los botones de abajo no iria como se espera que fuese
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
