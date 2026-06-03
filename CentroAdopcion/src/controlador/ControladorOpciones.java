package controlador;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import modelo.EnumColores;
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
				
				//vista.getvMenu().getbComenzar().setPreferredSize(new Dimension((int) (anchoBoton * 1.5), (int) (altoBoton * 1.5)));
				//vista.getvMenu().getbOpciones().setPreferredSize(new Dimension((int) (anchoBoton * 1.5), (int) (altoBoton * 1.5)));
				//vista.getvMenu().getbSalir().setPreferredSize(new Dimension((int) (anchoBoton * 1.5), (int) (altoBoton * 1.5)));
				vista.getvOpcionesCentros().getTexto().setFont(Vista.FuenteTextoPC);
				
			} else {
				
				ventanaPadre.setExtendedState(JFrame.NORMAL);
				
				//vista.getvMenu().getbComenzar().setPreferredSize(new Dimension((int) (anchoBoton), (int) (altoBoton)));
				//vista.getvMenu().getbOpciones().setPreferredSize(new Dimension((int) (anchoBoton), (int) (altoBoton)));
				//vista.getvMenu().getbSalir().setPreferredSize(new Dimension((int) (anchoBoton), (int) (altoBoton)));
				vista.getvOpcionesCentros().getTexto().setFont(Vista.FuenteTexto);
			}
			
			estaSeleccionado = VistaOpciones.getCbPantallaCompleta().isSelected();
			
			actualizarColores(vOpciones.getCmbColores().getSelectedItem().toString());
			
			guardarOpciones();
			
		} else {
			
			VistaOpciones.getCbPantallaCompleta().setSelected(estaSeleccionado);
		}

		vOpciones.getCmbColores().setSelectedItem(EnumColores.buscaNombrePorColores(Vista.ColoresVisibles));
		vista.muestraPrimerPanel();

	}

	private void guardarOpciones() {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Vista.FicheroOpciones), false))) {

			bw.write(Boolean.toString(VistaOpciones.getCbPantallaCompleta().isSelected()));
			bw.newLine();
			bw.write(vista.getvOpciones().getCmbColores().getSelectedItem().toString());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		VistaCentroAdopcion.BordeLinea = new LineBorder(Vista.TEXTO_OSCURO, VistaCentroAdopcion.BordeLinea.getThickness());
		
		vista.getvMenu().actualizarColores();
	    vista.getvOpciones().actualizarColores();
	    vista.getvOpcionesCentros().actualizarColores();
	    vista.getvSalir().actualizarColores();
	}
	
}
