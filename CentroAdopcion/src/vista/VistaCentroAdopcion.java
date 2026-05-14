package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import modelo.Animal;
import modelo.CentroAdopcion;

public class VistaCentroAdopcion extends JPanel{

	private static final long serialVersionUID = -8810666463018494123L;
	
	private CentroAdopcion centroAdopcion;
	private JLabel nombreCentro, datosCentro;
	private JButton bPrimero, bAnterior, bSiguiente, bUltimo, bAgendarCita, bAdoptar;
	private LineBorder bordeLinea;
	
	public VistaCentroAdopcion(CentroAdopcion centro) {
		
		centroAdopcion = centro;
		
		modificaPanelPrincipal();
	}

	private void modificaPanelPrincipal() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(10,10,10,10));
		this.setBackground(Vista.MARRON_CLARO3);
		
		bordeLinea = new LineBorder(Vista.MARRON_OSCURO3, 4);
		
		iniciaComponentes();
		modificaComponentes();
		
		this.add(panelDatosCentroAdopcion());
		this.add(new JLabel(" "));
		this.add(panelAnimales());
		this.add(new JLabel(" "));
		this.add(panelBotones());
	}
	
	private void iniciaComponentes() {
		
		//int cod, String nombre, String direccion, int codigoCentro, int codPostal, short capacidadMaxima, TreeSet<Animal> animalesAlojados
		nombreCentro = new JLabel(centroAdopcion.getNombre());
		datosCentro = new JLabel(" " + centroAdopcion.toStringSinNombre() + " ");
		
		bPrimero = new JButton("<<");
		bAnterior = new JButton("<");
		bSiguiente = new JButton(">");
		bUltimo = new JButton(">>");
		bAgendarCita = new JButton("PEDIR CITA");
		bAdoptar = new JButton("ADOPTAR");
		
	}
	
	private JPanel panelDatosCentroAdopcion() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Vista.MARRON_CLARO3);
		panel.setBorder(bordeLinea);
		
		panel.add(nombreCentro);
		panel.add(datosCentro);
		
		return panel;
	}
	
	private JPanel panelAnimales() {
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.X_AXIS));
		panelPrincipal.setBackground(Vista.MARRON_CLARO3);
		panelPrincipal.setBorder(bordeLinea);
		
		JPanel panelAnimal;
		
		for(Animal animal : this.centroAdopcion.getAnimalesAlojados()) {
			
			panelAnimal = new JPanel();
			panelAnimal.setLayout(new BoxLayout(panelAnimal, BoxLayout.Y_AXIS));
			panelAnimal.setBackground(Vista.MARRON_CLARO3);
			
			panelAnimal.add(new JLabel(animal.getNombre()));
			panelAnimal.add(new JRadioButton());
			panelAnimal.add(new JLabel(animal.toStringSinCodigo()));
			
			panelPrincipal.add(panelAnimal);
		}
		
		return panelPrincipal;
	}
	
	private JPanel panelBotones() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(Vista.MARRON_CLARO3);
		panel.setBorder(bordeLinea);
		
		panel.add(bPrimero);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(bAnterior);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(bSiguiente);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(bUltimo);
		panel.add(Box.createHorizontalGlue());
		panel.add(bAgendarCita);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(bAdoptar);
		
		return panel;
	}
	
	private void modificaComponentes() {
		
		nombreCentro.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		nombreCentro.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		
		datosCentro.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		datosCentro.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		
		JComponent[] componentes = {nombreCentro, datosCentro,
									bPrimero, bAnterior, bSiguiente,
									bUltimo, bAgendarCita, bAdoptar};
		
		for(JComponent componente : componentes) {
			
			componente.setFocusable(false);
			
			componente.setForeground(Vista.MARRON_OSCURO4);
			componente.setBackground(Vista.MARRON_CLARO4);
		}
	}
	
}
