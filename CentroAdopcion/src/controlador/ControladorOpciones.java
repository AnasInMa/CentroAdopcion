package controlador;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import modelo.EnumColores;
import utilidades.UtilidadesVariables;
import vista.*;

public class ControladorOpciones implements ActionListener {

	private Vista vista;
	private VistaOpciones vOpciones;
	private boolean estaSeleccionado;
	private JFrame ventanaPadre;
	
	double anchoBoton;
	double altoBoton;

	public ControladorOpciones(Vista v) {

		vista = v;

		vOpciones = vista.getvOpciones();
		vOpciones.control(this);
		
		ventanaPadre = (JFrame) SwingUtilities.getWindowAncestor(vista);
		estaSeleccionado = VistaOpciones.getCbPantallaCompleta().isSelected();
		
		anchoBoton = vista.getvMenu().getbComenzar().getPreferredSize().getWidth();
		altoBoton = vista.getvMenu().getbComenzar().getPreferredSize().getHeight();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == vOpciones.getbGuardarCambios()) {

			if (VistaOpciones.getCbPantallaCompleta().isSelected()) {

				ventanaPadre.setExtendedState(JFrame.MAXIMIZED_BOTH);
				
				vista.getvOpcionesCentros().getTexto().setFont(UtilidadesVariables.FuenteTextoPC);
				
			} else {
				
				ventanaPadre.setExtendedState(JFrame.NORMAL);
				
				vista.getvOpcionesCentros().getTexto().setFont(UtilidadesVariables.FuenteTexto);
			}
			
			estaSeleccionado = VistaOpciones.getCbPantallaCompleta().isSelected();
			
			actualizarColores(vOpciones.getCmbColores().getSelectedItem().toString());
			
			guardarOpciones();
			
		} else {
			
			VistaOpciones.getCbPantallaCompleta().setSelected(estaSeleccionado);
		}

		vOpciones.getCmbColores().setSelectedItem(EnumColores.buscaNombrePorColores(UtilidadesVariables.ColoresVisibles));
		vista.muestraPrimerPanel();

	}

	private void guardarOpciones() {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(UtilidadesVariables.FicheroOpciones), false))) {

			bw.write(Boolean.toString(VistaOpciones.getCbPantallaCompleta().isSelected()));
			bw.newLine();
			bw.write(vista.getvOpciones().getCmbColores().getSelectedItem().toString());

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void actualizarColores(String nombreColor) {
		
		UtilidadesVariables.ColoresVisibles = EnumColores.buscaColoresPorNombre(nombreColor);
		
		UtilidadesVariables.FONDO_DATOS = UtilidadesVariables.ColoresVisibles[0];
		UtilidadesVariables.FONDO_ANIMALES = UtilidadesVariables.ColoresVisibles[1];
		UtilidadesVariables.FONDO_PRINCIPAL = UtilidadesVariables.ColoresVisibles[2];
		UtilidadesVariables.FONDO_BOTON = UtilidadesVariables.ColoresVisibles[3];
		
		UtilidadesVariables.TEXTO_CLARO = UtilidadesVariables.ColoresVisibles[4];
		UtilidadesVariables.TEXTO_OSCURO = UtilidadesVariables.ColoresVisibles[5];

		UtilidadesVariables.BordeLinea = new LineBorder(UtilidadesVariables.TEXTO_OSCURO, UtilidadesVariables.BordeLinea.getThickness());
		
		vista.getvMenu().actualizarColores();
	    vista.getvOpciones().actualizarColores();
	    vista.getvOpcionesCentros().actualizarColores();
	    vista.getvSalir().actualizarColores();
	}
	
}
