package controlador;

import java.awt.event.*;
import java.sql.SQLException;

import modelo.DAOAnimales;
import vista.Vista;
import vista.VistaCentroAdopcion;

public class ControladorCentroAdopcion implements MouseListener, ActionListener {

	private Vista vista;
	private VistaCentroAdopcion vCentroAdopcion;
	private DAOAnimales dao;

	public ControladorCentroAdopcion(Vista v) {

		vista = v;

		vCentroAdopcion = vista.getvCentroAdopcion();
		vCentroAdopcion.control(this);

		try {

			dao = new DAOAnimales();

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == vCentroAdopcion.getbAdoptar()) {

			// System.out.println("adoptar");

		} else if (e.getSource() == vCentroAdopcion.getbDarEnAdopcion()) {
			
			//TODO

		} else if (e.getSource() == vCentroAdopcion.getbPrimero()) {

			try {

				dao.getCuatroPrimeros();

			} catch (Exception e1) {

				e1.printStackTrace();
			}
			
			System.out.println("primero");

		} else if (e.getSource() == vCentroAdopcion.getbAnterior()) {

			try {

				dao.getCuatroAnteriores();

			} catch (Exception e1) {

				e1.printStackTrace();
			}

		} else if (e.getSource() == vCentroAdopcion.getbSiguiente()) {

			try {

				dao.getCuatroSiguientes();

			} catch (Exception e1) {

				e1.printStackTrace();
			}

		} else {

			// System.out.println("boton ultimo");
			try {

				dao.getCuatroUltimos();

			} catch (Exception e1) {

				e1.printStackTrace();
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == vCentroAdopcion.getAtras()) {

			// System.out.println("atras");

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
