package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import controlador.ControladorCentroAdopcion;
import modelo.CentroAdopcion;

public class VistaCentroAdopcion extends JPanel {

	private static final long serialVersionUID = -8810666463018494123L;

	private CentroAdopcion centroAdopcion;
	private JLabel nombreCentro, datosCentro, atras;
	private JButton bPrimero, bAnterior, bSiguiente, bUltimo, bPedirCita, bAdoptar;
	
	//private static JPanel panel1, panel2, panel3, panel4;	//el panel1 sera el que estara visible al principio, y ya a partir de los botones se podra navegar por los paneles
	
	private int numAnimalesMaximos;

	private static LineBorder bordeLinea;
	private static EmptyBorder bordeVacio;

	static {

		bordeLinea = new LineBorder(Vista.MARRON_OSCURO3, 4);
		bordeVacio = new EmptyBorder(10, 10, 10, 10);
		
//		panel1 = new JPanel();
//		panel2 = new JPanel();
//		panel3 = new JPanel();
//		panel4 = new JPanel();
	}

	public VistaCentroAdopcion(CentroAdopcion centro) {

		centroAdopcion = centro;

		numAnimalesMaximos = centroAdopcion.getCapacidadMaxima();
		
		modificaPanelPrincipal();
	}
	
	public void control(ControladorCentroAdopcion c) {
		
		this.atras.addMouseListener(c);
		
		this.bPrimero.addActionListener(c);
		this.bAnterior.addActionListener(c);
		this.bSiguiente.addActionListener(c);
		this.bUltimo.addActionListener(c);
		
		this.bPedirCita.addActionListener(c);
		this.bAdoptar.addActionListener(c);
	}

	private void modificaPanelPrincipal() {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(bordeVacio);
		this.setBackground(Vista.MARRON_CLARO3);

		iniciaComponentes();
		modificaComponentes();

		this.add(panelDatosCentroAdopcion());
		this.add(new JLabel(" "));
		this.add(panelAnimales());
		this.add(new JLabel(" "));
		this.add(panelBotones());
	}

	private void iniciaComponentes() {

		// int cod, String nombre, String direccion, int codigoCentro, int codPostal,
		// short capacidadMaxima, TreeSet<Animal> animalesAlojados
		atras = new JLabel(" 🔁");
		nombreCentro = new JLabel(centroAdopcion.getNombre());
		datosCentro = new JLabel(" " + centroAdopcion.toStringSinNombre() + " ");

		bPrimero = new JButton("⏮️");
		bPrimero.setToolTipText("Primero");
		
		bAnterior = new JButton("◀️");
		bAnterior.setToolTipText("Anterior");
		
		bSiguiente = new JButton("▶️");
		bSiguiente.setToolTipText("Siguiente");
		
		bUltimo = new JButton("⏭️");
		bUltimo.setToolTipText("Ultimo");
		
		bPedirCita = new JButton("PEDIR CITA");
		bAdoptar = new JButton("ADOPTAR");

	}
	
	private void modificaComponentes() {

		atras.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
		
		//nombreCentro.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		nombreCentro.setHorizontalAlignment(JLabel.CENTER);
		nombreCentro.setFont(new Font(Font.SERIF, Font.BOLD, 40));

		datosCentro.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		datosCentro.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

		JComponent[] componentes = {atras, nombreCentro, datosCentro, bPrimero, bAnterior, bSiguiente, bUltimo, bPedirCita,
				bAdoptar };

		for (JComponent componente : componentes) {

			componente.setFocusable(false);

			componente.setForeground(Vista.MARRON_OSCURO4);
			componente.setBackground(Vista.MARRON_CLARO4);
		}
	}

	private JPanel panelDatosCentroAdopcion() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Vista.MARRON_CLARO3);
		panel.setBorder(bordeLinea);

		JPanel panelTitulo = new JPanel(new BorderLayout());
		//panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.X_AXIS));
		panelTitulo.setBackground(Vista.MARRON_CLARO3);
		
		panelTitulo.add(atras, BorderLayout.WEST);
		//panelTitulo.add(Box.createHorizontalGlue());
		panelTitulo.add(nombreCentro, BorderLayout.CENTER);
		panelTitulo.setMaximumSize(new Dimension(Vista.ANCHO_PANEL * 2, Vista.ALTO_COMPONENTE));
		
		panel.add(panelTitulo);
		panel.add(datosCentro);

		return panel;
	}

	private JPanel panelAnimales() {

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBorder(bordeLinea);
		panelPrincipal.setBackground(Vista.MARRON_CLARO3);

		JPanel panelSecundario = new JPanel();
		panelSecundario.setBorder(bordeVacio);
		panelSecundario.setBackground(Vista.MARRON_CLARO3);
		//panelSecundario.add(panelesAnimalesCuatro(/*panelSecundario*/));

		panelesAnimales();
		
		panelPrincipal.add(panelSecundario);

		return panelPrincipal;
	}
	
	private void panelesAnimales() {
		
		byte filas = (byte) Math.ceil(centroAdopcion.getAnimalesAlojados().size() / 4); // si tiene 6 animales habran 2 filas, si
																						// hay 13 animales habran 4 filas, si
																						// hay 3 animales solo habra 1 fila
		
		for(int i = 0; i < filas; i++) {
			
			
		}
	}
	
	private JPanel[] arrayPanelesAnimales() {
		
		JPanel[] paneles = new JPanel[numAnimalesMaximos];
		
		for(int i = 0; i < numAnimalesMaximos; i++) {
			
			paneles[i] = new JPanel();
		}
		
		return paneles;
	}

	/**
	 * Metodo que crea una matriz de 4 columnas, y las filas dependeran de los
	 * animales que estan alojados en el centro de adopcion.
	 * 
	 * Se le pasara un array de paneles, que seran los 4 paneles que estan visibles
	 * al inicio, y siempre estaran visbles, lo que cambiara sera su contenido
	 * 
	 * @return
	 */
	/*private JPanel[][] panelesAnimalesCuatro(JPanel[] panelesVisibles) {

		byte filas = (byte) Math.ceil(centroAdopcion.getAnimalesAlojados().size() / 4); // si tiene 6 animales habran 2 filas, si
																				// hay 13 animales habran 4 filas, si
																				// hay 3 animales solo habra 1 fila
		JPanel[][] panelesAnimales = new JPanel[filas][4];
		
		for(int fil = 0; fil < filas; fil++) {
			
			for(int col = 0; col < 4; col++) {
				
				
			}
		}

		return null;

	}*/

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
		panel.add(bPedirCita);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(bAdoptar);

		return panel;
	}

	public JLabel getDatosCentro() {
		return datosCentro;
	}

	public CentroAdopcion getCentroAdopcion() {
		return centroAdopcion;
	}

	public JLabel getAtras() {
		return atras;
	}

	public JButton getbPrimero() {
		return bPrimero;
	}

	public JButton getbAnterior() {
		return bAnterior;
	}

	public JButton getbSiguiente() {
		return bSiguiente;
	}

	public JButton getbUltimo() {
		return bUltimo;
	}

	public JButton getbPedirCita() {
		return bPedirCita;
	}

	public JButton getbAdoptar() {
		return bAdoptar;
	}
	
	/*
	public void setCentroAdopcion(CentroAdopcion centroAdopcion) {
		this.centroAdopcion = centroAdopcion;
	}
	*/
}
