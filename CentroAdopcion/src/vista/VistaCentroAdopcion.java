package vista;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modelo.CentroAdopcion;

public class VistaCentroAdopcion extends JPanel{

	private static final long serialVersionUID = -8810666463018494123L;
	
	private CentroAdopcion centroAdopcion;
	private JLabel nombreCentro, datosCentro;
	
	public VistaCentroAdopcion(CentroAdopcion centro) {
		
		centroAdopcion = centro;
		
		modificaPanelPrincipal();
	}

	private void modificaPanelPrincipal() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(10,10,10,10));
		this.setBackground(Vista.MARRON_CLARO3);
		
		iniciaComponentes();
		modificaComponentes();
		
		this.add(panelDatosCentroAdopcion());
		this.add(panelAnimales());
	}
	
	private void iniciaComponentes() {
		
		//int cod, String nombre, String direccion, int codigoCentro, int codPostal, short capacidadMaxima, TreeSet<Animal> animalesAlojados
		nombreCentro = new JLabel(centroAdopcion.getNombre());
		datosCentro = new JLabel(" " + centroAdopcion.toStringSinNombre() + " ");
		
	}
	
	private JPanel panelDatosCentroAdopcion() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Vista.MARRON_CLARO3);
		panel.setBorder(new LineBorder(Vista.MARRON_CLARO4, 4));
		
		panel.add(nombreCentro);
		panel.add(datosCentro);
		
		return panel;
	}
	
	private JPanel panelAnimales() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Vista.MARRON_CLARO3);
		panel.setBorder(new LineBorder(Vista.MARRON_CLARO4, 4));
		
		
		return panel;
	}
	
	private void modificaComponentes() {
		
		nombreCentro.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		nombreCentro.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		
		datosCentro.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		datosCentro.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		
		JComponent[] componentes = {nombreCentro, datosCentro};
		
		for(JComponent componente : componentes) {
			
			componente.setForeground(Vista.MARRON_OSCURO4);
		}
	}
	

}
