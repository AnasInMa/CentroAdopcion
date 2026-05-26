package vista;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import controlador.ControladorOpciones;

public class VistaOpciones extends JPanel{

	private static final long serialVersionUID = 4344532032686248546L;
	
	private static JCheckBox cbPantallaCompleta;
	private JButton bGuardarCambios, bCancelarCambios;
	
	private static final ImageIcon IMG_EQUIS, IMG_SINMARCAR;
	
	static {
		
		IMG_EQUIS = new ImageIcon("./imgs/equis.png");
		IMG_SINMARCAR = new ImageIcon("./imgs/sinMarcar.png");
	}
	
	public VistaOpciones() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Vista.MARRON_CLARO3);
		
		iniciaYModificaComponentes();
		
		this.add(Box.createVerticalGlue());
		this.add(Box.createVerticalGlue());
		this.add(panelTexto());
		this.add(panelBotones());
		this.add(Box.createVerticalGlue());
	}
	
	public void control(ControladorOpciones c) {
		
		//this.cbPantallaCompleta.addActionListener(c);
		this.bGuardarCambios.addActionListener(c);
		this.bCancelarCambios.addActionListener(c);
	}
	
	private void iniciaYModificaComponentes() {
		
		cbPantallaCompleta = new JCheckBox(" Pantalla Completa");
		cbPantallaCompleta.setIcon(IMG_SINMARCAR);			//la imagen que tendra por defecto (cuando no esta seleccionado)
		cbPantallaCompleta.setSelectedIcon(IMG_EQUIS);		//la imagen que tendra cuando este seleccionado
		
		this.bGuardarCambios = new JButton("Guardar");
		this.bCancelarCambios = new JButton("Cancelar");

		JComponent[] componentes = {cbPantallaCompleta, bGuardarCambios, bCancelarCambios};
		
		for (JComponent componente : componentes) {
			
			if(componente instanceof JButton) {
				
				componente.setBackground(Vista.MARRON_CLARO4);
				componente.setFont(Vista.FUENTE_BOTONES);
				
			} else {
				
				componente.setBackground(Vista.MARRON_CLARO3);
				componente.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
			}
			
			componente.setFocusable(false);
			componente.setForeground(Vista.MARRON_OSCURO4);
			
			
		}
	}
	
	private JPanel panelTexto() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Vista.MARRON_CLARO3);
		
		panel.add(cbPantallaCompleta);
		
		return panel;
	}
	
	private JPanel panelBotones() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Vista.MARRON_CLARO3);
		
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

}
