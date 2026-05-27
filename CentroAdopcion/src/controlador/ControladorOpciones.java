package controlador;

import java.awt.event.*;

import javax.swing.*;

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

			} else {

				//System.out.println("no");
				
				ventanaPadre.setExtendedState(JFrame.NORMAL);
			}
			
			estaSeleccionado = VistaOpciones.getCbPantallaCompleta().isSelected();
			
			Vista.ColoresVisibles = EnumColores.buscaColoresPorNombre(vOpciones.getCmbColores().getSelectedItem().toString());
			
		} else {
			
			VistaOpciones.getCbPantallaCompleta().setSelected(estaSeleccionado);
		}

		vista.muestraPrimerPanel();

	}

}
