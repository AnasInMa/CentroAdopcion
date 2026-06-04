package vista;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import controlador.ControladorOpciones;
import modelo.EnumColores;
import utilidades.UtilidadesVariables;

public class VistaOpciones extends JPanel{

	private static final long serialVersionUID = 4344532032686248546L;
	
	private static JCheckBox cbPantallaCompleta;
	private JLabel lColores;
	private JComboBox<String> cmbColores;
	private JButton bGuardarCambios, bCancelarCambios;
	private JPanel panelPrincipal, panelColores, panelBotones;
	
	private static final ImageIcon IMG_EQUIS, IMG_SINMARCAR;
	
	static {
		
		cbPantallaCompleta = new JCheckBox("  Pantalla Completa");
		cbPantallaCompleta.setSelected(UtilidadesVariables.EsPantallaCompleta);
		
		IMG_EQUIS = new ImageIcon("./imgs/equis.png");
		IMG_SINMARCAR = new ImageIcon("./imgs/sinMarcar.png");
	}
	
	public VistaOpciones() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		
		iniciaYModificaComponentes();
		
		this.add(Box.createVerticalGlue());
		this.add(panelPantallaCompleta());
		this.add(panelBotones());
		this.add(Box.createVerticalGlue());
		
		
	}
	
	public void control(ControladorOpciones c) {
		
		this.bGuardarCambios.addActionListener(c);
		this.bCancelarCambios.addActionListener(c);
	}
	
	private void iniciaYModificaComponentes() {
		
		cbPantallaCompleta.setIcon(IMG_SINMARCAR);			//la imagen que tendra por defecto (cuando no esta seleccionado)
		cbPantallaCompleta.setSelectedIcon(IMG_EQUIS);		//la imagen que tendra cuando este seleccionado
		
		this.lColores = new JLabel("Color de la Vista: ");
		
		this.cmbColores = new JComboBox<String>(EnumColores.getNombreColores());
		this.cmbColores.setSelectedItem(EnumColores.buscaNombrePorColores(UtilidadesVariables.ColoresVisibles));
		this.cmbColores.setFont(UtilidadesVariables.FUENTE_BOTONES);
		
		this.bGuardarCambios = new JButton("Guardar");
		this.bCancelarCambios = new JButton("Cancelar");

		JComponent[] componentes1 = {cbPantallaCompleta, bGuardarCambios, bCancelarCambios, cmbColores, lColores};
		
		for (JComponent componente : componentes1) {

			if (componente instanceof JButton) {

				componente.setForeground(UtilidadesVariables.TEXTO_CLARO);
				componente.setBackground(UtilidadesVariables.FONDO_BOTON);
				componente.setFont(UtilidadesVariables.FUENTE_BOTONES);

			} else if(componente instanceof JLabel || componente instanceof JCheckBox) {

				componente.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
				componente.setForeground(UtilidadesVariables.TEXTO_OSCURO);
				componente.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
				componente.setAlignmentX(CENTER_ALIGNMENT);
				
			} else {
				
				componente.setForeground(UtilidadesVariables.TEXTO_OSCURO);
				componente.setBackground(UtilidadesVariables.FONDO_ANIMALES);
			}
			
			componente.setFocusable(false);
		}
		
		//cmbColores.setForeground(UtilidadesVariables.TEXTO_OSCURO);
		//cmbColores.setBackground(UtilidadesVariables.FONDO_ANIMALES);
	}
	
	private JPanel panelPantallaCompleta() {
		
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		
		panelColores = new JPanel();
		panelColores.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		panelColores.add(lColores); 
		panelColores.add(cmbColores);
		
		panelPrincipal.add(cbPantallaCompleta);
		panelPrincipal.add(Box.createVerticalGlue());
		panelPrincipal.add(panelColores);
		
		return panelPrincipal;
	}
	
	public JPanel panelElegirColor() {
		
		JPanel panel = new JPanel();
		panel.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		
		panel.add(lColores);
		panel.add(cmbColores);
		
		return panel;
	}
	
	private JPanel panelBotones() {
		
		panelBotones = new JPanel();
		panelBotones.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		
		panelBotones.add(bGuardarCambios);
		panelBotones.add(Box.createRigidArea(new Dimension(100,0)));
		panelBotones.add(bCancelarCambios);
		
		return panelBotones;
	}
	
	public void actualizarColores() {
		
	    this.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
	    bGuardarCambios.setBackground(UtilidadesVariables.FONDO_BOTON);
	    bGuardarCambios.setForeground(UtilidadesVariables.TEXTO_CLARO);
	    
	    bCancelarCambios.setBackground(UtilidadesVariables.FONDO_BOTON);
	    bCancelarCambios.setForeground(UtilidadesVariables.TEXTO_CLARO);
	    
	    lColores.setForeground(UtilidadesVariables.TEXTO_OSCURO);
	    cmbColores.setForeground(UtilidadesVariables.TEXTO_OSCURO);
	    cmbColores.setBackground(UtilidadesVariables.FONDO_ANIMALES);
	    
	    cbPantallaCompleta.setForeground(UtilidadesVariables.TEXTO_OSCURO);
	    cbPantallaCompleta.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
	    
	    panelPrincipal.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
	    panelColores.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
	    panelBotones.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
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
