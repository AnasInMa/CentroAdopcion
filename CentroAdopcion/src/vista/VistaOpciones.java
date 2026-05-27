package vista;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import controlador.ControladorOpciones;
import modelo.EnumColores;

public class VistaOpciones extends JPanel{

	private static final long serialVersionUID = 4344532032686248546L;
	
	private static JCheckBox cbPantallaCompleta;
	private JLabel lColores;
	private JComboBox<String> cmbColores;
	private JButton bGuardarCambios, bCancelarCambios;
	
	private static final ImageIcon IMG_EQUIS, IMG_SINMARCAR;
	
	static {
		
		IMG_EQUIS = new ImageIcon("./imgs/equis.png");
		IMG_SINMARCAR = new ImageIcon("./imgs/sinMarcar.png");
	}
	
	public VistaOpciones() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Vista.FONDO_PRINCIPAL);
		
		iniciaYModificaComponentes();
		
		this.add(Box.createVerticalGlue());
		this.add(panelPantallaCompleta());
		//this.add(panelElegirColor());
		this.add(panelBotones());
		this.add(Box.createVerticalGlue());
	}
	
	public void control(ControladorOpciones c) {
		
		//this.cbPantallaCompleta.addActionListener(c);
		this.bGuardarCambios.addActionListener(c);
		this.bCancelarCambios.addActionListener(c);
	}
	
	private void iniciaYModificaComponentes() {
		
		cbPantallaCompleta = new JCheckBox("  Pantalla Completa");
		cbPantallaCompleta.setIcon(IMG_SINMARCAR);			//la imagen que tendra por defecto (cuando no esta seleccionado)
		cbPantallaCompleta.setSelectedIcon(IMG_EQUIS);		//la imagen que tendra cuando este seleccionado
		
		this.lColores = new JLabel("Color de la Vista: ");
		
		this.cmbColores = new JComboBox<String>(EnumColores.getNombreColores());
		this.cmbColores.setSelectedItem("Gris");
		this.cmbColores.setFont(Vista.FUENTE_BOTONES);
		
		this.bGuardarCambios = new JButton("Guardar");
		this.bCancelarCambios = new JButton("Cancelar");

		JComponent[] componentes1 = {cbPantallaCompleta, bGuardarCambios, bCancelarCambios, cmbColores, lColores};
		
		for (JComponent componente : componentes1) {

			if (componente instanceof JButton) {

				componente.setForeground(Vista.TEXTO_CLARO);
				componente.setBackground(Vista.FONDO_BOTON);
				componente.setFont(Vista.FUENTE_BOTONES);

			} else if(componente instanceof JLabel || componente instanceof JCheckBox) {

				componente.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
				componente.setForeground(Vista.TEXTO_OSCURO);
				componente.setAlignmentX(CENTER_ALIGNMENT);
				
			} else {
				
				cmbColores.setForeground(Vista.TEXTO_OSCURO);
				cmbColores.setBackground(Vista.FONDO_ANIMALES);
			}
			
			componente.setFocusable(false);
		}
		
		//cmbColores.setForeground(Vista.TEXTO_OSCURO);
		//cmbColores.setBackground(Vista.FONDO_ANIMALES);
	}
	
	private JPanel panelPantallaCompleta() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Vista.FONDO_PRINCIPAL);
		
		JPanel panelColores = new JPanel();
		panelColores.add(lColores); 
		panelColores.add(cmbColores);
		
		panel.add(cbPantallaCompleta);
		panel.add(panelColores);
		
		return panel;
	}
	
	public JPanel panelElegirColor() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Vista.FONDO_PRINCIPAL);
		
		panel.add(lColores);
		panel.add(cmbColores);
		
		return panel;
	}
	
	private JPanel panelBotones() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Vista.FONDO_PRINCIPAL);
		
		panel.add(bGuardarCambios);
		panel.add(Box.createRigidArea(new Dimension(100,0)));
		panel.add(bCancelarCambios);
		
		return panel;
	}
	
	public static JCheckBox getCbPantallaCompleta() {
		return cbPantallaCompleta;
	}

	public JButton getbGuardarCambios() {
		return bGuardarCambios;
	}

	public JButton getbCancelarCambios() {
		return bCancelarCambios;
	}

	public JComboBox<String> getCmbColores() {
		return cmbColores;
	}

}
