package controlador;

import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import modelo.DAOAnimales;
import modelo.DAOPersonas;
import utilidades.UtilidadesFicherosLista;
import utilidades.UtilidadesVariables;
import vista.*;

public class ControladorCentroAdopcion implements MouseListener, ActionListener {

	private JFrame ventanaPadre;
	
	private Vista vista;
	private VistaCentroAdopcion vCentroAdopcion;
	private VistaAnimales vAnimales;
	
	private DAOAnimales daoAnimales;
	private DAOPersonas daoPersonas;

	public ControladorCentroAdopcion(Vista v) {

		vista = v;

		vCentroAdopcion = vista.getvCentroAdopcion();
		vCentroAdopcion.control(this);
		
		vAnimales = vCentroAdopcion.getVistaAnimales();
		
		daoAnimales = vAnimales.getDao();
		
		try {
			
			daoPersonas = new DAOPersonas();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		ventanaPadre = (JFrame) SwingUtilities.getWindowAncestor(vista);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == vCentroAdopcion.getbAdoptar()) {
			
			try {
				
				DialogoAdoptar d = new DialogoAdoptar(ventanaPadre, vAnimales.panelAnimalSeleccionado(), daoAnimales, daoPersonas);
				
				if(d.getAnimalAdoptado() != null) {
					
					UtilidadesFicherosLista.guardarAnimalEnFichero(daoAnimales.adoptaAnimal(d.getAnimalAdoptado().getIDAnimal(), d.getIdPersona()), UtilidadesVariables.archivoAnimalesAdoptados);
					
					this.vCentroAdopcion.getCentroAdopcion().setAnimalesAlojados(daoAnimales.getAnimalesCentro(this.vCentroAdopcion.getCentroAdopcion()));
					
					JOptionPane.showMessageDialog(vAnimales, "Gracias " + d.getNombrePersona() + " por adoptar a " +  d.getAnimalAdoptado().getNombre() + "!", "Adoptado en " + this.vCentroAdopcion.getCentroAdopcion().getNombre(), JOptionPane.INFORMATION_MESSAGE);
					
					this.vista.muestraPanelOpcionesCentros();
				}
				
				
			} catch (NullPointerException error) {
				
				JOptionPane.showMessageDialog(vCentroAdopcion, "Debe de seleccionar un animal para poder adoptarlo" , "ERROR", JOptionPane.ERROR_MESSAGE);
				
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}

		} else if (e.getSource() == vCentroAdopcion.getbDarEnAdopcion()) {
			
			DialogoDarEnAdopcion d = null;
			
			if(this.vCentroAdopcion.getCentroAdopcion().puedeAlojar()) {
				
				d = new DialogoDarEnAdopcion(ventanaPadre, vCentroAdopcion.getCentroAdopcion(), daoPersonas, daoAnimales);
				
			} else {
				
				JOptionPane.showMessageDialog(vCentroAdopcion, "Lo siento. No es posible alojar mas animales en " + this.vCentroAdopcion.getCentroAdopcion().getNombre() , "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
			if(d != null && d.isHaPulsadoBotonConfirmar()) this.vista.muestraPanelOpcionesCentros();
			
		} else if (e.getSource() == vCentroAdopcion.getbPrimero()) {

			vAnimales.primeraFila();

		} else if (e.getSource() == vCentroAdopcion.getbAnterior()) {

			vAnimales.anteriorFila();

		} else if (e.getSource() == vCentroAdopcion.getbSiguiente()) {

			
			vAnimales.siguienteFila();

		} else {

			vAnimales.ultimaFila();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == vCentroAdopcion.getAtras()) {

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

	public DAOAnimales getDaoAnimales() {
		return daoAnimales;
	}

	public DAOPersonas getDaoPersonas() {
		return daoPersonas;
	}
}
