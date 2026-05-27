package controlador;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import modelo.EnumColores;
import vista.*;

public class ControladorOpciones implements ActionListener {

	private Vista vista;
	private VistaOpciones vOpciones;
	private boolean estaSeleccionado;
	private JFrame ventanaPadre;

	public ControladorOpciones(Vista v) {

		vista = v;

		vOpciones = vista.getvOpciones();
		vOpciones.control(this);
		
		ventanaPadre = (JFrame) SwingUtilities.getWindowAncestor(vista);
		estaSeleccionado = VistaOpciones.getCbPantallaCompleta().isSelected();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == vOpciones.getbGuardarCambios()) {

			// System.out.println("guardar");

			if (VistaOpciones.getCbPantallaCompleta().isSelected()) {

				//System.out.println("activo");

				//ventanaPadre.dispose();
				//ventanaPadre.setVisible(false);
				//ventanaPadre.setUndecorated(true);
				ventanaPadre.setExtendedState(JFrame.MAXIMIZED_BOTH);
				//ventanaPadre.setVisible(true);
				
				vista.getvOpcionesCentros().getTexto().setFont(Vista.FuenteTextoPC);

			} else {

				//System.out.println("no");
				
				ventanaPadre.setExtendedState(JFrame.NORMAL);
				vista.getvOpcionesCentros().getTexto().setFont(Vista.FuenteTexto);
			}
			
			estaSeleccionado = VistaOpciones.getCbPantallaCompleta().isSelected();
			
			actualizarColores(vOpciones.getCmbColores().getSelectedItem().toString());
			
		} else {
			
			VistaOpciones.getCbPantallaCompleta().setSelected(estaSeleccionado);
		}

		vista.muestraPrimerPanel();

	}

	private void actualizarColores(String nombreColor) {
		
		//System.out.println(Arrays.toString(Vista.ColoresVisibles));
		Vista.ColoresVisibles = EnumColores.buscaColoresPorNombre(nombreColor);
		//System.out.println(Arrays.toString(Vista.ColoresVisibles));
		
		Vista.FONDO_DATOS = Vista.ColoresVisibles[0];
		Vista.FONDO_ANIMALES = Vista.ColoresVisibles[1];
		Vista.FONDO_PRINCIPAL = Vista.ColoresVisibles[2];
		Vista.FONDO_BOTON = Vista.ColoresVisibles[3];
		
		Vista.TEXTO_CLARO = Vista.ColoresVisibles[4];
		Vista.TEXTO_OSCURO = Vista.ColoresVisibles[5];
		
		vista.getvMenu().actualizarColores();
	    vista.getvOpciones().actualizarColores();
	    vista.getvOpcionesCentros().actualizarColores();
	    vista.getvSalir().actualizarColores();
	    
	    VistaCentroAdopcion.BordeLinea = new LineBorder(Vista.TEXTO_OSCURO, VistaCentroAdopcion.BordeLinea.getThickness());
		
		//vista.repaint();
	}
	
}
